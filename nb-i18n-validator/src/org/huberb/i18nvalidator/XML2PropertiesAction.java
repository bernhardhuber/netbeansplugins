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

public final class XML2PropertiesAction extends CookieAction {
    
    protected void performAction(Node[] activatedNodes) {
        PropertiesActivatedNodeHandler  panh = new PropertiesActivatedNodeHandler();
        panh.handleActivatedNodes( activatedNodes );
    }
    
    static class PropertiesActivatedNodeHandler extends AbstractPropertiesActivatedNodeHandler {
        public PropertiesActivatedNodeHandler() {
            super(true);
        }
        protected void handleSingleFileObject(FileObject fo) {
            //System.out.println( "FO " + fo.getNameExt() + ", " + fo.getMIMEType() );
            if (fo.isData()) {
                File file = FileUtil.toFile(fo);
                PropertiesConverter propertiesConverter = new PropertiesConverter();
                try {
                    propertiesConverter.xml2Properties(file);
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
        return NbBundle.getMessage(XML2PropertiesAction.class, "CTL_XML2PropertiesAction");
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

