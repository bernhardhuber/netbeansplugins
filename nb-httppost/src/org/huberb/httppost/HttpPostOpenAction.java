package org.huberb.httppost;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

/**
 * Action which shows HttpPost component.
 */
public class HttpPostOpenAction extends AbstractAction {

    public HttpPostOpenAction() {
        super(NbBundle.getMessage(HttpPostOpenAction.class, "CTL_HttpPostAction"));
//        putValue(SMALL_ICON, new ImageIcon(ImageUtilities.loadImage(HttpPostTopComponent.ICON_PATH, true)));
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        TopComponent win = HttpPostTopComponent.findInstance();
        win.open();
        win.requestActive();
    }

}
