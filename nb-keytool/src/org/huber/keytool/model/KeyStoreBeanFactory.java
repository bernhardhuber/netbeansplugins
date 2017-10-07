/*
 * KeyStoreBeanFactory.java
 *
 * Created on 03. April 2006, 21:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.openide.ErrorManager;

/**
 *
 * @author HuberB1
 */
public class KeyStoreBeanFactory {
    
    /** Creates a new instance of KeyStoreBeanFactory */
    public KeyStoreBeanFactory() {
    }
    
    /**
     * Validate that keystore file can be loaded
     *
     * @param keyStoreFile the keystore file
     * @param password the keystore password
     *
     * @return boolean true if loading succeeded, else false
     * @throws Exception if loading of keystore fails
     */
    public boolean validateKeyStore(File keyStoreFile, char[] password) throws Exception {
        FileInputStream fis = null;
        KeyStore keyStore = null;
        try {
            fis = new FileInputStream( keyStoreFile.getPath() );
            keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load( fis, password );
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    ErrorManager.getDefault().notify( ErrorManager.INFORMATIONAL, ex );
                }
            }
        }
        return true;
    }
    
    public KeyStoreBean createKeyStoreBean(File keyStoreFile, char[] password)  {
        FileInputStream fis = null;
        KeyStoreBean ksb = null;
        try {
            fis = new FileInputStream( keyStoreFile.getPath() );
            try {
                final KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load( fis, password );
                
                ksb = new KeyStoreBean(keyStoreFile.getAbsolutePath(),
                        keyStore, password );
                
            } catch (CertificateException ce) {
                errorManagerAnnotate( ce );
            } catch (NoSuchAlgorithmException nsae) {
                errorManagerAnnotate( nsae);
            } catch (KeyStoreException kse) {
                errorManagerAnnotate( kse);
            } catch (IOException ioe) {
                errorManagerAnnotate( ioe);
            }
        } catch (FileNotFoundException fnfe) {
            errorManagerAnnotate( fnfe);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    ErrorManager.getDefault().notify( ErrorManager.INFORMATIONAL, ex );
                }
            }
        }
        return ksb;
    }
    
    private void errorManagerAnnotate( Throwable t ) {
        String msg = t.getLocalizedMessage();
        if (msg == null) {
            msg = t.getMessage();
        }
        ErrorManager em = ErrorManager.getDefault();
        em.annotate( t, msg );
        em.notify( t );
    }
}
