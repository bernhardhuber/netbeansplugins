/*
 * KeyStoreFileFilterFactory.java
 *
 * Created on 20. April 2006, 14:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.ui.filefilter;

import java.io.File;
import javax.swing.JFileChooser;
import org.openide.util.NbBundle;

/**
 *
 * @author HuberB1
 */
public class KeyStoreFileFilterFactory {
    private final static KeyStoreFilter ksf;
    private final static JKSFilter jks;
    static {
        ksf = new KeyStoreFilter();
        jks = new JKSFilter();
    }
    
    /**
     * Creates a new instance of KeyStoreFileFilterFactory
     */
    protected KeyStoreFileFilterFactory() {
    }
    
    /**
     * Add the file filter to the file chooser
     *
     * @param fc the file chooser
     */
    public static void addChoosableFileFilter( JFileChooser fc ) {
        fc.addChoosableFileFilter( ksf );
        fc.addChoosableFileFilter( jks );
    }
    public static boolean accept( File file ) {
        boolean accept = false; 
        
        accept = accept || ksf.accept(file);
        accept = accept || jks.accept(file);
        
        return accept;
    }
    
    /**
     * A FileFilter for key store files
     */
    public static class KeyStoreFilter extends AbstractFilter {
        private static final String KEYSTORE_EXTENSION = ".keystore";
        
        public KeyStoreFilter() {
            super( KEYSTORE_EXTENSION, NbBundle.getMessage(KeyStoreFilter.class, "DESC_KeyStoreFileFilter") );
        }
    }
    /**
     * A FileFilter for key store files
     */
    public static class JKSFilter extends AbstractFilter {
        private static final String KEYSTORE_EXTENSION = ".jks";
        
        public JKSFilter() {
            super( KEYSTORE_EXTENSION, NbBundle.getMessage(JKSFilter.class, "DESC_JKSFileFilter") );
        }
    }
}
