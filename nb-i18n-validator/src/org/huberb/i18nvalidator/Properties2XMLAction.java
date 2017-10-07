package org.huberb.i18nvalidator;

import java.io.File;
import java.io.IOException;
import org.huberb.i18nvalidator.converter.PropertiesConverter;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CookieAction;

public final class Properties2XMLAction extends CookieAction {
    
    protected void performAction(Node[] activatedNodes) {
        XMLPropertiesActivatedNodeHandler  panh = new XMLPropertiesActivatedNodeHandler();
        panh.handleActivatedNodes( activatedNodes );
    }
    
    static class XMLPropertiesActivatedNodeHandler extends AbstractActivatedNodeHandler {
        public XMLPropertiesActivatedNodeHandler() {
            super(true);
        }
        protected void handleSingleFileObject(FileObject fo) {
            //System.out.println( "FO " + fo.getNameExt() + ", " + fo.getMIMEType() );
            if (fo.isData()) {
                File file = FileUtil.toFile(fo);
                PropertiesConverter propertiesConverter = new PropertiesConverter();
                try {
                    propertiesConverter.properties2XML(file);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
        
    protected int mode() {
        return CookieAction.MODE_ALL;
    }
    
    public String getName() {
        return NbBundle.getMessage(Properties2XMLAction.class, "CTL_Properties2XMLAction");
    }
    
    protected Class[] cookieClasses() {
        return new Class[] {
            DataObject.class
        };
    }
    
    
    protected void initialize() {
        super.initialize();
        // see org.openide.util.actions.SystemAction.iconResource() javadoc for more details
        putValue("noIconInMenu", Boolean.TRUE);
    }
    
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }
    
    protected boolean asynchronous() {
        return false;
    }
    
}

