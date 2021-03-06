/*
 * DigestPanel.java
 *
 * Created on 06. Oktober 2005, 22:38
 */

package org.huberb.digestnb.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import org.huberb.digestnb.option.DigestOption;
import org.huberb.digestnb.helper.Digest;
import org.huberb.digestnb.helper.DigestActionCommand;
import org.huberb.digestnb.helper.DigestActionCommand.EncodingMode;
import org.huberb.digestnb.helper.ServiceTypeHelper;
/**
 *
 * @author  HuberB1
 */
public class DigestPanel extends javax.swing.JPanel implements PropertyChangeListener {
    private final static Logger logger = Logger.getLogger( DigestPanel.class.getName() );
    
    private static final String[] algorithms;
    static {
        // retrieve the supported algorithms
        algorithms = ServiceTypeHelper.getMessageDigestImpls();
        // sort it
        Arrays.sort(algorithms);
    }
    
    /** Creates new form DigestPanel */
    public DigestPanel() {
        initComponents();
        // moved to initComponents
        // this.algorithmComboBox.setModel( new DefaultComboBoxModel(algorithms));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        encodingButtonGroup = new javax.swing.ButtonGroup();
        toolBar = new javax.swing.JToolBar();
        digestButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        encodingLabel = new javax.swing.JLabel();
        encodingHexRadioButton = new javax.swing.JRadioButton();
        encodingBase64RadioButton = new javax.swing.JRadioButton();
        algorithmLabel = new javax.swing.JLabel();
        algorithmComboBox = new javax.swing.JComboBox();
        secretLabel = new javax.swing.JLabel();
        secretTextField = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        resultScrollPane = new javax.swing.JScrollPane();
        resultTextArea = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        digestButton.setMnemonic('D');
        digestButton.setText(org.openide.util.NbBundle.getMessage(DigestPanel.class, "CTL_DigestButton")); // NOI18N
        digestButton.setToolTipText(org.openide.util.NbBundle.getMessage(DigestPanel.class, "HINT_DigestAction")); // NOI18N
        digestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleDigestAction(evt);
            }
        });
        toolBar.add(digestButton);

        add(toolBar, java.awt.BorderLayout.NORTH);

        encodingLabel.setDisplayedMnemonic('E');
        encodingLabel.setText(org.openide.util.NbBundle.getMessage(DigestPanel.class, "CTL_Encoding")); // NOI18N

        encodingButtonGroup.add(encodingHexRadioButton);
        encodingHexRadioButton.setMnemonic('H');
        encodingHexRadioButton.setText(org.openide.util.NbBundle.getMessage(DigestPanel.class, "CTL_EncodingHex")); // NOI18N
        encodingHexRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        encodingHexRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));

        encodingButtonGroup.add(encodingBase64RadioButton);
        encodingBase64RadioButton.setMnemonic('B');
        encodingBase64RadioButton.setText(org.openide.util.NbBundle.getMessage(DigestPanel.class, "CTL_EncodingBase64")); // NOI18N
        encodingBase64RadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        encodingBase64RadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));

        algorithmLabel.setDisplayedMnemonic('A');
        algorithmLabel.setLabelFor(algorithmComboBox);
        algorithmLabel.setText(org.openide.util.NbBundle.getMessage(DigestPanel.class, "CTL_Algorithm")); // NOI18N

        algorithmComboBox.setModel(new DefaultComboBoxModel(algorithms));

        secretLabel.setDisplayedMnemonic('S');
        secretLabel.setLabelFor(secretTextField);
        secretLabel.setText(org.openide.util.NbBundle.getMessage(DigestPanel.class, "CTL_Secret")); // NOI18N

        secretTextField.setText("secret");

        resultTextArea.setColumns(20);
        resultTextArea.setEditable(false);
        resultTextArea.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        resultTextArea.setRows(5);
        resultScrollPane.setViewportView(resultTextArea);

        org.jdesktop.layout.GroupLayout mainPanelLayout = new org.jdesktop.layout.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .add(resultScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .add(mainPanelLayout.createSequentialGroup()
                        .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(secretLabel)
                            .add(encodingLabel)
                            .add(algorithmLabel))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(secretTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .add(algorithmComboBox, 0, 266, Short.MAX_VALUE)))
                    .add(mainPanelLayout.createSequentialGroup()
                        .add(61, 61, 61)
                        .add(encodingBase64RadioButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(encodingHexRadioButton)
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(secretTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(secretLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(algorithmLabel)
                    .add(algorithmComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(encodingLabel)
                    .add(encodingBase64RadioButton)
                    .add(encodingHexRadioButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(resultScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(mainPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    private void handleDigestAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_handleDigestAction
        final String NEWLINE = System.getProperty( "line.separator", "\n" );
        final StringBuffer sb = new StringBuffer();
        sb.append( this.resultTextArea.getText() );
        if (sb.length() > 0) {
            sb.append(NEWLINE);
        }
        final StringBuffer digestMessage = buildDigest();
        sb.append( digestMessage );
        this.resultTextArea.setText( sb.toString() );
        
    }//GEN-LAST:event_handleDigestAction
    
    /**
     * Build digest
     *
     */
    protected StringBuffer buildDigest() {
        final DigestActionCommand dac = getDigestActionCommand();
        dac.setMessagePattern( this.getMessagePattern() );
        final StringBuffer sb = dac.buildDigest();
        return sb;
    }
    
    public DigestActionCommand getDigestActionCommand() {
        final String secret = this.secretTextField.getText();
        final String algorithm = this.algorithmComboBox.getSelectedItem().toString();
        EncodingMode encodingMode = EncodingMode.Hex;
        
        if (this.encodingHexRadioButton.isSelected()) {
            encodingMode = EncodingMode.Hex;
        } else if (this.encodingBase64RadioButton.isSelected()) {
            encodingMode = EncodingMode.Base64;
        }
        DigestActionCommand dac = new DigestActionCommand( encodingMode, algorithm, secret );
        return dac;
    }
    
    public void setDigestActionCommand(DigestActionCommand dac) {
        this.secretTextField.setText( dac.getSecret() );
        this.algorithmComboBox.setSelectedItem( dac.getAlgorithm() );
        this.encodingHexRadioButton.setSelected( dac.getEncodingMode() == EncodingMode.Hex );
        this.encodingBase64RadioButton.setSelected( dac.getEncodingMode() == EncodingMode.Base64 );
    }
        
    /**
     * The default digest message pattern
     */
    private final static String DEFAULT_MESSAGE_PATTERN = "secret {0} [{1} (algorigthm), {2} (encoding)] : {3}";
    
    protected String formatMessage(Object []args) {
        final String theMessagePattern = this.messagePattern != null ?
            this.messagePattern : DEFAULT_MESSAGE_PATTERN;
        final String message = MessageFormat.format( theMessagePattern, args );
        
        return message;
    }
    
    /**
     * Handle a property change of digest message pattern.
     *
     * @evt the property change event sent if the user changes a the message pattern
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt != null && evt.getPropertyName() != null && evt.getNewValue() != null) {
            if (evt.getPropertyName().equals( DigestOption.PROPERTY_DIGEST_FORMAT_MESSAGE)) {
                String newMessagePattern = (String)evt.getNewValue();
                setMessagePattern( newMessagePattern );
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox algorithmComboBox;
    private javax.swing.JLabel algorithmLabel;
    private javax.swing.JButton digestButton;
    private javax.swing.JRadioButton encodingBase64RadioButton;
    private javax.swing.ButtonGroup encodingButtonGroup;
    private javax.swing.JRadioButton encodingHexRadioButton;
    private javax.swing.JLabel encodingLabel;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane resultScrollPane;
    private javax.swing.JTextArea resultTextArea;
    private javax.swing.JLabel secretLabel;
    private javax.swing.JTextField secretTextField;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Holds value of property messagePattern.
     */
    private String messagePattern;
    
    /**
     * Getter for property messagePattern.
     * @return Value of property messagePattern.
     */
    public String getMessagePattern() {
        return this.messagePattern;
    }
    
    /**
     * Setter for property messagePattern.
     * @param messagePattern New value of property messagePattern.
     */
    public void setMessagePattern(String messagePattern) {
        this.messagePattern = messagePattern;
    }
    
}
