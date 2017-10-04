package org.huberb.localenb;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import org.openide.ErrorManager;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Action which shows <code>LocalePanel</code> component.
 *
 * @see org.huberb.localenb.LocalePanel
 */
public class OpenLocaleTopComponentAction extends AbstractAction {
    private static final String ICON_RESOURCE = "org/huberb/localenb/images/locale-16x16.png";
    /**
     * Creates a new instance of OpenLocaleTopComponentAction
     */
    public OpenLocaleTopComponentAction() {
        putValue(NAME, NbBundle.getMessage(OpenLocaleTopComponentAction.class, "CTL_LocaleAction"));
        putValue(SMALL_ICON, new ImageIcon(Utilities.loadImage(ICON_RESOURCE, true)));
    }
    
    public void actionPerformed(ActionEvent evt) {
        final TopComponent win = WindowManager.getDefault().findTopComponent("LocaleTopComponent");
        if (win == null) {
            ErrorManager.getDefault().log(ErrorManager.WARNING, "Cannot find Locale component.");
            return;
        }
        win.open();
        win.requestActive();
    }
}
