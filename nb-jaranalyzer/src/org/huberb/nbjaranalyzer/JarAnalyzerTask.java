/*
 * JarAnalyzerTask.java
 *
 * Created on 17. Februar 2007, 11:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbjaranalyzer;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import org.huberb.nbjaranalyzer.jaranalyzer.JarAnalyzerProcessor2;
import org.huberb.nbjaranalyzer.jaranalyzer.JarAnalyzerProcessorIF;
import org.huberb.nbjaranalyzer.misc.HtmlResultHelper;
import org.huberb.nbjaranalyzer.misc.ProgressHandleHelper;
import org.openide.ErrorManager;
import org.openide.util.NbBundle;

/**
 * Encapsulate running the JarAnalyzer.
 *
 * @author HuberB1
 */
public class JarAnalyzerTask {
    private JarAnalyzerSourceParams jasp;
    
    /** Creates a new instance of JarAnalyzerTask */
    public JarAnalyzerTask() {
    }

    /**
     * Set the JarAnalyzerSourceParams
     *
     * @param jasp specifying which jars shall get analyzed
     */
    public void setJarAnalyzerSourceParams(JarAnalyzerTask.JarAnalyzerSourceParams jasp) {
        this.jasp = jasp;
    }
    
    /**
     * Launch the JarAnalyzer
     * <p>
     * Use a progress handle to indicate the progress so far. Following steps are done
     * <ol>
     *  <li>Prepare the JarAnalyzerProcessor
     *  <li>Create analyze the jars, as a result create an XML document
     *  <li>Transform the XML document using XSLT to an HTML document
     *  <li>Show the HTML document in the default browser of NB.
     * </ol>
     */
    public void launchJarAnalyzer() {
        final int NUMBER_OF_STEPS = 4;
        final String displayName = NbBundle.getMessage(JarAnalyzerTask.class,"CTL_JarAnalyzerAction");
        final ProgressHandleHelper php = new ProgressHandleHelper( NUMBER_OF_STEPS, displayName );
        
        try {
            // (1) prepare jap
            final StringWriter sw = new StringWriter();
            final PrintWriter pw = new PrintWriter(sw);
            JarAnalyzerProcessorIF jap = new JarAnalyzerProcessor2(pw);
            php.progress();
            
            // (2) create jap summary
            createSummary(jap);
            php.progress();
            
            // (3) transform jap xml using xslt to html
            final TransformHelper th = new TransformHelper();
            final String html = th.transformResult(sw.toString() );
            php.progress();
            
            // (4) show the html in the NB's default browser
            final HtmlResultHelper rh = new HtmlResultHelper();
            rh.showResult( html );
            php.progress();
            
        } catch (Exception ex) {
            ErrorManager.getDefault().notify(ex);
        } finally {
            php.finish();
        }
    }
    
    private void createSummary(JarAnalyzerProcessorIF jap) throws Exception {
        if (this.jasp.getSrcDir() != null) {
            jap.createSummary(this.jasp.getSrcDir());
        } else {
            jap.createSummary(this.jasp.getFiles());
        }
    }
    
    
    static class JarAnalyzerSourceParams {
        private File srcDir;
        private List<File> files;
        
        public JarAnalyzerSourceParams() {
            this.srcDir = null;
            this.files = new ArrayList<File>();
        }
        
        public void setSrcDir(File srcDir) {
            this.srcDir = srcDir;
        }
        
        public File getSrcDir() {
            return srcDir;
        }
        
        public void setFiles(List<File> files) {
            this.files = files;
        }
        
        public List<File> getFiles() {
            return files;
        }
        
    }
    
}
