package org.netbeans.modules.policysupport.dataobject;

import java.io.IOException;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.UniFileLoader;
import org.openide.util.NbBundle;

public class PolicyDataLoader extends UniFileLoader {
    
    public static final String REQUIRED_MIME = "text/x-java-policy";
    
    private static final long serialVersionUID = 1L;
    
    public PolicyDataLoader() {
        super("org.netbeans.modules.policysupport.dataobject.PolicyDataObject");
    }
    
    protected String defaultDisplayName() {
        return NbBundle.getMessage(PolicyDataLoader.class, "LBL_Policy_loader_name");
    }
    
    protected void initialize() {
        super.initialize();
        getExtensions().addMimeType(REQUIRED_MIME);
    }
    
    protected MultiDataObject createMultiObject(FileObject primaryFile) throws DataObjectExistsException, IOException {
        return new PolicyDataObject(primaryFile, this);
    }
    
    protected String actionsContext() {
        return "Loaders/" + REQUIRED_MIME + "/Actions";
    }
    
}
