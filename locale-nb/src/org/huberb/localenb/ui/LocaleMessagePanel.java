/*
 * LocaleMessagePanel.java
 *
 * Created on 20. Mai 2005, 20:35
 */

package org.huberb.localenb.ui;

import java.text.Format;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.TableColumnModel;

/**
 * A panel for formatting a message.
 *
 * @author  HuberB1
 */
public class LocaleMessagePanel extends javax.swing.JPanel implements FormatCommandInterface {
    private static final long serialVersionUID = 20060131123500L;
    
    /**
     * Creates new form LocaleMessagePanel
     */
    public LocaleMessagePanel() {
        initComponents();
        //this.textPatternComboBox.setModel( ComboBoxModelFactory.createMessagePatternComboBoxModel() );
    }
    
    /**
     * Build the formatting result.
     * @param selectedLocale for formatting use this locale
     */
    @Override
    public void format(Locale selectedLocale) {
        final String pattern = (String)this.textPatternComboBox.getSelectedItem();
        MessageFormat messageFormat = new MessageFormat( pattern, selectedLocale );
        
        final MessageArgsTableModel matm = (MessageArgsTableModel)this.argsTable.getModel();
        Format[] formats = messageFormat.getFormatsByArgumentIndex();
        Object[] args = new Object[formats.length];
        for (int i = 0; i < formats.length; i++ ) {
            Format format = formats[i];
            try {
                args[i] = matm.getArgumentAs(i, format);
            } catch (ParseException ex) {
                // TODO inform user, and continue, or only inform user
                args[i] = null;
            }
        }
        final String result = messageFormat.format( args );
        this.textFormattedObject.setText( result );
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        messageLabel = new javax.swing.JLabel();
        textPatternLabel = new javax.swing.JLabel();
        textPatternComboBox = new javax.swing.JComboBox();
        textFormattedLabel = new javax.swing.JLabel();
        textFormattedObject = new javax.swing.JTextField();
        argsScrollPane = new javax.swing.JScrollPane();
        argsTable = new javax.swing.JTable();

        messageLabel.setDisplayedMnemonic('M');
        messageLabel.setText(org.openide.util.NbBundle.getMessage(LocaleMessagePanel.class, "LocaleMessagePanel.messageLabel.text")); // NOI18N

        textPatternLabel.setDisplayedMnemonic('P');
        textPatternLabel.setLabelFor(textPatternComboBox);
        textPatternLabel.setText(org.openide.util.NbBundle.getMessage(LocaleMessagePanel.class, "LocaleMessagePanel.textPatternLabel.text")); // NOI18N

        textPatternComboBox.setEditable(true);
        textPatternComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                localeMessageBeanActionListener(evt);
            }
        });

        textFormattedLabel.setDisplayedMnemonic('o');
        textFormattedLabel.setText(org.openide.util.NbBundle.getMessage(LocaleMessagePanel.class, "LocaleMessagePanel.textFormattedLabel.text")); // NOI18N

        textFormattedObject.setEditable(false);
        textFormattedObject.setText(org.openide.util.NbBundle.getMessage(LocaleMessagePanel.class, "LocaleMessagePanel.textFormattedObject.text")); // NOI18N

        argsTable.setModel(new MessageArgsTableModel());
        argsScrollPane.setViewportView(argsTable);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(messageLabel)
                            .add(textPatternLabel))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(textPatternComboBox, 0, 450, Short.MAX_VALUE)
                            .add(argsScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)))
                    .add(layout.createSequentialGroup()
                        .add(12, 12, 12)
                        .add(textFormattedLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(textFormattedObject, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(20, 20, 20)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(messageLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 98, Short.MAX_VALUE))
                    .add(argsScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                .add(4, 4, 4)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(textPatternLabel)
                    .add(textPatternComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(textFormattedLabel)
                    .add(textFormattedObject, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void localeMessageBeanActionListener(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_localeMessageBeanActionListener
        //updateLocaleMessageBean();
    }//GEN-LAST:event_localeMessageBeanActionListener
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane argsScrollPane;
    private javax.swing.JTable argsTable;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel textFormattedLabel;
    private javax.swing.JTextField textFormattedObject;
    private javax.swing.JComboBox textPatternComboBox;
    private javax.swing.JLabel textPatternLabel;
    // End of variables declaration//GEN-END:variables
    
    @Override
    public void setPatterns( String[] newPatterns ) {
        final DefaultComboBoxModel dcbm = (DefaultComboBoxModel)this.textPatternComboBox.getModel();
        Object selectedItem = dcbm.getSelectedItem();
        
        // (1) remove all elements from the ComboBoxModel
        dcbm.removeAllElements();
        
        // (2) add all new elements to the ComboBoxModel
        for (int i = 0; i < newPatterns.length; i++ ) {
            final String newPattern = newPatterns[i];
            dcbm.insertElementAt( newPattern, i );
        }
        
        if (selectedItem == null) {
            selectedItem = dcbm.getElementAt(0);
        }
        dcbm.setSelectedItem( selectedItem );
    }
    @Override
    public Object getState() {
        final String pattern = (String)this.textPatternComboBox.getSelectedItem();
        
        final MessageArgsTableModel matm = (MessageArgsTableModel)this.argsTable.getModel();
        
        final TableColumnModel tcm = this.argsTable.getTableHeader().getColumnModel();
        final Integer[] columnWidths = new Integer[tcm.getColumnCount()];
        for (int i = 0; i < columnWidths.length; i++ ) {
            columnWidths[i] = tcm.getColumn(i).getPreferredWidth();
        }
        final Object[] state = new Object[] {
            pattern,
            matm.getArgs(),
            columnWidths,
            //textArg1, textArg2, textArg3,
        };
        return state;
    }
    @Override
    public void setState(Object newState) {
        final Object[] state = (Object[])newState;
        
        if (state.length >= 1) {
            final String pattern = (String)state[0];
            this.textPatternComboBox.setSelectedItem(pattern);
        }
        
        if (state.length >= 2 && state[1] instanceof List) {
            final List<String> args = (List<String>)state[1];
            final MessageArgsTableModel matm = (MessageArgsTableModel)this.argsTable.getModel();
            matm.setArgs( args );
        }
        if (state.length >= 3 && state[2] instanceof Integer[]) {
            Integer[] columnWidths = (Integer[])state[2];
            final TableColumnModel tcm = this.argsTable.getTableHeader().getColumnModel();
            final int iMinLength = Math.min( columnWidths.length, tcm.getColumnCount());
            for (int i = 0; i < iMinLength; i++ ) {
                Integer width = columnWidths[i];
                
                tcm.getColumn(i).setPreferredWidth( width );
            }
        }
    }
}
