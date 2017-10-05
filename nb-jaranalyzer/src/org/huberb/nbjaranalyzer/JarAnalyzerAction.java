package org.huberb.nbjaranalyzer;

import java.io.File;
import org.huberb.nbjaranalyzer.misc.ConstantsHelper;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.actions.CookieAction;

/**
 * An action for running the jar analyzing
 */
public final class JarAnalyzerAction extends CookieAction {
    private final static String ICON_PATH = "org/huberb/nbjaranalyzer/resources/jaranalyzer.png";
    
    protected void performAction(Node[] activatedNodes) {
        if(activatedNodes == null || activatedNodes.length == 0) {
            return ;
        }
        
        // setup jasp, using the selected nodes
        final JarAnalyzerTask.JarAnalyzerSourceParams jasp = new JarAnalyzerTask.JarAnalyzerSourceParams();
        if (activatedNodes.length == 1) {
            final DataObject dataObject = (DataObject)activatedNodes[0].getLookup().lookup(DataObject.class);
            FileObject fo = dataObject.getPrimaryFile();
            if (fo.isFolder()) {
                // analyze any jar in this directory
                jasp.setSrcDir(FileUtil.toFile(fo));
            } else {
                // analyzer this selected jar only
                jasp.getFiles().add( FileUtil.toFile(fo));
            }
        } else {
            // analyzer all selected jars
            for (int i = 0; i < activatedNodes.length; i++ ) {
                final DataObject dataObject = (DataObject)activatedNodes[i].getLookup().lookup(DataObject.class);
                FileObject fo = dataObject.getPrimaryFile();
                File file = FileUtil.toFile(fo);
                jasp.getFiles().add( file );
            }
        }
        
        // Run the jar analyzing in a separate thread
        final Runnable task = createLaunchJarAnalyzerTask(jasp);
        RequestProcessor.getDefault().post(task);
        
    }
    
    /**
     * Create a Runnable for running the JarAnalyzingTask
     *
     * @param jasp specify the jars which shall get analyzer
     * @return Runnable running the jar analyzing
     */
    private Runnable createLaunchJarAnalyzerTask(final JarAnalyzerTask.JarAnalyzerSourceParams jasp) {
        final Runnable task = new Runnable() {
            public void run() {
                final JarAnalyzerTask jat = new JarAnalyzerTask();
                jat.setJarAnalyzerSourceParams( jasp );
                jat.launchJarAnalyzer();
            }
        };
        return task;
    }
    
    protected int mode() {
        return CookieAction.MODE_ALL;
    }
    
    public String getName() {
        return NbBundle.getMessage(JarAnalyzerAction.class, "CTL_JarAnalyzerAction");
    }
    
    protected Class[] cookieClasses() {
        return new Class[] {
            DataObject.class
        };
    }
    
    protected void initialize() {
        super.initialize();
        // see org.openide.util.actions.SystemAction.iconResource() javadoc for more details
        // putValue("noIconInMenu", Boolean.TRUE);
    }
    
    public HelpCtx getHelpCtx() {
        HelpCtx helpCtx = ConstantsHelper.getHelpCtx();
        return helpCtx;
    }
    
    protected boolean asynchronous() {
        return false;
    }
    
    protected String iconResource() {
        return ICON_PATH;
    }
}

