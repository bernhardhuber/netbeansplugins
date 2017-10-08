package org.huberb.jdepend;

import org.huberb.jdepend.misc.ConstantsHelper;
import org.netbeans.api.project.Project;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.actions.CookieAction;

/**
 * An action for running JDepend upon a Project node
 */
public final class JDependAction extends CookieAction {
    private final static String ICON_PATH = "org/huberb/jdepend/resources/jdepend.png";
    
    @Override
    protected void performAction(Node[] activatedNodes) {
        if(activatedNodes == null || activatedNodes.length == 0) {
            return ;
        }

        final Node activatedNode = activatedNodes[0];
        final Project project = (Project)activatedNode.getLookup().lookup(Project.class);
        if (project == null) {
            return;
        }
        // create the task running JDepend
        final Runnable task = createLaunchJDependTask(project);
        // start it
        RequestProcessor.getDefault().post(task);
    }
    
    /**
     * Create a Runnable running launchJDepend
     *
     * @param project the project for which JDepend shall run
     * @return Runnable running launchJDepend
     */
    private Runnable createLaunchJDependTask( final Project project ) {
        final Runnable task = new Runnable() {
            @Override
            public void run() {
                final JDependTask jdt = new JDependTask();
                jdt.launchJDepend(project);
            }
        };
        return task;
    }
    
    @Override
    protected int mode() {
        return CookieAction.MODE_EXACTLY_ONE;
    }
    
    public String getName() {
        return NbBundle.getMessage(JDependAction.class, "CTL_JDependAction");
    }
    
    @Override
    protected Class[] cookieClasses() {
        return new Class[] {
            Project.class
        };
    }
    
    @Override
    protected void initialize() {
        super.initialize();
        // see org.openide.util.actions.SystemAction.iconResource() javadoc for more details
        // putValue("noIconInMenu", Boolean.TRUE);
    }
    
    @Override
    public HelpCtx getHelpCtx() {
        HelpCtx helpCtx = ConstantsHelper.getHelpCtx();
        return helpCtx;
    }
    
    @Override
    protected boolean asynchronous() {
        return false;
    }

    @Override
    protected String iconResource() {
        return ICON_PATH;
    }

}

