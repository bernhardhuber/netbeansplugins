package org.huber.keytool.ui;

import java.io.Serializable;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import javax.swing.table.AbstractTableModel;
import org.openide.util.NbBundle;

/**
 * The table model for key store entries
 * @author HuberB1
 */
public class KeyStoreEntryTableModel extends AbstractTableModel {
    private KeyStore keyStore;
    private List<KeyStoreEntry> listOfKeyStoreEntry;
    private final String[] columnNames;
    
    /**
     * Creates a new instance of KeyStoreEntryTableModel
     */
    public KeyStoreEntryTableModel() {
        this.listOfKeyStoreEntry = new ArrayList<KeyStoreEntry>();
        columnNames = new String[] {
            NbBundle.getMessage( KeyStoreEntryTableModel.class, "TBL_HDR0_Alias" ),
            NbBundle.getMessage( KeyStoreEntryTableModel.class, "TBL_HDR1_CreatedWhen" ),
            NbBundle.getMessage( KeyStoreEntryTableModel.class, "TBL_HDR2_EntryType" ),
            
            NbBundle.getMessage( KeyStoreEntryTableModel.class, "TBL_HDR2_ValidNotBefore" ),
            NbBundle.getMessage( KeyStoreEntryTableModel.class, "TBL_HDR2_ValidNotAfter" ),
        };
    }
    
    public int getRowCount() {
        int rowCount = listOfKeyStoreEntry.size();
        return rowCount;
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }
    public String getColumnName(int column) {
        String retValue = "";
        final int COLUMN_COUNT = this.columnNames.length;
        if (column >= 0 && column < COLUMN_COUNT) {
            retValue = columnNames[column];
        }
        return retValue;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object retValue = null;
        
        KeyStoreEntry kse = this.listOfKeyStoreEntry.get( rowIndex );
        switch (columnIndex) {
            case 0:
                retValue = kse.getAlias();
                break;
            case 1:
                retValue = formatDate( kse.getCreationDate() );
                break;
            case 2:
                retValue = kse.getType();
                break;
            case 3:
                retValue = formatDate( kse.getNotBefore() );
                break;
            case 4:
                retValue = formatDate( kse.getNotAfter() );
                break;
        }
        return retValue;
    }
    private String formatDate(Date d) {
        String dateFormatted = "";
        if (d != null) {
            final Locale locale = Locale.getDefault();
            final DateFormat dateFormat = DateFormat.getDateTimeInstance(
                    DateFormat.LONG,
                    DateFormat.LONG, locale );
            dateFormatted = dateFormat.format(d);
        }
        return dateFormatted;
    }
    
    public String getAlias( int rowIndex ) {
        KeyStoreEntry kse = this.listOfKeyStoreEntry.get(rowIndex );
        return kse.getAlias();
    }
    
    public void setKeyStore(KeyStore keyStore) {
        this.keyStore = keyStore;
        final List<KeyStoreEntry> kseList = createKeyStoreEntries(keyStore);
        
        this.listOfKeyStoreEntry.clear();
        this.listOfKeyStoreEntry.addAll( kseList );
        this.fireTableDataChanged();
    }
    public KeyStore getKeyStore() {
        return this.keyStore;
    }
    
    private List<KeyStoreEntry> createKeyStoreEntries(KeyStore keyStore) {
        List<KeyStoreEntry> listOfKeyStoreEntry = new ArrayList<KeyStoreEntry>();
        try {
            for (Enumeration<String> aliases = keyStore.aliases(); aliases.hasMoreElements(); ) {
                final String alias = aliases.nextElement();
                
                Date creationDate = keyStore.getCreationDate(alias);
                KeyStoreEntry.Type type =
                        keyStore.isCertificateEntry(alias) ? KeyStoreEntry.Type.Certificate :
                            keyStore.isKeyEntry(alias) ? KeyStoreEntry.Type.Key :
                                KeyStoreEntry.Type.Unknown;
                
                final Certificate certificate = keyStore.getCertificate(alias);
                KeyStoreEntry kse = new KeyStoreEntry( alias, creationDate, type, certificate );
                
                listOfKeyStoreEntry.add( kse );
            }
        } catch (KeyStoreException kse) {
            //??
        }
        Collections.sort( listOfKeyStoreEntry );
        return listOfKeyStoreEntry;
    }
    
    public String[] getAllAliases() {
        final String[] allAliases = new String[ this.listOfKeyStoreEntry.size() ];
        for (int i = 0; i < allAliases.length; i++ ) {
            final String alias = this.listOfKeyStoreEntry.get(i).getAlias();
            allAliases[i] = alias;
        }
        return allAliases;
    }    
}

