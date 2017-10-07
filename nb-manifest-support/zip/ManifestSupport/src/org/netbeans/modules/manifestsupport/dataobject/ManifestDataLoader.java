package org.netbeans.modules.manifestsupport.dataobject;

import java.io.IOException;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.UniFileLoader;
import org.openide.util.NbBundle;

public class ManifestDataLoader extends UniFileLoader {

    public static final String REQUIRED_MIME = "text/x-java-jar-manifest";

    private static final long serialVersionUID = 1L;

    public ManifestDataLoader() {
        super("org.netbeans.modules.manifestsupport.dataobject.mfDataObject");
    }

    protected String defaultDisplayName() {
        return NbBundle.getMessage(ManifestDataLoader.class, "LBL_mf_loader_name");
    }

    protected void initialize() {
        super.initialize();
        getExtensions().addMimeType(REQUIRED_MIME);
    }

    protected MultiDataObject createMultiObject(FileObject primaryFile) throws DataObjectExistsException, IOException {
        return new ManifestDataObject(primaryFile, this);
    }

    protected String actionsContext() {
        return "Loaders/" + REQUIRED_MIME + "/Actions";
    }

}
