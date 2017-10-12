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
    
    @Override
    protected void performAction(final Node[] activatedNodes) {
        final WordCountingActionHelper  wcaiw = new WordCountingActionHelper();
        final Runnable task = wcaiw.createTask( activatedNodes );
        
        // let's go
        RequestProcessor.getDefault().post(task);
    }
    
    
    @Override
    protected int mode() {
        return CookieAction.MODE_ANY;
    }
    
    @Override
    public String getName() {
        return NbBundle.getMessage(WordCountingAction.class, "CTL_WordCountingAction");
    }
    
    @Override
    protected Class[] cookieClasses() {
        return new Class[] {
            DataObject.class
        };
    }
    
    @Override
    protected void initialize() {
        super.initialize();
        // see org.openide.util.actions.SystemAction.iconResource() javadoc for more details
        //putValue("noIconInMenu", Boolean.TRUE);
    }
    @Override
    protected String iconResource() {
        return ICON_PATH;
    }
    
    @Override
    public HelpCtx getHelpCtx() {
        return ConstantsHelper.getHelpCtx();
    }
    
    @Override
    protected boolean asynchronous() {
        return false;
    }

}
