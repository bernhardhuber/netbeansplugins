package org.huberb.jdepend;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import jdepend.framework.JavaClass;
import jdepend.framework.PackageFilter;
import jdepend.framework.ParserListener;
import org.huberb.jdepend.misc.HtmlResultHelper;
import org.huberb.jdepend.misc.ProgressHandleHelper;
import org.huberb.jdepend.misc.ProjectHelper;
import org.huberb.jdepend.options.JDependSettings;
import org.huberb.jdependlib.JDependProcessorXML2;
import org.netbeans.api.project.Project;
import org.openide.ErrorManager;
import org.openide.util.NbBundle;


/**
 * Encapsulate running JDepend here
 *
 * @author HuberB1
 */
public class JDependTask {
    JDependTask() {
    }
    
    /**
     * Run JDepend
     *
     * @param project the project for which JDepend shall run
     */
    public void launchJDepend(Project project) {
        
        // create a progress handle for showing the progress
        final int NUMBER_OF_STEPS = 3;
        final String displayName = NbBundle.getMessage(JDependTask.class,"CTL_JDependAction");
        final ProgressHandleHelper php = new ProgressHandleHelper( NUMBER_OF_STEPS, displayName );
        
        try {
            // (1) determine classes directory (build.classes.dir)
            final ProjectHelper ph = new ProjectHelper();
            final List<String> classDirectories = ph.findClassDirectories(project);
            
            // defines output sink of jdepend xml file
            final StringWriter sw = new StringWriter();
            final JDependProcessorXML2 jdp = createJDependProcessor(sw);
            // attach progress listener
            attachListener( jdp, php );
            
            // add directories holding classes which shall be analyzed
            for(String classDirectory : classDirectories) {
                try {
                    jdp.addDirectory( classDirectory );
                } catch (IOException ex) {
                    ErrorManager.getDefault().log(ErrorManager.WARNING, "Cannot add directory " + classDirectory );
                }
            }
            php.progress();
            
            // (2) start the jdepend analyze
            jdp.analyze();
            php.progress();
            
            // (3) show the generated html file
            final TransformHelper th = new TransformHelper();
            final String html = th.transformResult(sw.toString() );
            final HtmlResultHelper rh = new HtmlResultHelper();
            rh.showResult( html );
            php.progress();
        }  finally {
            php.finish();
        }
    }
    
    /**
     * Create processor running jdepend analyze
     *
     * @param sw destination writer for jdepend analyze,
     *   results of the jdepend analyze are written here
     * @return JDependProcessorXML created jdepend processor
     */
    protected JDependProcessorXML2 createJDependProcessor(Writer sw ) {
        
        // read settings from JDependSettings
        
        // (1) setup the filter packages
        final PackageFilter fp = new PackageFilter();
        final JDependSettings settings = JDependSettings.getDefault();
        String[] filterPackages = settings.getFilterPackages();
        for (int i = 0; i < filterPackages.length; i++ ) {
            final String filterPackage = filterPackages[i];
            fp.addPackage( filterPackage );
        }
        // (2) analyze inner classes flag
        final boolean analyzeInnerClasses = settings.getAnalyzerInnerClasses().booleanValue();
        
        // (3) Create a print writer
        final PrintWriter pw = new PrintWriter(sw);
        
        // (3) create the processor
        final JDependProcessorXML2 jdp = new JDependProcessorXML2(pw);
        // (3.1) set options
        jdp.analyzeInnerClasses( analyzeInnerClasses );
        jdp.setFilter( fp );
        
        return jdp;
    }
    
    private void attachListener(JDependProcessorXML2 jdp, ProgressHandleHelper phh) {
        final JDependParseListener jdpl = new JDependParseListener(phh);
        
        jdp.addParseListener( jdpl );
    }
    
    static class JDependParseListener implements ParserListener {
        private final ProgressHandleHelper phh;
        
        JDependParseListener(ProgressHandleHelper phh) {
            this.phh = phh;
        }
        
        public void onParsedJavaClass(final JavaClass javaClass) {
            final String className = javaClass.getName();            
            phh.progress( className );
        }
        
    }
}