package org.huberb.regexpnb;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.ErrorManager;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Action which shows Regexp component.
 */
public class RegexpAction extends AbstractAction {

    public RegexpAction() {
        putValue(NAME, NbBundle.getMessage(RegexpAction.class, "CTL_RegexpAction"));
//        putValue(SMALL_ICON, new ImageIcon(ImageUtilities.loadImage("SET/PATH/TO/ICON/HERE", true)));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = WindowManager.getDefault().findTopComponent("RegexpTopComponent");
        if (win == null) {
            ErrorManager.getDefault().log(ErrorManager.WARNING, "Cannot find Regexp component.");
            return;
        }
        win.open();
        win.requestActive();
    }

}
