package org.huber.keytool;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.security.KeyStore;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.text.html.HTMLEditorKit;
import org.huber.keytool.action.CreateCertReqFileAction;
import org.huber.keytool.action.DeleteKeyAction;
import org.huber.keytool.action.LoadKeyStoreDropTargetListener;
import org.huber.keytool.action.SaveKeyStoreAction;

import org.huber.keytool.model.KeyStoreBean;
import org.huber.keytool.ui.AliasDetailListener;
import org.huber.keytool.ui.AliasDetailListener.DetailMode;
import org.huber.keytool.ui.KeyStoreEntryTableModel;
import org.huber.keytool.action.GenKeyAction;
import org.huber.keytool.action.LoadKeyStoreAction;
import org.huber.keytool.action.NewKeyStoreAction;
import org.huber.keytool.action.StorePasswordAction;
import org.huber.keytool.model.ConstantsHelper;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;
import org.openide.ErrorManager;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.util.actions.SystemAction;
import org.openide.util.io.NbMarshalledObject;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
final public class KeyStoreTopComponent extends TopComponent {
    
    private static final long serialVersionUID = 1L;
    
    private static KeyStoreTopComponent instance;
    /** path to the icon used by the component and its open action */
    private final static String ICON_RESOURCE = "org/huber/keytool/images/password16x16.png";
    
    private static final String PREFERRED_ID = "KeyStoreTopComponent";
    public static final String KEY_STORE_BEAN_PROP_NAME = "keyStoreBean";
    
    private final KeyStoreEntryTableModel ksetm;
    private final AliasDetailListener adl;
    private KeyStoreBean keyStoreBean;
    private KeyStoreBeanHistory ksbh;
    
    private KeyStoreTopComponent() {
        this.keyStoreBean = new KeyStoreBean();
        this.keyStoreBean.setName( "" );
        
        setKeyStoreBeanHistory( new KeyStoreBeanHistory() );
        
        initComponents();
        
        setName(NbBundle.getMessage(KeyStoreTopComponent.class, "CTL_KeyStoreTopComponent"));
        setToolTipText(NbBundle.getMessage(KeyStoreTopComponent.class, "HINT_KeyStoreTopComponent"));
        setIcon(Utilities.loadImage(ICON_RESOURCE, true));
        
        this.detailTextPane.setEditorKit( new HTMLEditorKit() );
        
        this.ksetm = (KeyStoreEntryTableModel)this.aliasesTable.getModel();
        
        //---
        this.adl = new AliasDetailListener( this.detailTextPane, this.ksetm );
        this.aliasesTable.getSelectionModel().addListSelectionListener( this.adl );
        
        //---
        installApplicationStateListener();
        
        //---
        installToolBar();
        
        //---
        LoadKeyStoreDropTargetListener lksdtl = new LoadKeyStoreDropTargetListener();
        DropTarget[] dropTargets = new DropTarget[] {
            new DropTarget(this, lksdtl ),
            new DropTarget(this.nameTextField, lksdtl),
        };
        
    }
    
    private void installApplicationStateListener() {
        final ActionStateListener asl = new ActionStateListener();
        asl.setEnableObjectsIfLoaded( new Object[] {
            SaveKeyStoreAction.get(SaveKeyStoreAction.class),
            StorePasswordAction.get( StorePasswordAction.class),
            GenKeyAction.get(GenKeyAction.class),
            DeleteKeyAction.get(DeleteKeyAction.class),
            CreateCertReqFileAction.get(CreateCertReqFileAction.class),
            
            this.detailVerboseRadioButton,
            this.detailRfcRadioButton,
        });
        this.addPropertyChangeListener( KEY_STORE_BEAN_PROP_NAME, asl );
        
        this.firePropertyChange( KEY_STORE_BEAN_PROP_NAME, null, this.keyStoreBean );
    }
    
    private void installToolBar() {
        final JToolBar toolbar = SystemAction.createToolbarPresenter( new SystemAction[] {
            NewKeyStoreAction.get(NewKeyStoreAction.class),
            LoadKeyStoreAction.get(LoadKeyStoreAction.class),
            SaveKeyStoreAction.get(SaveKeyStoreAction.class),
            StorePasswordAction.get( StorePasswordAction.class),
            GenKeyAction.get(GenKeyAction.class),
            DeleteKeyAction.get(DeleteKeyAction.class),
            CreateCertReqFileAction.get(CreateCertReqFileAction.class),
        });
        if (toolbar != null) {
            toolbar.setFloatable(false);
            this.add( toolbar, BorderLayout.NORTH );
        }
    }
    
    public void setKeyStoreBean( KeyStoreBean ksb ) {
        final KeyStoreBean oldKeyStoreBean = this.keyStoreBean;
        this.keyStoreBean = ksb;
        this.refreshKeyStoreView();
        
        this.firePropertyChange(KEY_STORE_BEAN_PROP_NAME, oldKeyStoreBean, ksb );
    }
    
    public KeyStoreBean getKeyStoreBean() {
        return this.keyStoreBean;
    }
    
    public int[] getSelectedAliases() {
        final int[] selectedRows = this.aliasesTable.getSelectedRows();
        return selectedRows;
    }
    
    public KeyStoreBeanHistory getKeyStoreBeanHistory() {
        return this.ksbh;
    }
    public KeyStoreEntryTableModel getKeyStoreEntryTableModel() {
        return this.ksetm;
    }
    
    public void refreshKeyStoreView() {
        final KeyStore ks = this.keyStoreBean.getKeyStore();
        this.ksetm.setKeyStore( ks );
        
        this.typeTextField.setText( ks.getType() );
        
        final String name = this.keyStoreBean.getName();
        this.nameTextField.setText( name );
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        detailModeButtonGroup = new ButtonGroup();
        aliasesPanel = new JPanel();
        typeLabel = new JLabel();
        typeTextField = new JTextField();
        nameLabel = new JLabel();
        nameTextField = new JTextField();
        aliasesSplitPane = new JSplitPane();
        detailScrollPane = new JScrollPane();
        detailTextPane = new JTextPane();
        aliasesScrollPane = new JScrollPane();
        aliasesTable = new JTable();
        detailVerboseRadioButton = new JRadioButton();
        detailRfcRadioButton = new JRadioButton();

        setLayout(new BorderLayout());

        typeLabel.setDisplayedMnemonic('T');
        typeLabel.setLabelFor(typeTextField);
        typeLabel.setText("Type");

        typeTextField.setEditable(false);

        nameLabel.setDisplayedMnemonic('N');
        nameLabel.setLabelFor(nameTextField);
        nameLabel.setText("Name");

        nameTextField.setEditable(false);

        aliasesSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        detailTextPane.setEditable(false);
        detailScrollPane.setViewportView(detailTextPane);

        aliasesSplitPane.setTopComponent(detailScrollPane);

        aliasesTable.setModel(new KeyStoreEntryTableModel());
        aliasesScrollPane.setViewportView(aliasesTable);

        aliasesSplitPane.setBottomComponent(aliasesScrollPane);

        detailModeButtonGroup.add(detailVerboseRadioButton);
        detailVerboseRadioButton.setMnemonic(Integer.parseInt(NbBundle.getMessage(KeyStoreTopComponent.class, "IDX_Verbose")));
        detailVerboseRadioButton.setSelected(true);
        detailVerboseRadioButton.setText(NbBundle.getMessage(KeyStoreTopComponent.class, "BTN_Verbose"));
        detailVerboseRadioButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        detailVerboseRadioButton.setMargin(new Insets(0, 0, 0, 0));
        detailVerboseRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                detailVerboseModeHandler(evt);
            }
        });

        detailModeButtonGroup.add(detailRfcRadioButton);
        detailRfcRadioButton.setMnemonic(Integer.parseInt(NbBundle.getMessage(KeyStoreTopComponent.class, "IDX_RFC")));
        detailRfcRadioButton.setText(NbBundle.getMessage(KeyStoreTopComponent.class, "BTN_RFC"));
        detailRfcRadioButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        detailRfcRadioButton.setMargin(new Insets(0, 0, 0, 0));
        detailRfcRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                detailRfcModeHandler(evt);
            }
        });

        GroupLayout aliasesPanelLayout = new GroupLayout(aliasesPanel);
        aliasesPanel.setLayout(aliasesPanelLayout);
        aliasesPanelLayout.setHorizontalGroup(
            aliasesPanelLayout.createParallelGroup(GroupLayout.LEADING)
            .add(aliasesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(aliasesPanelLayout.createParallelGroup(GroupLayout.LEADING)
                    .add(aliasesSplitPane, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .add(GroupLayout.TRAILING, aliasesPanelLayout.createSequentialGroup()
                        .add(nameLabel)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(nameTextField, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(typeLabel)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(typeTextField, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                    .add(aliasesPanelLayout.createSequentialGroup()
                        .add(detailVerboseRadioButton)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(detailRfcRadioButton)))
                .addContainerGap())
        );
        aliasesPanelLayout.setVerticalGroup(
            aliasesPanelLayout.createParallelGroup(GroupLayout.LEADING)
            .add(aliasesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(aliasesPanelLayout.createParallelGroup(GroupLayout.BASELINE)
                    .add(nameLabel)
                    .add(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .add(typeLabel)
                    .add(typeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.RELATED)
                .add(aliasesPanelLayout.createParallelGroup(GroupLayout.BASELINE)
                    .add(detailVerboseRadioButton)
                    .add(detailRfcRadioButton))
                .addPreferredGap(LayoutStyle.RELATED)
                .add(aliasesSplitPane, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addContainerGap())
        );
        add(aliasesPanel, BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents
    
    
    private void detailRfcModeHandler(ActionEvent evt) {//GEN-FIRST:event_detailRfcModeHandler
        this.adl.setDetailMode( DetailMode.Rfc );
    }//GEN-LAST:event_detailRfcModeHandler
    
    private void detailVerboseModeHandler(ActionEvent evt) {//GEN-FIRST:event_detailVerboseModeHandler
        this.adl.setDetailMode( DetailMode.Verbose );
    }//GEN-LAST:event_detailVerboseModeHandler
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel aliasesPanel;
    private JScrollPane aliasesScrollPane;
    private JSplitPane aliasesSplitPane;
    private JTable aliasesTable;
    private ButtonGroup detailModeButtonGroup;
    private JRadioButton detailRfcRadioButton;
    private JScrollPane detailScrollPane;
    private JTextPane detailTextPane;
    private JRadioButton detailVerboseRadioButton;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel typeLabel;
    private JTextField typeTextField;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link findInstance}.
     */
    public static synchronized KeyStoreTopComponent getDefault() {
        if (instance == null) {
            instance = new KeyStoreTopComponent();
        }
        return instance;
    }
    private static synchronized void setDefault( KeyStoreTopComponent newKeyStoreTopComponent ) {
        if (instance == null) {
            instance = newKeyStoreTopComponent;
        }
    }
    
    /**
     * Obtain the KeyStoreTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized KeyStoreTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            ErrorManager.getDefault().log(ErrorManager.WARNING, "Cannot find KeyStore component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof KeyStoreTopComponent) {
            return (KeyStoreTopComponent)win;
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
    
    protected String preferredID() {
        return PREFERRED_ID;
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
            this.getKeyStoreBean().getName(),
            Integer.valueOf(this.aliasesSplitPane.getDividerLocation()),
            this.ksbh,
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
                
                if (state != null && state.length > 0) {
                    this.getKeyStoreBean().setName( (String)state[0] );
                }
                if (state != null && state.length > 1) {
                    this.aliasesSplitPane.setDividerLocation( ((Integer)state[1]).intValue() );
                }
                if (state != null && state.length > 2) {
                    KeyStoreBeanHistory newKsbh = (KeyStoreBeanHistory)state[2];
                    setKeyStoreBeanHistory( newKsbh );
                }
            } catch (Exception e) {
                ErrorManager.getDefault().notify(ErrorManager.WARNING, e);
            }
        }
    }
    
    private void setKeyStoreBeanHistory( KeyStoreBeanHistory newKsbh ) {
        if (this.ksbh != null) {
            this.removePropertyChangeListener( KEY_STORE_BEAN_PROP_NAME, this.ksbh );
        }
        
        this.ksbh = newKsbh;
        this.addPropertyChangeListener( KEY_STORE_BEAN_PROP_NAME, this.ksbh );
    }
    
    public HelpCtx getHelpCtx() {
        return ConstantsHelper.getHelpCtx();
    }
}
