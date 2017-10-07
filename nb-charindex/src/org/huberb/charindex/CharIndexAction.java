package org.huberb.charindex;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import org.openide.ErrorManager;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Action which shows CharIndex component.
 */
public class CharIndexAction extends AbstractAction {

    public CharIndexAction() {
        putValue(NAME, NbBundle.getMessage(CharIndexAction.class, "CTL_CharIndexAction"));
        putValue(SMALL_ICON, new ImageIcon(ImageUtilities.loadImage(CharIndexTopComponent.ICON_RESOURCE, true)));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = WindowManager.getDefault().findTopComponent("CharIndexTopComponent");
        if (win == null) {
            ErrorManager.getDefault().log(ErrorManager.WARNING, "Cannot find CharIndex component.");
            return;
        }
        win.open();
        win.requestActive();
    }

}
