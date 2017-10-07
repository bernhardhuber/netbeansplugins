package org.huberb.i18nvalidator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.huberb.i18nvalidator.matcher.PropertiesKeyUsageHelper;
import org.huberb.i18nvalidator.misc.IOHelper;
import org.huberb.i18nvalidator.misc.ProgressHandleHelper;
import org.huberb.i18nvalidator.misc.ProjectHelper;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.actions.CookieAction;
import org.openide.windows.OutputWriter;

public final class PropertiesKeyUsageAction extends CookieAction {
    
    protected void performAction(final Node[] activatedNodes) {
        final Runnable task = new Runnable() {
            public void run() {
                evaluateKeyUsage( activatedNodes );
            }
        };
        RequestProcessor.getDefault().post(task);
        
    }
    
    protected void evaluateKeyUsage(Node[] activatedNodes ) {
        // gather all keys
        // TODO fix handling activatedNodes.length > 1
        PropertiesActivatedNodeHandler  panh = new PropertiesActivatedNodeHandler();
        panh.handleActivatedNodes( activatedNodes );
        Properties allProperties = panh.getProps();
        
        ProgressHandleHelper phh = new ProgressHandleHelper(3, "Key Usage");
        try {
            // determine the base directory
            FileObject projectDirectoryFileObject = null;
            Project project = ProjectHelper.findProject(activatedNodes[0]);
            if (project != null) {
                projectDirectoryFileObject = project.getProjectDirectory();
            }
            phh.progress();
            
            // evaluate the key usage from files of the base directory
            if (projectDirectoryFileObject != null) {
                PropertiesKeyUsageHelper pku = new PropertiesKeyUsageHelper();
                pku.setProps( allProperties );
                // gather usage
                for (Enumeration files = projectDirectoryFileObject.getData(true); files.hasMoreElements(); ) {
                    FileObject fo = (FileObject)files.nextElement();
                    File f = FileUtil.toFile(fo);
                    phh.progress(f.getAbsolutePath());
                    // TODO find some better way to exclude files
                    if (f.length() < 10L * 1024L * 1024L) {
                        pku.scanFile( f );
                    }
                }
                phh.progress();
                
                //---
                // output usage result
                IOHelper ioHelper = new IOHelper( "Properties Key Usage", false );
                OutputWriter ow = ioHelper.getOut();
                
                Map<String,String> propUsage = pku.getKeysToFiles();
                for (Iterator i = allProperties.entrySet().iterator(); i.hasNext(); ) {
                    Map.Entry me = (Map.Entry)i.next();
                    String key = (String)me.getKey();
                    if (key != null) {
                        phh.progress(key);
                        
                        String keyUsage = (String)propUsage.get( key );
                        if (keyUsage == null) keyUsage = "-";
                        
                        ow.println( key + ": " + keyUsage );
                    } else {
                        //System.out.println("key is null???");
                    }
                }
                phh.progress();
            }
            
        } finally {
            phh.finish();
        }
    }

    
    static class PropertiesActivatedNodeHandler extends AbstractPropertiesActivatedNodeHandler {
        private Properties props;
        public PropertiesActivatedNodeHandler() {
            super(true);
            
            props = new Properties();
        }
        
        public Properties getProps() {
            return props;
        }
        
        protected void handleSingleFileObject(FileObject fo) {
            //System.out.println( "FO " + fo.getNameExt() + ", " + fo.getMIMEType() );
            if (fo.isData()) {
                File file = FileUtil.toFile(fo);
                Properties oneFileProps = new Properties();
                FileInputStream fis;
                try {
                    fis = new FileInputStream(file);
                    try {
                        oneFileProps.load( fis );
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } finally {
                        try {
                            fis.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    this.props.putAll( oneFileProps );
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    protected int mode() {
        return CookieAction.MODE_ALL;
    }
    
    public String getName() {
        return NbBundle.getMessage(PropertiesKeyUsageAction.class, "CTL_PropertiesKeyUsageAction");
    }
    
    protected Class[] cookieClasses() {
        return new Class[] {
            DataObject.class,
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

