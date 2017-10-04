/*
 * LocaleNumberPanel.java
 *
 * Created on 20. Mai 2005, 20:35
 */
package org.huberb.localenb.ui;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;

/**
 * A panel for formatting a number.
 *
 * @author HuberB1
 */
public class LocaleNumberPanel extends javax.swing.JPanel implements FormatCommandInterface {

    private static final long serialVersionUID = 20060131123500L;

    /**
     * Creates new form LocaleNumberPanel
     */
    public LocaleNumberPanel() {
        initComponents();
        //this.numberPatternComboBox.setModel( ComboBoxModelFactory.createNumberPatternComboBoxModel());
        final Double defaultDouble = Double.valueOf(12345.9876);
        Double value = defaultDouble;
        Double min = Double.valueOf(-Double.MAX_VALUE);
        Double max = Double.valueOf(Double.MAX_VALUE);

        final SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(value, min, max, Double.valueOf(0.001));
        this.numberObjectSpinner.setModel(spinnerNumberModel);

    }

    /**
     * Build the formatting result.
     *
     * @param selectedLocale for formatting use this locale
     */
    @Override
    public void format(Locale selectedLocale) {
        final String pattern = (String) this.numberPatternComboBox.getSelectedItem();
        final Number number = (Number) this.numberObjectSpinner.getModel().getValue();
        double numberAsDouble = number.doubleValue();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(selectedLocale);
        NumberFormat numberFormat = new DecimalFormat(pattern, symbols);
        String result = numberFormat.format(numberAsDouble);
        this.numberResultTextField.setText(result);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        numberLabel = new javax.swing.JLabel();
        numberObjectSpinner = new javax.swing.JSpinner();
        numberPatternLabel = new javax.swing.JLabel();
        numberPatternComboBox = new javax.swing.JComboBox();
        numberResultLabel = new javax.swing.JLabel();
        numberResultTextField = new javax.swing.JTextField();

        numberLabel.setDisplayedMnemonic('N');
        numberLabel.setLabelFor(numberObjectSpinner);
        numberLabel.setText(org.openide.util.NbBundle.getMessage(LocaleNumberPanel.class, "LocaleNumberPanel.numberLabel.text")); // NOI18N

        numberPatternLabel.setDisplayedMnemonic('P');
        numberPatternLabel.setLabelFor(numberPatternComboBox);
        numberPatternLabel.setText(org.openide.util.NbBundle.getMessage(LocaleNumberPanel.class, "LocaleNumberPanel.numberPatternLabel.text")); // NOI18N

        numberPatternComboBox.setEditable(true);
        numberPatternComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                localeNumberBeanActionHandler(evt);
            }
        });

        numberResultLabel.setDisplayedMnemonic('o');
        numberResultLabel.setLabelFor(numberResultTextField);
        numberResultLabel.setText(org.openide.util.NbBundle.getMessage(LocaleNumberPanel.class, "LocaleNumberPanel.numberResultLabel.text")); // NOI18N

        numberResultTextField.setEditable(false);
        numberResultTextField.setText(org.openide.util.NbBundle.getMessage(LocaleNumberPanel.class, "LocaleNumberPanel.numberResultTextField.text")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(numberLabel)
                    .add(numberPatternLabel)
                    .add(numberResultLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(numberObjectSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 155, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(numberResultTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .add(numberPatternComboBox, 0, 202, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(numberLabel)
                    .add(numberObjectSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(numberPatternLabel)
                    .add(numberPatternComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(numberResultLabel)
                    .add(numberResultTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void localeNumberBeanActionHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_localeNumberBeanActionHandler
//        if (this.localeNumberBean != null) {
//            JComboBox comboBox = (JComboBox)evt.getSource();
//            final String numberPattern = (String)comboBox.getSelectedItem();
//            this.localeNumberBean.setNumberPattern( numberPattern );
//        }
    }//GEN-LAST:event_localeNumberBeanActionHandler

//    public static class LocaleNumberBeanChangeListener implements ChangeListener {
//        private LocaleNumberBean localeNumberBean;
//        public LocaleNumberBeanChangeListener( LocaleNumberBean localeNumberBean ) {
//            this.localeNumberBean = localeNumberBean;
//        }
//        public void stateChanged( ChangeEvent evt ) {
//            SpinnerModel model = (SpinnerModel)evt.getSource();
//            Number newNumber = (Number)model.getValue();
//            this.localeNumberBean.setNumber( newNumber );
//        }
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel numberLabel;
    private javax.swing.JSpinner numberObjectSpinner;
    private javax.swing.JComboBox numberPatternComboBox;
    private javax.swing.JLabel numberPatternLabel;
    private javax.swing.JLabel numberResultLabel;
    private javax.swing.JTextField numberResultTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setPatterns(String[] newPatterns) {
        final DefaultComboBoxModel dcbm = (DefaultComboBoxModel) this.numberPatternComboBox.getModel();
        Object selectedItem = dcbm.getSelectedItem();

        // (1) remove all elements from the ComboBoxModel
        dcbm.removeAllElements();

        // (2) add all new elements to the ComboBoxModel
        for (int i = 0; i < newPatterns.length; i++) {
            final String newPattern = newPatterns[i];
            dcbm.insertElementAt(newPattern, i);
        }
        if (selectedItem == null) {
            selectedItem = dcbm.getElementAt(0);
        }
        dcbm.setSelectedItem(selectedItem);
    }

    @Override
    public Object getState() {
        //super.writeExternal(oo);

        final String pattern = (String) this.numberPatternComboBox.getSelectedItem();
        final Number number = (Number) this.numberObjectSpinner.getModel().getValue();
        final Object[] state = new Object[]{
            pattern,
            number,};
        return state;
    }

    @Override
    public void setState(Object newState) {
        Object[] state = (Object[]) newState;
        final String pattern = (String) state[0];
        final Number number = (Number) state[1];

        this.numberPatternComboBox.setSelectedItem(pattern);
        this.numberObjectSpinner.getModel().setValue(number);
    }
}