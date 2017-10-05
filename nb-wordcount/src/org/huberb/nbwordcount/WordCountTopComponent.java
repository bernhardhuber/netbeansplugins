package org.huberb.nbwordcount;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import static org.huberb.nbwordcount.WordCountTopComponent.findInstance;

import org.huberb.nbwordcount.model.WordCountTableModel;
import org.openide.ErrorManager;
import org.openide.util.HelpCtx;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;
import org.openide.util.io.NbMarshalledObject;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays characters, words, and lines of selected nodes.
 */
final class WordCountTopComponent extends TopComponent {
    private static final long serialVersionUID = 1L;
    
    private static WordCountTopComponent instance;
    
    private transient WordCountTableModel wordCountTableModel;
    
    /** path to the icon used by the component and its open action */
    public static final String ICON_PATH = "org/huberb/nbwordcount/images/wordCount.gif";
    
    private static final String PREFERRED_ID = "WordCountTopComponent";
    
    private WordCountTopComponent() {
        initComponents();
        
        //----
        this.wordCountTableModel = (WordCountTableModel)this.wordCountResultTable.getModel();
        DefaultNumberRenderer defaultNumberRenderer = new DefaultNumberRenderer();
        this.wordCountResultTable.setDefaultRenderer( Double.class, defaultNumberRenderer );
        this.wordCountResultTable.setDefaultRenderer( Float.class, defaultNumberRenderer );
        this.wordCountResultTable.setDefaultRenderer( Number.class, defaultNumberRenderer );
        
        //----
        javax.swing.JToolBar myToolBar = SystemAction.createToolbarPresenter(new SystemAction[] {
            ExportDataAction.get( ExportDataAction.class ),
            WordCountingAction.get( WordCountingAction.class ),
        });
        add(myToolBar, java.awt.BorderLayout.NORTH);
        
        //----
        setName(NbBundle.getMessage(WordCountTopComponent.class, "CTL_WordCountTopComponent"));
        setToolTipText(NbBundle.getMessage(WordCountTopComponent.class, "HINT_WordCountTopComponent"));
        setIcon(ImageUtilities.loadImage(ICON_PATH, true));
    }
    
    public void setCounters(Long[] counters ) {
        this.wordCountTableModel.setCounters( counters );
    }
    public void setNamesOfCounters( int countOfFiles, String csvNames ) {
        this.countOfFilesTextField.setText( String.valueOf(countOfFiles) );
        this.processedNamesTextPane.setText( csvNames );
    }
    
    public WordCountTableModel getWordCountTableModel() {
        return this.wordCountTableModel;
    }
    public String getProcessedNames() {
        return this.processedNamesTextPane.getText();
    }
    public String getCountOfProcessedNames() {
        return this.countOfFilesTextField.getText();
    }
        
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        splitPane = new javax.swing.JSplitPane();
        filesPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        processedNamesScrollPane = new javax.swing.JScrollPane();
        processedNamesTextPane = new javax.swing.JTextPane();
        countOfFilesLabel = new javax.swing.JLabel();
        countOfFilesTextField = new javax.swing.JTextField();
        countersPanel = new javax.swing.JPanel();
        countersLabel = new javax.swing.JLabel();
        wordCountResultScrollPane = new javax.swing.JScrollPane();
        wordCountResultTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        splitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        org.openide.awt.Mnemonics.setLocalizedText(nameLabel, org.openide.util.NbBundle.getMessage(WordCountTopComponent.class, "LBL_Name")); // NOI18N

        processedNamesTextPane.setEditable(false);
        processedNamesScrollPane.setViewportView(processedNamesTextPane);

        countOfFilesLabel.setLabelFor(countOfFilesTextField);
        org.openide.awt.Mnemonics.setLocalizedText(countOfFilesLabel, org.openide.util.NbBundle.getMessage(WordCountTopComponent.class, "LBL_COUNT_OF_FILES")); // NOI18N

        countOfFilesTextField.setEditable(false);

        org.jdesktop.layout.GroupLayout filesPanelLayout = new org.jdesktop.layout.GroupLayout(filesPanel);
        filesPanel.setLayout(filesPanelLayout);
        filesPanelLayout.setHorizontalGroup(
            filesPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, filesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(filesPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, countOfFilesLabel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, nameLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(filesPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(processedNamesScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .add(countOfFilesTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                .addContainerGap())
        );
        filesPanelLayout.setVerticalGroup(
            filesPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(filesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(filesPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(countOfFilesLabel)
                    .add(countOfFilesTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(filesPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(nameLabel)
                    .add(processedNamesScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                .addContainerGap())
        );
        splitPane.setLeftComponent(filesPanel);

        org.openide.awt.Mnemonics.setLocalizedText(countersLabel, org.openide.util.NbBundle.getMessage(WordCountTopComponent.class, "LBL_Counters")); // NOI18N

        wordCountResultTable.setModel(new WordCountTableModel());
        wordCountResultScrollPane.setViewportView(wordCountResultTable);

        org.jdesktop.layout.GroupLayout countersPanelLayout = new org.jdesktop.layout.GroupLayout(countersPanel);
        countersPanel.setLayout(countersPanelLayout);
        countersPanelLayout.setHorizontalGroup(
            countersPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(countersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(countersLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(wordCountResultScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addContainerGap())
        );
        countersPanelLayout.setVerticalGroup(
            countersPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(countersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(countersPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(wordCountResultScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                    .add(countersLabel))
                .addContainerGap())
        );
        splitPane.setRightComponent(countersPanel);

        add(splitPane, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel countOfFilesLabel;
    private javax.swing.JTextField countOfFilesTextField;
    private transient javax.swing.JLabel countersLabel;
    private javax.swing.JPanel countersPanel;
    private javax.swing.JPanel filesPanel;
    private transient javax.swing.JLabel nameLabel;
    private transient javax.swing.JScrollPane processedNamesScrollPane;
    private transient javax.swing.JTextPane processedNamesTextPane;
    private javax.swing.JSplitPane splitPane;
    private transient javax.swing.JScrollPane wordCountResultScrollPane;
    private transient javax.swing.JTable wordCountResultTable;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link findInstance}.
     */
    public static synchronized WordCountTopComponent getDefault() {
        if (instance == null) {
            instance = new WordCountTopComponent();
        }
        return instance;
    }
    
    private static synchronized void setDefault( WordCountTopComponent newInstance ) {
        if (instance == null) {
            instance = newInstance;
        }
    }
    
    /**
     * Obtain the WordCountTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized WordCountTopComponent findInstance() {
        WordCountTopComponent wctc = null;
        
        final TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            ErrorManager.getDefault().log(ErrorManager.WARNING, "Cannot find WordCount component. It will not be located properly in the window system.");
            wctc = getDefault();
        } else if (win instanceof WordCountTopComponent) {
            wctc = (WordCountTopComponent)win;
        } else {
            ErrorManager.getDefault().log(ErrorManager.WARNING, "There seem to be multiple components with the '" + PREFERRED_ID + "' ID. That is a potential source of errors and unexpected behavior.");
            wctc = getDefault();
        }
        return wctc;
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
    
    protected String preferredID() {
        return PREFERRED_ID;
    }
    
    public HelpCtx getHelpCtx() {
        return ConstantsHelper.getHelpCtx();
    }
    
    //-------------------------------------------------------------
    // state serialization
    //-------------------------------------------------------------
    public Object readResolve() throws ObjectStreamException {
        setDefault( this );
        return getDefault();
    }
    
    public void writeExternal(ObjectOutput oo) throws IOException {
        super.writeExternal(oo);
        
        final Object[] state = new Object[] {
            Integer.valueOf(this.splitPane.getDividerLocation()),
        };
        Object writeObject;
        
        try {
            writeObject = new NbMarshalledObject(state);
        } catch (Exception e) {
            ErrorManager.getDefault().notify(ErrorManager.WARNING, e);
            writeObject = null;
        }
        oo.writeObject(writeObject);
    }
    
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        super.readExternal(oi);
        
        final NbMarshalledObject readObject =(NbMarshalledObject)oi.readObject();
        if (readObject != null) {
            try {
                Object[] state = (Object[])readObject.get();
                if (state == null) {
                    return ;
                }
                final int stateLength = state.length;
                if (stateLength > 0 && state[0] instanceof Integer) {
                    int dividerLocation = ((Integer)state[0]).intValue();
                    this.splitPane.setDividerLocation( dividerLocation );
                }
            } catch (Exception e) {
                ErrorManager.getDefault().notify(ErrorManager.WARNING, e);
            }
        }
    }
    
}
