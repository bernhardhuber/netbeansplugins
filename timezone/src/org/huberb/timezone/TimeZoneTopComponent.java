package org.huberb.timezone;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import static org.huberb.timezone.TimeZoneTopComponent.findInstance;
import org.huberb.timezone.helper.LabelTimeZoneBean;
import org.huberb.timezone.helper.TimeZoneHelper;
import org.openide.ErrorManager;
import org.openide.util.HelpCtx;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
final class TimeZoneTopComponent extends TopComponent {
    
    private final TimeZoneHelper timeZoneHelper;
    private static final long serialVersionUID = 1L;
    
    private static TimeZoneTopComponent instance;
    /** path to the icon used by the component and its open action */
    public static final String ICON_RESOURCE = "org/huberb/timezone/images/timezone.png";
    
    private static final String PREFERRED_ID = "TimeZoneTopComponent";
    
    private TimeZoneTopComponent() {
        this.timeZoneHelper = new TimeZoneHelper();
        
        initComponents();
        setName(NbBundle.getMessage(TimeZoneTopComponent.class, "CTL_TimeZoneTopComponent"));
        setToolTipText(NbBundle.getMessage(TimeZoneTopComponent.class, "HINT_TimeZoneTopComponent"));
        setIcon(ImageUtilities.loadImage(ICON_RESOURCE, true));
        
        // install the tool tip listener
        final TimeZoneToolTipActionListener tzttal = new TimeZoneToolTipActionListener();
        this.targetComboBox.addActionListener( tzttal );
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        toolBar = new javax.swing.JToolBar();
        convertButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        sourceLabel = new javax.swing.JLabel();
        sourceFormattedTextField = new javax.swing.JFormattedTextField();
        sourcePatternLabel = new javax.swing.JLabel();
        sourcePatternComboBox = new javax.swing.JComboBox();
        sourceSeparator = new javax.swing.JSeparator();
        targetLabel = new javax.swing.JLabel();
        targetComboBox = new javax.swing.JComboBox();
        defaultTimeZoneButton = new javax.swing.JButton();
        targetPatternLabel = new javax.swing.JLabel();
        targetPatternComboBox = new javax.swing.JComboBox();
        targetSeparator = new javax.swing.JSeparator();
        resultLabel = new javax.swing.JLabel();
        resultTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        convertButton.setMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(TimeZoneTopComponent.class, "IDX_Convert")));
        convertButton.setText(org.openide.util.NbBundle.getMessage(TimeZoneTopComponent.class, "LBL_Convert")); // NOI18N
        convertButton.setToolTipText(org.openide.util.NbBundle.getMessage(TimeZoneTopComponent.class, "TTP_Convert")); // NOI18N
        convertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertActionHandler(evt);
            }
        });

        toolBar.add(convertButton);

        add(toolBar, java.awt.BorderLayout.NORTH);

        sourceLabel.setDisplayedMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(TimeZoneTopComponent.class, "IDX_Source")));
        sourceLabel.setFont(new java.awt.Font("Tahoma", 0, 11));
        sourceLabel.setLabelFor(sourceFormattedTextField);
        sourceLabel.setText(org.openide.util.NbBundle.getMessage(TimeZoneTopComponent.class, "LBL_Source")); // NOI18N

        sourceFormattedTextField.setText("2006-03-03 09:55:00 CET +0100");

        sourcePatternLabel.setDisplayedMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(TimeZoneTopComponent.class, "IDX_SourcePattern")));
        sourcePatternLabel.setText(org.openide.util.NbBundle.getMessage(TimeZoneTopComponent.class, "LBL_SourcePattern")); // NOI18N

        sourcePatternComboBox.setEditable(true);
        sourcePatternComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "yyyy-MM-dd HH:mm:ss z Z" }));

        targetLabel.setDisplayedMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(TimeZoneTopComponent.class, "IDX_Target")));
        targetLabel.setText(org.openide.util.NbBundle.getMessage(TimeZoneTopComponent.class, "LBL_Target")); // NOI18N

        targetComboBox.setModel(this.timeZoneHelper.getTimeZoneComboBoxModel());

        defaultTimeZoneButton.setMnemonic('D');
        defaultTimeZoneButton.setText("Default TimeZone");
        defaultTimeZoneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultTimeZoneButtonActionPerformed(evt);
            }
        });

        targetPatternLabel.setDisplayedMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(TimeZoneTopComponent.class, "IDX_TargetPattern")));
        targetPatternLabel.setText(org.openide.util.NbBundle.getMessage(TimeZoneTopComponent.class, "LBL_TargetPattern")); // NOI18N

        targetPatternComboBox.setEditable(true);
        targetPatternComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "yyyy-MM-dd HH:mm:ss z Z", "EEEE, MMMM dd yyyy, HH:mm:ss z Z" }));

        resultLabel.setDisplayedMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(TimeZoneTopComponent.class, "IDX_Result")));
        resultLabel.setText(org.openide.util.NbBundle.getMessage(TimeZoneTopComponent.class, "LBL_Result")); // NOI18N

        resultTextField.setEditable(false);

        jButton1.setMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(TimeZoneTopComponent.class, "IDX_Now")));
        jButton1.setText(org.openide.util.NbBundle.getMessage(TimeZoneTopComponent.class, "LBL_Now")); // NOI18N
        jButton1.setToolTipText(org.openide.util.NbBundle.getMessage(TimeZoneTopComponent.class, "TTP_Now")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleNowAction(evt);
            }
        });

        org.jdesktop.layout.GroupLayout mainPanelLayout = new org.jdesktop.layout.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(mainPanelLayout.createSequentialGroup()
                        .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, resultLabel)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, targetPatternLabel)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, targetLabel)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, sourcePatternLabel)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, sourceLabel))
                        .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(mainPanelLayout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(sourcePatternComboBox, 0, 194, Short.MAX_VALUE)
                                    .add(mainPanelLayout.createSequentialGroup()
                                        .add(sourceFormattedTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jButton1))))
                            .add(mainPanelLayout.createSequentialGroup()
                                .add(4, 4, 4)
                                .add(resultTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                            .add(mainPanelLayout.createSequentialGroup()
                                .add(4, 4, 4)
                                .add(targetPatternComboBox, 0, 194, Short.MAX_VALUE))
                            .add(mainPanelLayout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(targetComboBox, 0, 71, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(defaultTimeZoneButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, targetSeparator, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, sourceSeparator, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(sourceLabel)
                    .add(sourceFormattedTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(sourcePatternLabel)
                    .add(sourcePatternComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(sourceSeparator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(targetLabel)
                    .add(targetComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(defaultTimeZoneButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(targetPatternComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(targetPatternLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(targetSeparator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(resultLabel)
                    .add(resultTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        add(mainPanel, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Set the source date and time to the current date and time
     */
    private void handleNowAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_handleNowAction
        
        final Date now = new Date();
        final String pattern = (String)this.sourcePatternComboBox.getSelectedItem();
        if (pattern != null) {
            final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            
            final String nowFormatted = sdf.format(now);
            this.sourceFormattedTextField.setText( nowFormatted);
        }
        
    }//GEN-LAST:event_handleNowAction
    
    /**
     * Set the default timezone
     */
    private void defaultTimeZoneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defaultTimeZoneButtonActionPerformed
        LabelTimeZoneBean ltzb = this.timeZoneHelper.getDefaultTimeZoneIds();
        targetComboBox.setSelectedItem(ltzb);
    }//GEN-LAST:event_defaultTimeZoneButtonActionPerformed

    /**
     * Convert the source date to the target date
     */
    private void convertActionHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convertActionHandler
        
        // get all the input data
        final String source = this.sourceFormattedTextField.getText();
        final String sourcePattern = (String)this.sourcePatternComboBox.getSelectedItem();
        this.timeZoneHelper.setSourcePattern( sourcePattern );
        final Date sourceDate = this.timeZoneHelper.convertToDate( source );
        
        final LabelTimeZoneBean target = (LabelTimeZoneBean)this.targetComboBox.getSelectedItem();
        // get the target time zone
        final TimeZone tz = target.getValue();
        // get the target pattern
        final String targetPattern = (String)this.targetPatternComboBox.getSelectedItem();
        this.timeZoneHelper.setTargetPattern( targetPattern );
        
        // format result, and set result text field
        String formatDateResult = "";
        if (sourceDate != null && tz != null) {
            // calculate the result
            formatDateResult = this.timeZoneHelper.formatDate( sourceDate, tz );
            this.resultTextField.setText( formatDateResult );
        }
        
        // write detail info to output window
        {
            final StringBuffer sb2 = new StringBuffer();
            sb2.append( this.timeZoneHelper.formatResult( source, tz, formatDateResult ) );
            
            final String resultOffsets = this.timeZoneHelper.timeZoneOffsets( sourceDate, tz );
            sb2.append( resultOffsets );
            sb2.append( "\n" );
            
            final String tabName = NbBundle.getMessage( TimeZoneTopComponent.class, "OpenIDE-Module-Name");
            final InputOutput io = IOProvider.getDefault().getIO(tabName, false);
            final OutputWriter ow = io.getOut();
            ow.print( sb2.toString() );
        }
    }//GEN-LAST:event_convertActionHandler
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton convertButton;
    private javax.swing.JButton defaultTimeZoneButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JTextField resultTextField;
    private javax.swing.JFormattedTextField sourceFormattedTextField;
    private javax.swing.JLabel sourceLabel;
    private javax.swing.JComboBox sourcePatternComboBox;
    private javax.swing.JLabel sourcePatternLabel;
    private javax.swing.JSeparator sourceSeparator;
    private javax.swing.JComboBox targetComboBox;
    private javax.swing.JLabel targetLabel;
    private javax.swing.JComboBox targetPatternComboBox;
    private javax.swing.JLabel targetPatternLabel;
    private javax.swing.JSeparator targetSeparator;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link findInstance}.
     */
    public static synchronized TimeZoneTopComponent getDefault() {
        if (instance == null) {
            instance = new TimeZoneTopComponent();
        }
        return instance;
    }
    
    /**
     * Obtain the TimeZoneTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized TimeZoneTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            ErrorManager.getDefault().log(ErrorManager.WARNING, "Cannot find TimeZone component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof TimeZoneTopComponent) {
            return (TimeZoneTopComponent)win;
        }
        ErrorManager.getDefault().log(ErrorManager.WARNING, "There seem to be multiple components with the '" + PREFERRED_ID + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }
    
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }
    
    public void componentOpened() {
        // TODO add custom code on component opening
    }
    
    public void componentClosed() {
        // TODO add custom code on component closing
    }
    
    /** replaces this in object stream */
    public Object writeReplace() {
        return new ResolvableHelper();
    }
    
    protected String preferredID() {
        return PREFERRED_ID;
    }
    
    public HelpCtx getHelpCtx() {
        HelpCtx retValue = new HelpCtx("org.huberb.timezone.about");
        
        retValue = super.getHelpCtx();
        return retValue;
    }
        
    final static class ResolvableHelper implements Serializable {
        private static final long serialVersionUID = 1L;
        public Object readResolve() {
            return TimeZoneTopComponent.getDefault();
        }
    }

}
