package org.huberb.nbwordcount;

import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.actions.CookieAction;

/**
 * Action which initiates counting of characters, words, and lines.
 */
public final class WordCountingAction extends CookieAction {
    static final String ICON_PATH = "org/huberb/nbwordcount/images/wordCounting.gif";
    
    protected void performAction(final Node[] activatedNodes) {
        final WordCountingActionHelper  wcaiw = new WordCountingActionHelper();
        final Runnable task = wcaiw.createTask( activatedNodes );
        
        // let's go
        RequestProcessor.getDefault().post(task);
    }
    
    
    protected int mode() {
        return CookieAction.MODE_ANY;
    }
    
    public String getName() {
        return NbBundle.getMessage(WordCountingAction.class, "CTL_WordCountingAction");
    }
    
    protected Class[] cookieClasses() {
        return new Class[] {
            DataObject.class
        };
    }
    
    protected void initialize() {
        super.initialize();
        // see org.openide.util.actions.SystemAction.iconResource() javadoc for more details
        //putValue("noIconInMenu", Boolean.TRUE);
    }
    protected String iconResource() {
        return ICON_PATH;
    }
    
    public HelpCtx getHelpCtx() {
        return ConstantsHelper.getHelpCtx();
    }
    
    protected boolean asynchronous() {
        return false;
    }

}
