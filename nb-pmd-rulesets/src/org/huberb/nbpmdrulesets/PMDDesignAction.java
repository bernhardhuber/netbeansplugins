package org.huberb.nbpmdrulesets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.tools.ant.module.api.support.ActionUtils;
import org.huberb.nbpmdrulesets.options.PmdRulesetSettings;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.execution.ExecutorTask;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.filesystems.Repository;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.actions.CallableSystemAction;

/**
 * An action for launching PMD's designer GUI application.
 * <p>
 * This action launches the designer GUI applicatio via ant
 */
public final class PMDDesignAction extends CallableSystemAction {
    
    public void performAction() {
        // run ant target for launching pmd ruleset designer
        
        final String target = "launch-pmd-designer";
        final Properties props = new Properties();
        // pass pmd.home to ant as property
        PmdRulesetSettings pmdRulesetSettings = PmdRulesetSettings.getDefault();
        // validate pmdHome
        if (!pmdRulesetSettings.validatePmdHome()) {
            // TODO inform user to set pmdHome directory
            
            String message = NbBundle.getMessage(PMDDesignAction.class, "MSG_BAD_PMD_HOME", pmdRulesetSettings.getPmdHome() );
            
            NotifyDescriptor ndesc = new NotifyDescriptor.Message(message, NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(ndesc);
            
            return ;
        }
        
        String pmdHome = pmdRulesetSettings.getPmdHome();
        props.put( "pmd.home", pmdHome );
        
        // create physical ant file
        final String resource = "PmdRulesets/pmd-ruleset-ant.xml";
        final FileObject foAnt = createAntFile(resource);
        // TODO check foAnt existance, not null...
        
        //
        final RequestProcessor rp = RequestProcessor.getDefault();
        final Runnable runnable = new Runnable() {
            public void run() {
                try {
                    final ExecutorTask et = ActionUtils.runTarget( foAnt, new String[] {target}, props );
                    //et.result();
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        foAnt.delete();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        final RequestProcessor.Task task = rp.post( runnable );
    }
    
    public String getName() {
        return NbBundle.getMessage(PMDDesignAction.class, "CTL_PMDDesignAction");
    }
    
    protected void initialize() {
        super.initialize();
        // see org.openide.util.actions.SystemAction.iconResource() javadoc for more details
        putValue("noIconInMenu", Boolean.TRUE);
    }
    
    public HelpCtx getHelpCtx() {
        return new HelpCtx("org.huberb.nbpmdrulesets.about");
    }
    
    protected boolean asynchronous() {
        return false;
    }
    
    /**
     * Create a temp ant file for running ant
     */
    private FileObject createAntFile(String resource) {
        FileObject foAnt = null;
        
        final FileObject foAntResource = Repository.getDefault().getDefaultFileSystem().findResource(resource);
        try {
            InputStream fis = foAntResource.getInputStream();
            
            File foAntFile = File.createTempFile("nb-pmd-rulesets", "ant");
            foAntFile.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(foAntFile);
            
            try {
                int readCount = 0;
                byte[] byteBuffer = new byte[8*1024];
                while ((readCount = fis.read(byteBuffer)) > -1) {
                    fos.write( byteBuffer, 0, readCount);
                }
            } finally {
                fis.close();
                fos.close();
            }
            
            foAnt = FileUtil.toFileObject(foAntFile.getCanonicalFile());
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
        return foAnt;
        
    }
}
