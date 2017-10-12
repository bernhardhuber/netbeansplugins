package org.huberb.nbwordcount;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import org.openide.util.HelpCtx;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

/**
 * Action which shows WordCount TopComponent window.
 */
public class WordCountAction extends AbstractAction {
    
    public WordCountAction() {
        super(NbBundle.getMessage(WordCountAction.class, "CTL_WordCountAction"));
        putValue(SMALL_ICON, new ImageIcon(ImageUtilities.loadImage(WordCountTopComponent.ICON_PATH, true)));
    }
    
    public HelpCtx getHelpCtx() {
        return ConstantsHelper.getHelpCtx();
    }
        
    @Override
    public void actionPerformed(ActionEvent evt) {
        TopComponent win = WordCountTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
    
}
