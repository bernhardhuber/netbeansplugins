package org.huberb.cpd;

import org.huberb.cpd.misc.ConstantsHelper;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.actions.CookieAction;

/**
 * An action for running CPD.
 *
 */
public final class CpdAction extends CookieAction {
    private final static String ICON_PATH = "org/huberb/cpd/resources/cpd.png";
    
    protected void performAction(Node[] activatedNodes) {
        if(activatedNodes == null || activatedNodes.length == 0) {
            return ;
        }
        
        // Run CPD in a separate thread
        final Runnable task = createLaunchJarAnalyzerTask(activatedNodes);
        RequestProcessor.getDefault().post(task);
        
    }
    
    /**
     * Create a Runnable for running the Cpd Task
     *
     * @param nodes specify the files, and dirs which shall get analyzer
     * @return Runnable running the jar analyzing
     */
    private Runnable createLaunchJarAnalyzerTask(final Node[] activatedNodes) {
        final Runnable task = new Runnable() {
            public void run() {
                final CpdTask cpdTask = new CpdTask();
                
                if (activatedNodes.length == 1) {
                    final DataObject dataObject = (DataObject)activatedNodes[0].getLookup().lookup(DataObject.class);
                    cpdTask.addFile( dataObject );
                } else {
                    // analyzer all selected jars
                    for (int i = 0; i < activatedNodes.length; i++ ) {
                        final DataObject dataObject = (DataObject)activatedNodes[i].getLookup().lookup(DataObject.class);
                        cpdTask.addFile( dataObject );
                    }
                }
                cpdTask.launchCpd();
            }
        };
        return task;
    }
    
    protected int mode() {
        return CookieAction.MODE_ALL;
    }
    
    public String getName() {
        return NbBundle.getMessage(CpdAction.class, "CTL_CpdAction");
    }
    
    protected Class[] cookieClasses() {
        return new Class[]{
            DataFolder.class, 
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

