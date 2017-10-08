package org.netbeans.modules.policysupport.dataobject;

import org.openide.loaders.DataNode;
import org.openide.nodes.Children;

public class PolicyDataNode extends DataNode {
    
    //private static final String IMAGE_ICON_BASE = "SET/PATH/TO/ICON/HERE";
    private static final String IMAGE_ICON_BASE = "org/netbeans/modules/policysupport/resources/policy_java.png";
    
    public PolicyDataNode(PolicyDataObject obj) {
        super(obj, Children.LEAF);
        setIconBaseWithExtension(IMAGE_ICON_BASE);
    }
    
//    /** Creates a property sheet. */
//    protected Sheet createSheet() {
//        Sheet s = super.createSheet();
//        Sheet.Set ss = s.get(Sheet.PROPERTIES);
//        if (ss == null) {
//            ss = Sheet.createPropertiesSet();
//            s.put(ss);
//        }
//        // TODO add some relevant properties: ss.put(...)
//        return s;
//    }
    
}
