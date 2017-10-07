package org.huber.keytool.action;

import java.awt.Component;
import java.awt.Dialog;
import java.io.File;
import java.text.MessageFormat;

import javax.swing.JComponent;

import org.huber.keytool.KeyStoreBeanHistory;
import org.huber.keytool.KeyStoreTopComponent;
import org.huber.keytool.model.ConstantsHelper;
import org.huber.keytool.model.KeyStoreBean;
import org.huber.keytool.model.KeyStoreBeanFactory;
import org.huber.keytool.ui.wizard.loadkeystore.EnterKeyStorePasswordWizardPanel;
import org.huber.keytool.ui.wizard.loadkeystore.SelectKeyStoreWizardPanel;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

// An example action demonstrating how the wizard could be called from within
// your code. You can copy-paste the code below wherever you need.
public final class LoadKeyStoreAction extends CallableSystemAction {

    private WizardDescriptor.Panel[] panels;
    private WizardDescriptor wizardDescriptor;

    public void performActionWith( File keyStoreFile ) {
        doPerformAction(keyStoreFile );
    }
    public void performAction() {
        doPerformAction(null);
    }

    private void doPerformAction( File preSelectedKeyStoreFile ) {

        final KeyStoreTopComponent win = KeyStoreTopComponent.findInstance();
        if (win != null) {
            final String prevKeyStoreName = win.getKeyStoreBean().getName();
            final KeyStoreBeanHistory ksbh = win.getKeyStoreBeanHistory();
            boolean cancelled = showWizard(preSelectedKeyStoreFile, prevKeyStoreName, ksbh);

            if (!cancelled) {
                // do something
                final File keyStoreFile =  (File)this.wizardDescriptor.getProperty( "keyStoreFile" );
                final char[] password = (char[])this.wizardDescriptor.getProperty( "password" );

                KeyStoreBeanFactory ksbf = new KeyStoreBeanFactory();
                final KeyStoreBean ksb = ksbf.createKeyStoreBean(keyStoreFile, password);

                if (ksb != null) {
                    win.setKeyStoreBean( ksb );
                    win.open();
                    win.requestActive();
                }
            }
        }
    }

    /**
     * Show the wizard for loading the keystore file
     *
     * @param prevKeyStoreName
     * @param kshbh
     */
    private boolean showWizard(File preSelectedKeyStoreFile, String prevKeyStoreName, KeyStoreBeanHistory ksbh) {
        this.wizardDescriptor = new WizardDescriptor(getPanels());

        if (preSelectedKeyStoreFile != null) {
            this.wizardDescriptor.putProperty( "keyStoreFile", preSelectedKeyStoreFile );
        }

        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
        this.wizardDescriptor.setTitleFormat(new MessageFormat("{0}"));
        this.wizardDescriptor.setTitle( NbBundle.getMessage(LoadKeyStoreAction.class, "TTL_LoadKeyStoreYour" ) );

        //this.wizardDescriptor.putProperty()

        this.wizardDescriptor.putProperty( "currentDirectoryOrFile", prevKeyStoreName );
        this.wizardDescriptor.putProperty( "keyStoreBeanHistory", ksbh );

        final Dialog dialog = DialogDisplayer.getDefault().createDialog(wizardDescriptor);
        dialog.setVisible(true);
        dialog.toFront();

        boolean cancelled = this.wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;
        return cancelled;
    }

    /**
     * Initialize panels representing individual wizard's steps and sets
     * various properties for them influencing wizard appearance.
     */
    private WizardDescriptor.Panel[] getPanels() {
        if (panels == null) {
            panels = new WizardDescriptor.Panel[] {
                new SelectKeyStoreWizardPanel(),
                new EnterKeyStorePasswordWizardPanel()
            };
            String[] steps = new String[panels.length];
            for (int i = 0; i < panels.length; i++) {
                Component c = panels[i].getComponent();
                // Default step name to component name of panel. Mainly useful
                // for getting the name of the target chooser to appear in the
                // list of steps.
                steps[i] = c.getName();
                if (c instanceof JComponent) { // assume Swing components
                    JComponent jc = (JComponent) c;
                    // Sets step number of a component
                    jc.putClientProperty("WizardPanel_contentSelectedIndex", Integer.valueOf(i));
                    // Sets steps names for a panel
                    jc.putClientProperty("WizardPanel_contentData", steps);
                    // Turn on subtitle creation on each step
                    jc.putClientProperty("WizardPanel_autoWizardStyle", Boolean.TRUE);
                    // Show steps on the left side with the image on the background
                    jc.putClientProperty("WizardPanel_contentDisplayed", Boolean.TRUE);
                    // Turn on numbering of all steps
                    jc.putClientProperty("WizardPanel_contentNumbered", Boolean.TRUE);
                }
            }
        }
        return panels;
    }

    public String getName() {
        return NbBundle.getMessage(LoadKeyStoreAction.class, "ACT_LoadKeyStore" );
    }

    public String iconResource() {
        return "org/huber/keytool/images/password-open16x16.png";
    }
    protected void initialize() {
        super.initialize();
        // see org.openide.util.actions.SystemAction.iconResource() javadoc for more details
        // putValue("noIconInMenu", Boolean.TRUE);
    }

    public HelpCtx getHelpCtx() {
        return ConstantsHelper.getHelpCtx();
    }

    protected boolean asynchronous() {
        return false;
    }

}

