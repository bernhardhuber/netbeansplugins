package org.huberb.cryptoservices;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

/**
 * Action which shows CryptographicServices component.
 */
public class CryptographicServicesAction extends AbstractAction {

    public CryptographicServicesAction() {
        super(NbBundle.getMessage(CryptographicServicesAction.class, "CTL_CryptographicServicesAction"));
//        putValue(SMALL_ICON, new ImageIcon(Utilities.loadImage(CryptographicServicesTopComponent.ICON_PATH, true)));
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        TopComponent win = CryptographicServicesTopComponent.findInstance();
        win.open();
        win.requestActive();
    }

}
