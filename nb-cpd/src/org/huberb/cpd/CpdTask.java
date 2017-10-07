/*
 * CpdTask.java
 *
 * Created on 21. April 2007, 15:43
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.cpd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sourceforge.pmd.cpd.CPDListener;
import org.huberb.cpd.cpdlib.CpdProcessor;
import org.huberb.cpd.misc.ProgressHandleHelper;
import org.openide.cookies.LineCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.text.Line;
import org.openide.util.NbBundle;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputEvent;
import org.openide.windows.OutputListener;
import org.openide.windows.OutputWriter;

/**
 * Encapsulate launching CPD.
 * <p>
 * The encapsulation makes it possible to run these task
 * in a non-swing thread.
 *
 * @author HuberB1
 */
public class CpdTask {
    private CpdProcessor cpdProcessor;
    private StringWriter stringWriter;
    
    /** Creates a new instance of CpdTask */
    public CpdTask() {
        this.stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(this.stringWriter);
        cpdProcessor = new CpdProcessor(printWriter);
    }
    
    public void addFile(DataObject dataObject) {
        //final DataObject dataObject = (DataObject)activatedNodes[0].getLookup().lookup(DataObject.class);
        FileObject fo = dataObject.getPrimaryFile();
        cpdProcessor.addFile(FileUtil.toFile(fo));
    }
    
    public void launchCpd() {
        // TODO we 5 ??? see CPDListener.DONE +1
        final int NUMBER_OF_STEPS = 5;
        final String displayName = NbBundle.getMessage(CpdTask.class,"CTL_CpdAction");
        final ProgressHandleHelper php = new ProgressHandleHelper( NUMBER_OF_STEPS, displayName );
        try {
            MyCpdListener mcl = new MyCpdListener(php);
            cpdProcessor.setCpdListener(mcl);
            cpdProcessor.analyze();
        } finally {
            php.finish();
        }
        
        dumpResultToOutput(this.stringWriter.toString());
        
    }
    
    /**
     * Dump text to the output tab "cpd"
     *
     * @param sw the text to be dumped
     */
    protected void dumpResultToOutput(String sw) {
        final String tabTitle = NbBundle.getMessage(CpdTask.class, "TAB_CPD_OUTPUT");
        final InputOutput io = IOProvider.getDefault().getIO(tabTitle, false);
        final OutputWriter ow = io.getOut();
        
        MyOutputListener mol = MyOutputListener.mol;
        StringReader sr = new StringReader(sw);
        BufferedReader br = new BufferedReader(sr);
        try {
            RendererMatching rm = new RendererMatching();
            for( String line; (line = br.readLine()) != null; ) {
                if (rm.isHyperlinkedLine(line)) {
                    ow.println( line, mol );
                    // add attachment:
                } else {
                    ow.println(line);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
    }
    
    /**
     * Listener to CPD events.
     * <p>
     * This implementation updates the progress handler accordingly
     */
    static class MyCpdListener implements CPDListener {
        private final ProgressHandleHelper phh;
        
        /** Creates a new instance of MyCpdListener */
        public MyCpdListener(ProgressHandleHelper phh) {
            this.phh = phh;
        }
        
        public void addedFile(int i, File file) {
            phh.progress( file.toString() );
        }
        
        public void phaseUpdate(int i) {
            phh.progress();
            
            String m = mapPhaseToProgress(i);
            if (m != null) {
                phh.progress( m );
            }
        }
        
        protected String mapPhaseToProgress(int phase) {
            String m = null;
            
            String messageKey = null;
            switch (phase) {
                case CPDListener.INIT:
                    messageKey = "CpdPhase.Init"; break;
                case CPDListener.HASH:
                    messageKey = "CpdPhase.Hash"; break;
                case CPDListener.MATCH:
                    messageKey = "CpdPhase.Match"; break;
                case CPDListener.GROUPING:
                    messageKey = "CpdPhase.Grouping"; break;
                case CPDListener.DONE:
                    messageKey = "CpdPhase.Done"; break;
            }
            if (messageKey != null) {
                m = NbBundle.getMessage( CpdTask.class, messageKey );
            }
            return m;
        }
        
    }
    
    /**
     * A listener listening on output tab message actions
     */
    static class MyOutputListener implements OutputListener {
        static MyOutputListener mol = new MyOutputListener();
        
        public void outputLineSelected(OutputEvent outputEvent) {
            // do nothing
        }
        
        /**
         * The user has selected a line.
         * <p>
         * Extract filename, line, and info from the message line,
         * open the file, jump to the linenumber, and attach an editor annotation
         */
        public void outputLineAction(OutputEvent outputEvent) {
            //dumpOutputEvent( "outputLineAction", outputEvent );
            
            RendererMatching rm = new RendererMatching();
            String lineString = outputEvent.getLine();
            Object[] result = rm.extract( lineString );
            if (result != null && result[0] != null) {
                Line line = (Line) result [0];
                //
                line.show(line.SHOW_TOFRONT);
                
                // add an annotation
                CpdAnnotation cpdAnnotation = new CpdAnnotation(lineString);
                cpdAnnotation.attach( line );
            }
            
        }
        
        public void outputLineCleared(OutputEvent outputEvent) {
            // do nothing
        }
        
    }
    
    /**
     * Encapsulate splitting up a cpd message line into:
     * filename, line, and info.
     * <p>
     * Sample output:
     * <pre><code>
     * &lt;file line="153" path="C:\all_projects\nb4.0\hia-spring\hia-spring-web\catalina-base\work\Catalina\localhost\hia-spring\org\apache\jsp\WEB_002dINF\jsp\items_jsp.java"/>
     * Starting at line 103 of C:\all_projects\jbossCourse\labs\lab-jbosscache\src\org\jboss\test\cache\perf\aop\ReplicatedSyncPerfAopTest.java
     * 57,233,2,84,C:\all_projects\jbossCourse\labs\lab-jbosscache\src\org\jboss\test\cache\perf\basic\ReplicatedAsyncMapPerfTest.java,84,C:\all_projects\jbossCourse\labs\lab-jbosscache\src\org\jboss\test\cache\perf\basic\ReplicatedSyncMapPerfTest.java
     *</code></pre>
     */
    static class RendererMatching {
        private Pattern xmlPattern = Pattern.compile("^<file line=\"([0-9]+)\" path=\"(.+)\"/>");
        private Pattern textPattern = Pattern.compile("^Starting at line ([0-9]+) of (.*)");
        
        // lines,tokens,occurence, line,path, line,path,...
        // private Pattern csvPattern = Pattern.compile("^[0-9]+,[0-9]+,[0-9]+,([0-9]+),(.*)");
        private Pattern[] patterns = new Pattern[] { xmlPattern, textPattern };
        public RendererMatching() {
            
        }
        
        public boolean isHyperlinkedLine( String line ) {
            boolean isHyperlinkedLine = false;
            for (int i = 0; !isHyperlinkedLine && i < patterns.length; i++ ) {
                Pattern p = patterns[i];
                Matcher matcher = p.matcher( line );
                isHyperlinkedLine = matcher.matches();
            }
            return isHyperlinkedLine;
        }
        
        public Object[] extract( String message ) {
            // try to find a match
            Matcher matcher = null;
            boolean matches = false;
            for (int i = 0; !matches && i < patterns.length; i++ ) {
                Pattern p = patterns[i];
                matcher = p.matcher( message );
                matches = matcher.matches();
            }
            
            // extract filename, and linenumber form the match
            if (matches && matcher != null && matcher.groupCount() >= 2) {
                String fileName = matcher.group(2);
                String lineNumberString = matcher.group(1);
                int lineNumber = Integer.parseInt(lineNumberString);
                
                FileObject fo = FileUtil.toFileObject(new File(fileName));
                if (fo == null) {
                    return null;
                }
                try {
                    DataObject dob = DataObject.find(fo);
                    LineCookie lineCookie = (LineCookie) dob.getCookie(LineCookie.class);
                    if (lineCookie == null) {
                        return null;
                    }
                    Line line = lineCookie.getLineSet().getOriginal(lineNumber - 1);
                    return new Object[] {line, message};
                } catch (DataObjectNotFoundException ex) {
                    return null;
                }
            }
            return null;
        }
    }
}