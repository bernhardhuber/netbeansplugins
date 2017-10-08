package org.huberb.randompassword;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.ErrorManager;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Action which shows RandomPassword component.
 */
public class RandomPasswordAction extends AbstractAction {

    public RandomPasswordAction() {
        putValue(NAME, NbBundle.getMessage(RandomPasswordAction.class, "CTL_RandomPasswordAction"));
//        putValue(SMALL_ICON, new ImageIcon(Utilities.loadImage("SET/PATH/TO/ICON/HERE", true)));
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        TopComponent win = WindowManager.getDefault().findTopComponent("RandomPasswordTopComponent");
        if (win == null) {
            ErrorManager.getDefault().log(ErrorManager.WARNING, "Cannot find RandomPassword component.");
            return;
        }
        win.open();
        win.requestActive();
    }

}
