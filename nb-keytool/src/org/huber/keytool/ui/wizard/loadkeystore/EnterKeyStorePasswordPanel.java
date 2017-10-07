package org.huber.keytool.ui.wizard.loadkeystore;

import java.io.File;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.huber.keytool.model.KeyStoreBeanFactory;
import org.huber.keytool.settings.KeytoolSettings;

import org.huber.keytool.ui.wizard.ChangeObserverOfWizardPanel;
import org.huber.keytool.ui.wizard.ObserverablePanel;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;
import org.openide.util.NbBundle;

public final class EnterKeyStorePasswordPanel extends JPanel implements ObserverablePanel {
    
    /** Creates new form LoadKeyStoreVisualPanel2 */
    public EnterKeyStorePasswordPanel() {
        initComponents();
    }
    
    public String getName() {
        return NbBundle.getMessage(EnterKeyStorePasswordPanel.class, "NAME_EnterKeyStorePasswordPanel");
    }
    
    public void bind(ChangeObserverOfWizardPanel changeObserver) {
        changeObserver.bindDocumentListener( this.keyStorePasswordField.getDocument() );
    }
    
    public ObserverablePanel.ValidUserEntryResult isValidUserEntry() {
        boolean isValid = true;
        final ObserverablePanel.ValidUserEntryResult vuer = new ObserverablePanel.ValidUserEntryResult();
        
        KeytoolSettings keytoolSettings = KeytoolSettings.getDefault();
        final int MIN_LENGTH = keytoolSettings.getMinPasswordLength();
        
        isValid = isValid && (this.keyStorePasswordField.getPassword().length == 0 || this.keyStorePasswordField.getPassword().length >= MIN_LENGTH);
        if (!isValid) {
            final String msg = NbBundle.getMessage( EnterKeyStorePasswordPanel.class, "ERR_INVALID_STORE_PASSWORD", Integer.valueOf(MIN_LENGTH) );
            vuer.setInvalidMessage( msg );
            return vuer;
        }
        
        try {
            final KeyStoreBeanFactory ksbf = new KeyStoreBeanFactory();
            final File keyStoreFile = new File( getSelectedKeyStoreFile() );
            final char[] password = this.keyStorePasswordField.getPassword();
            ksbf.validateKeyStore( keyStoreFile, password );
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null) {
                msg = ex.getMessage();
            }
            if (msg != null) {
                vuer.setInvalidMessage( msg );
                return vuer;
            }
        }
        return vuer;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        keyStoreFileLabel = new JLabel();
        keyStorePasswordLabel = new JLabel();
        keyStoreFileTextField = new JTextField();
        keyStorePasswordField = new JPasswordField();

        keyStoreFileLabel.setLabelFor(keyStoreFileTextField);
        keyStoreFileLabel.setText(NbBundle.getMessage(EnterKeyStorePasswordPanel.class, "LBL_KeyStoreFile"));

        keyStorePasswordLabel.setDisplayedMnemonic(Integer.parseInt(NbBundle.getBundle(EnterKeyStorePasswordPanel.class).getString("IDX_KeyStorePassword")));
        keyStorePasswordLabel.setLabelFor(keyStorePasswordField);
        keyStorePasswordLabel.setText(NbBundle.getMessage(EnterKeyStorePasswordPanel.class, "LBL_KeyStorePassword"));

        keyStoreFileTextField.setEditable(false);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(GroupLayout.LEADING)
                    .add(GroupLayout.TRAILING, keyStorePasswordLabel)
                    .add(GroupLayout.TRAILING, keyStoreFileLabel))
                .addPreferredGap(LayoutStyle.RELATED)
                .add(layout.createParallelGroup(GroupLayout.LEADING)
                    .add(keyStoreFileTextField, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .add(keyStorePasswordField, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(GroupLayout.BASELINE)
                    .add(keyStoreFileLabel)
                    .add(keyStoreFileTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.RELATED)
                .add(layout.createParallelGroup(GroupLayout.BASELINE)
                    .add(keyStorePasswordLabel)
                    .add(keyStorePasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel keyStoreFileLabel;
    private JTextField keyStoreFileTextField;
    private JPasswordField keyStorePasswordField;
    private JLabel keyStorePasswordLabel;
    // End of variables declaration//GEN-END:variables
    
    private final static String EMPTY_STRING = "";
    
    public void reset() {
        this.keyStoreFileTextField.setText(EMPTY_STRING);
        this.keyStorePasswordField.setText(EMPTY_STRING);
    }
    
    void setSelectedKeyStoreFile( String selectedKeyStoreFile ) {
        this.keyStoreFileTextField.setText( selectedKeyStoreFile );
        this.keyStorePasswordField.setText(EMPTY_STRING);
    }
    String getSelectedKeyStoreFile() {
        return this.keyStoreFileTextField.getText( );
    }
    char[] getKeyStorePassword() {
        return this.keyStorePasswordField.getPassword();
    }
}

