/*
 * AliasDetailListener.java
 *
 * Created on 13. Februar 2006, 18:07
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.ui;

import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.openide.ErrorManager;
import org.openide.util.NbBundle;

/**
 * This class listens on selections of a key entry, and updates
 * the key entry detail pane, showing the details of the selected
 * key entry.
 * <p>
 * A key entry is uniquely identified by its alias.
 *
 * @author HuberB1
 */
public class AliasDetailListener implements ListSelectionListener {
    private JTextPane detailPane;
    private KeyStoreEntryTableModel ksetm;
    private final KeyStoreFormatter keyStoreFormatter = new KeyStoreFormatter();
    private String currentAliasShown;
    
    public static enum DetailMode {
        Verbose,
        Rfc
    };
    private DetailMode detailMode = DetailMode.Verbose;
        
    /**
     * Creates a new instance of AliasDetailListener.
     *
     * @param newDetailPane the pane which displays the entry
     * @param ksetm the table model holding the entries
     */
    public AliasDetailListener(JTextPane newDetailPane, KeyStoreEntryTableModel ksetm) {
        this.detailPane = newDetailPane;
        this.ksetm = ksetm;
        this.currentAliasShown = null;
    }
    
    /**
     * Set the new detail mode.
     * <p>
     * Update the text pane accordingly.
     *
     * @param newDetailMode the new detail mode
     */
    public void setDetailMode( DetailMode newDetailMode ) {
        final DetailMode oldDetailMode = this.detailMode;
        this.detailMode = newDetailMode;
        if (this.detailMode != oldDetailMode) {
            final String alias = this.currentAliasShown;
            final KeyStore keyStore = this.ksetm.getKeyStore();
            
            updateDetailPaneText(alias, keyStore );
        }
    }

    /** 
     * Return the current active detail mode
     *
     * @return DetailMode
     */
    public DetailMode getDetailMode() {
        return this.detailMode;
    }
    
    /**
     * Invoked if the selection of the entries table has changed
     *
     * @param e the event describing the selection
     */
    public void valueChanged(ListSelectionEvent e) {
        //Ignore extra messages.
        if (e.getValueIsAdjusting()) {
            return;
        }
        
        // get the alias of the selected entry
        final ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        if (!lsm.isSelectionEmpty()) {
            final int selectedRowIndex = lsm.getMinSelectionIndex();
            
            final String alias = this.ksetm.getAlias( selectedRowIndex );
            final KeyStore keyStore = this.ksetm.getKeyStore();
            
            updateDetailPaneText( alias, keyStore );
        } else {
            // no entry has been selected
            this.detailPane.setText( "<html><p>&nbsp;</p></html>" );
        }
    }
    
    /**
     * Update the detail pane
     *
     * @param alias the entry alias
     * @param keyStore the key store
     */
    private void updateDetailPaneText( String alias, KeyStore keyStore ) {
        final StringBuilder sb = new StringBuilder();
        
        if (alias != null && keyStore != null) {
            try {
                if (keyStore.containsAlias( alias )) {
                    formatAliasKeyStoreAsHtml( sb, alias, keyStore );
                    this.currentAliasShown = alias;
                }
            } catch (KeyStoreException ex) {
                // alias is not valid in respect to the key store
                // ignore
            }
        }

        // set the new detail pane text
        this.detailPane.setText( sb.toString() );
    }
    
    /**
     * Format the key store entry.
     *
     * @param sb write the formatted output here
     * @param alias the alias of the key store entry
     * @param keyStore the key store
     * @return StringBuilder the formatted output
     */
    private StringBuilder formatAliasKeyStoreAsHtml( StringBuilder sb, String alias, KeyStore keyStore ) {
        try {
            sb.append( "<html>" );
            sb.append( NbBundle.getMessage( AliasDetailListener.class, "htmlHead") );
            
            sb.append( NbBundle.getMessage( AliasDetailListener.class, "alias", new Object[] { alias } ) );
            sb.append( NbBundle.getMessage( AliasDetailListener.class, "createdWhen", new Object[] {keyStore.getCreationDate(alias) } ) );
            
            if (keyStore.isCertificateEntry( alias )) {
                final Certificate certificate = keyStore.getCertificate(alias);
                sb.append( "<p>" );
                formatCertificate( sb, certificate );
                sb.append( "</p>" );
            } else if (keyStore.isKeyEntry( alias )) {
                Certificate acertificate[] = keyStore.getCertificateChain(alias);
                if(acertificate != null) {
                    sb.append( NbBundle.getMessage( AliasDetailListener.class,
                            "certificateChainLength",
                            new Object[] { Integer.valueOf(acertificate.length) } )
                            );
                    sb.append( "<ol>" );
                    for(int i = 0; i < acertificate.length; i++) {
                        sb.append( "<li>" );
                        final Certificate certificate = acertificate[i];
                        formatCertificate( sb, certificate );
                        sb.append( "</li>" );
                    }
                    sb.append( "</ol>" );
                }
            }
            sb.append( "</html>" );
        } catch (GeneralSecurityException cex) {
            ErrorManager.getDefault().notify( cex );
        }
        return sb;
    }
    
    private StringBuilder formatCertificate(StringBuilder sb, Certificate certificate ) throws CertificateException {
        if (certificate instanceof X509Certificate) {
            if (this.detailMode == DetailMode.Verbose) {
                try {
                    this.keyStoreFormatter.printX509Cert( sb, (X509Certificate)certificate);
                } catch (NoSuchAlgorithmException ex) {
                    sb.append( "<pre><code>");
                    sb.append( ex.getMessage() );
                    sb.append( "</code></pre>");
                }
            } else {
                this.keyStoreFormatter.dumpCert( sb, certificate );
            }
        } else {
            this.keyStoreFormatter.dumpCert( sb, certificate );
        }
        return sb;
    }
}
