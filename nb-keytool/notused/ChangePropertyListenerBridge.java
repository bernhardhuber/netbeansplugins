package notused;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * @deprecated Use *ChangeListenerBridge classes instead
 */
public class ChangePropertyListenerBridge implements ChangeListener {
    private PropertyChangeListener pcl;
    public ChangePropertyListenerBridge(PropertyChangeListener pcl) {
        this.pcl = pcl;
    }
    public void stateChanged(ChangeEvent cevt) {
        PropertyChangeEvent pcevt = new PropertyChangeEvent(cevt.getSource(), "value", null, null );
        this.pcl.propertyChange( pcevt );
    }
}