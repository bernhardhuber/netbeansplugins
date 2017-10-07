/*
 * KeyToolUtils.java
 *
 * Created on 17. September 2006, 20:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.openide.util.NbBundle;

/**
 * General utilities for manipulating the keystore, and its entries.
 *
 * @author HuberB1
 */
public class KeyToolUtils {
    
    /** Creates a new instance of KeyToolUtils */
    private KeyToolUtils() {
    }
    
    /**
     * Recover private key
     *
     * @param keyStore the key store
     * @param alias the alias name
     * @param storePass the password of the key store
     * @param keyPass the password of the key entry, named by alias
     */
    public static Object[] recoverPrivateKey(KeyStore keyStore, char storePass[],
            String alias, char keyPass[]) throws Exception {
        java.security.Key key = null;
        if(!keyStore.containsAlias(alias)) {
            final String message = NbBundle.getMessage(KeyToolUtils.class,
                    "ERR_ALIAS_DOES_NOT_EXIST", new Object[] {alias} );
            throw new Exception(message);
        }
        if(!keyStore.isKeyEntry(alias)) {
            final String message = NbBundle.getMessage(KeyToolUtils.class,
                    "ERR_ALIAS_HAS_NO_PRIVATE_KEY", new Object[] {alias} );
            throw new Exception(message);
        }
        if(keyPass == null)
            try {
                key = keyStore.getKey(alias, storePass);
                keyPass = storePass;
            } catch (UnrecoverableKeyException unrecoverablekeyexception) {
                throw unrecoverablekeyexception;
            } else {
            key = keyStore.getKey(alias, keyPass);
            }
        if(!(key instanceof PrivateKey)) {
            final String message = NbBundle.getMessage(KeyToolUtils.class,
                    "ERR_NOT_A_PRIVATE_KEY", new Object[] {alias} );
            throw new Exception(message);
        } else {
            return (new Object[] {
                (PrivateKey)key, keyPass
            });
        }
    }
    
    public static boolean installReply(InputStream inputstream,
            KeyStore keyStore, char[] storePass,
            String alias, char[] keyPass )
            throws Exception {
        
        if(alias == null) {
            //alias = keyAlias;
        }
        Object aobj[] = recoverPrivateKey(keyStore, storePass, alias, keyPass);
        PrivateKey privatekey = (PrivateKey)aobj[0];
        if(keyPass == null) {
            keyPass = (char[])(char[])aobj[1];
        }
        java.security.cert.Certificate certificate = keyStore.getCertificate(alias);
        if(certificate == null) {
            MessageFormat messageformat = new MessageFormat(msg("alias has no public key (certificate)"));
            Object aobj1[] = {
                alias
            };
            throw new Exception(messageformat.format(((Object) (aobj1))));
        }
        CertificateFactory cf = CertificateFactory.getInstance("X509");
        Collection collection = cf.generateCertificates(inputstream);
        if(collection.isEmpty())
            throw new Exception(msg("Reply has no certificates"));
        java.security.cert.Certificate acertificate[] = (java.security.cert.Certificate[])(java.security.cert.Certificate[])collection.toArray(new java.security.cert.Certificate[collection.size()]);
        java.security.cert.Certificate acertificate1[];
        if(acertificate.length == 1)
            acertificate1 = establishCertChain(keyStore, certificate, acertificate[0]);
        else
            acertificate1 = validateReply(alias, certificate, acertificate);
        if(acertificate1 != null) {
            keyStore.setKeyEntry(alias, privatekey, keyPass == null ? storePass : keyPass, acertificate1);
            return true;
        } else {
            return false;
        }
    }
    
    private static Certificate[] establishCertChain(KeyStore keyStore,
            Certificate certificate, Certificate certificate1)
            throws Exception {
        final boolean trustcacerts = false;
        
        if(certificate != null) {
            PublicKey publickey = certificate.getPublicKey();
            PublicKey publickey1 = certificate1.getPublicKey();
            if(!publickey.equals(publickey1))
                throw new Exception(msg("Public keys in reply and keystore don't match"));
            if(certificate1.equals(certificate))
                throw new Exception(msg("Certificate reply and certificate in keystore are identical"));
        }
        Hashtable hashtable = null;
        if(keyStore.size() > 0) {
            hashtable = new Hashtable(11);
            keystorecerts2Hashtable(keyStore, hashtable);
        }
        final KeyStore caks = getCacertsKeyStore();
        if(trustcacerts && caks != null && caks.size() > 0) {
            if(hashtable == null)
                hashtable = new Hashtable(11);
            keystorecerts2Hashtable(caks, hashtable);
        }
        Vector vector = new Vector(2);
        if(buildChain((X509Certificate)certificate1, vector, hashtable)) {
            java.security.cert.Certificate acertificate[] = new java.security.cert.Certificate[vector.size()];
            int i = 0;
            for(int j = vector.size() - 1; j >= 0; j--) {
                acertificate[i] = (java.security.cert.Certificate)vector.elementAt(j);
                i++;
            }
            
            return acertificate;
        } else {
            throw new Exception(msg("Failed to establish chain from reply"));
        }
    }
    private static void keystorecerts2Hashtable(KeyStore keystore, Hashtable hashtable)
    throws Exception {
        Enumeration enumeration = keystore.aliases();
        do
        {
            if(!enumeration.hasMoreElements())
                break;
            String s = (String)enumeration.nextElement();
            Certificate certificate = keystore.getCertificate(s);
            if(certificate != null) {
                java.security.Principal principal = ((X509Certificate)certificate).getSubjectDN();
                Vector vector = (Vector)hashtable.get(principal);
                if(vector == null) {
                    vector = new Vector();
                    vector.addElement(certificate);
                } else
                    if(!vector.contains(certificate))
                        vector.addElement(certificate);
                hashtable.put(principal, vector);
            }
        } while(true);
    }
    private static boolean buildChain(X509Certificate x509certificate, Vector vector, Hashtable hashtable) {
        label0:
        {
            java.security.Principal principal = x509certificate.getSubjectDN();
            java.security.Principal principal1 = x509certificate.getIssuerDN();
            if(principal.equals(principal1)) {
                vector.addElement(x509certificate);
                return true;
            }
            Vector vector1 = (Vector)hashtable.get(principal1);
            if(vector1 == null)
                return false;
            X509Certificate x509certificate1;
            label1:
                do
                {
                    for(Enumeration enumeration = vector1.elements(); enumeration.hasMoreElements();) {
                        x509certificate1 = (X509Certificate)enumeration.nextElement();
                        java.security.PublicKey publickey = x509certificate1.getPublicKey();
                        try {
                            x509certificate.verify(publickey);
                            continue label1;
                        } catch(Exception exception) { }
                    }
                    
                    break label0;
                } while(!buildChain(x509certificate1, vector, hashtable));
                vector.addElement(x509certificate);
                return true;
        }
        return false;
    }
    
    private static KeyStore getCacertsKeyStore()
    throws Exception {
        String s = File.separator;
        final StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("java.home")).append(s).append("lib").append(s).append("security").append(s).append("cacerts");
        File file = new File(sb.toString());
        if(!file.exists()) {
            return null;
        } else {
            FileInputStream fileinputstream = new FileInputStream(file);
            try {
                KeyStore keystore = KeyStore.getInstance("jks");
                keystore.load(fileinputstream, null);
                return keystore;
            } finally {
                fileinputstream.close();
            }
        }
    }
    
    private static Certificate[] validateReply(String s, Certificate certificate, Certificate acertificate[])
    throws Exception {
        PublicKey publickey = certificate.getPublicKey();
        int i;
        for(i = 0; i < acertificate.length && !publickey.equals(acertificate[i].getPublicKey()); i++);
        if(i == acertificate.length) {
            MessageFormat messageformat = new MessageFormat(msg("Certificate reply does not contain public key for <alias>"));
            Object aobj[] = {
                s
            };
            throw new Exception(messageformat.format(((Object) (aobj))));
        }
        Certificate certificate1 = acertificate[0];
        acertificate[0] = acertificate[i];
        acertificate[i] = certificate1;
        java.security.Principal principal = ((X509Certificate)acertificate[0]).getIssuerDN();
        for(int j = 1; j < acertificate.length - 1; j++) {
            int l = j;
            do
            {
                if(l >= acertificate.length)
                    break;
                java.security.Principal principal1 = ((X509Certificate)acertificate[l]).getSubjectDN();
                if(principal1.equals(principal)) {
                    java.security.cert.Certificate certificate2 = acertificate[j];
                    acertificate[j] = acertificate[l];
                    acertificate[l] = certificate2;
                    principal = ((X509Certificate)acertificate[j]).getIssuerDN();
                    break;
                }
                l++;
            } while(true);
            if(l == acertificate.length)
                throw new Exception(msg("Incomplete certificate chain in reply"));
        }
        
        for(int k = 0; k < acertificate.length - 1; k++) {
            java.security.PublicKey publickey1 = acertificate[k + 1].getPublicKey();
            try {
                acertificate[k].verify(publickey1);
            } catch(Exception exception) {
                throw new Exception((new StringBuilder()).append(msg("Certificate chain in reply does not verify: ")).append(exception.getMessage()).toString());
            }
        }
        final boolean noprompt = true;
        
//        if(noprompt)
//            return acertificate;
//        java.security.cert.Certificate certificate3 = acertificate[acertificate.length - 1];
//        if(!isTrusted(certificate3)) {
//            boolean flag = false;
//            java.security.cert.Certificate certificate4 = null;
//            if(trustcacerts && caks != null) {
//                Enumeration enumeration = caks.aliases();
//                do
//                {
//                    if(!enumeration.hasMoreElements())
//                        break;
//                    String s2 = (String)enumeration.nextElement();
//                    certificate4 = caks.getCertificate(s2);
//                    if(certificate4 == null)
//                        continue;
//                    try {
//                        certificate3.verify(certificate4.getPublicKey());
//                        flag = true;
//                        break;
//                    } catch(Exception exception1) { }
//                } while(true);
//            }
//            if(!flag) {
//                System.err.println();
//                System.err.println(rb.getString("Top-level certificate in reply:\n"));
//                printX509Cert((X509Certificate)certificate3, System.out);
//                System.err.println();
//                System.err.print(rb.getString("... is not trusted. "));
//                String s1 = getYesNoReply(rb.getString("Install reply anyway? [no]:  "));
//                if(s1.equals("NO"))
//                    return null;
//            } else
//                if(!isSelfSigned((X509Certificate)certificate3)) {
//                java.security.cert.Certificate acertificate1[] = new java.security.cert.Certificate[acertificate.length + 1];
//                System.arraycopy(acertificate, 0, acertificate1, 0, acertificate.length);
//                acertificate1[acertificate1.length - 1] = certificate4;
//                acertificate = acertificate1;
//                }
//        }
        return acertificate;
    }
    private static boolean isSelfSigned(X509Certificate x509certificate) {
        return x509certificate.getSubjectDN().equals(x509certificate.getIssuerDN());
    }
    private static boolean isTrusted(KeyStore keyStore, Certificate certificate)
    throws Exception {
        if(keyStore.getCertificateAlias(certificate) != null) {
            return true;
        }
        final KeyStore caks = getCacertsKeyStore();
        final boolean trustcacerts = false;
        
        return trustcacerts && caks != null && caks.getCertificateAlias(certificate) != null;
    }
    private static String msg(String m ) {
        return m;
    }
}
