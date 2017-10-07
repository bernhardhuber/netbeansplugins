package org.huberb.i18nvalidator;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import org.huberb.i18nvalidator.api.ComponentCounter;
import org.huberb.i18nvalidator.api.FindInfoComposite;
import org.huberb.i18nvalidator.api.FindItem;
import org.huberb.i18nvalidator.api.SearchInfoComposite;
import org.huberb.i18nvalidator.misc.ProjectHelper;
import org.huberb.i18nvalidator.spi.implementation.CounterListener;
import org.huberb.i18nvalidator.spi.implementation.PropertiesKeyExtractor;
import org.huberb.i18nvalidator.spi.implementation.SimpleRenderVisitor;
import org.huberb.i18nvalidator.spi.implementation.SingleFileSearcher;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileStateInvalidException;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CookieAction;
import org.openide.windows.IOProvider;
import org.openide.windows.OutputWriter;

public final class FindPropertyKeysActions extends CookieAction {
    
    protected void performAction(Node[] activatedNodes) {
        Node node = activatedNodes[0];
        DataObject c = (DataObject)node.getCookie(DataObject.class);
        // TODO use c
        
        // Extract the prop keys
        FileObject foProperty = c.getPrimaryFile();
        FindInfoComposite fic = null;
        try {
            URL propertiesUrl = null;
            propertiesUrl = foProperty.getURL();
            PropertiesKeyExtractor pke = new PropertiesKeyExtractor(propertiesUrl);
            fic = pke.extract();
        } catch (FileStateInvalidException ex) {
            ex.printStackTrace();
        }
        
        if (fic != null) {
            // Output tab text visiting render
            SimpleRenderVisitor srv = new SimpleRenderVisitor();
            // Count #match per FindItem
            CounterListener counterListener = new CounterListener();
            
            // search extracted key props in this project
            Project project = ProjectHelper.findProject(node);
            FileObject projectDirectoryFileObject = project.getProjectDirectory();
            for (Enumeration files = projectDirectoryFileObject.getData(true); files.hasMoreElements(); ) {
                FileObject fo = (FileObject)files.nextElement();
                final String foMimeType = fo.getMIMEType();
                
                if (foMimeType.startsWith("text")) { // f.getName().endsWith("java")) {
                    File f = FileUtil.toFile(fo);
                    System.out.println( "f " + f.toString() );
                    
                    SingleFileSearcher sfs = new SingleFileSearcher(f);
                    sfs.getSearchVisitor().addPropertyChangeListener( counterListener );
                    
                    SearchInfoComposite sic = sfs.search(fic);
                    if (sic != null && sic.getSize() > 0) {
                        sic.accept( srv );
                    }
                    sfs.getSearchVisitor().removePropertyChangeListener( counterListener );
                }
            }
            
            // TODO what to do with counterListener???!!!
            IOProvider iop = IOProvider.getDefault();
            OutputWriter ow = iop.getIO( "FindPropertyKey Counter", false ).getOut();
            List<ComponentCounter<FindItem>> ccfil = counterListener.getCounterFindItemList();
            for (Iterator<ComponentCounter<FindItem>> i = ccfil.iterator(); i.hasNext(); ) {
                ComponentCounter<FindItem> ccfi = i.next();
                FindItem fi = ccfi.getComponent();
                Integer count = ccfi.getCount();
                
                ow.println( fi.getPattern() + ": " + count );
            }
            
        }
        
    }
    
    protected int mode() {
        //return CookieAction.MODE_ALL;
        return CookieAction.MODE_EXACTLY_ONE;
    }
    
    public String getName() {
        return NbBundle.getMessage(FindPropertyKeysActions.class, "CTL_FindPropertyKeysActions");
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
    
    static class PropertiesActivatedNodeHandler extends AbstractPropertiesActivatedNodeHandler {
        
        public PropertiesActivatedNodeHandler() {
            super(true);
        }
        
        
        protected void handleSingleFileObject(FileObject fo) {
            //System.out.println( "FO " + fo.getNameExt() + ", " + fo.getMIMEType() );
            if (fo.isData()) {
                File file = FileUtil.toFile(fo);
                
            }
        }
    }
}

