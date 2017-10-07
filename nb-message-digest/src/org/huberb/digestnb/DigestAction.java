package org.huberb.digestnb;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.ErrorManager;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Action which shows Digest component.
 */
public class DigestAction extends AbstractAction {

    public DigestAction() {
        putValue(NAME, NbBundle.getMessage(DigestAction.class, "CTL_DigestAction"));
//        putValue(SMALL_ICON, new ImageIcon(ImageUtilities.loadImage("SET/PATH/TO/ICON/HERE", true)));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = WindowManager.getDefault().findTopComponent("DigestTopComponent");
        if (win == null) {
            ErrorManager.getDefault().log(ErrorManager.WARNING, "Cannot find Digest component.");
            return;
        }
        win.open();
        win.requestActive();
    }

}
