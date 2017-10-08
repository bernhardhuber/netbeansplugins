package org.huberb.timezone;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

/**
 * Action which shows TimeZone component.
 */
public class TimeZoneAction extends AbstractAction {
    public TimeZoneAction() {
        super(NbBundle.getMessage(TimeZoneAction.class, "CTL_TimeZoneAction"));
        putValue(SMALL_ICON, new ImageIcon(ImageUtilities.loadImage(TimeZoneTopComponent.ICON_RESOURCE, true)));
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        TopComponent win = TimeZoneTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
    
}
