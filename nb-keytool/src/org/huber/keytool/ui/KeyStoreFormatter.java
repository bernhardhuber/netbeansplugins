/*
 * KeyStoreFormatter.java
 *
 * Created on 14. Februar 2006, 00:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.openide.util.NbBundle;
import sun.misc.BASE64Encoder;

/**
 * A formatter for a key store entry.
 *
 * @author HuberB1
 */
public class KeyStoreFormatter {
    
    /** Creates a new instance of KeyStoreFormatter */
    public KeyStoreFormatter() {
    }

    /**
     * Dump the certificate base64 encoded.
     *
     * @param sb write certificate here
     * @param certificate the certificate
     * @return StringBuilder
     */
    public StringBuilder dumpCert(StringBuilder sb, Certificate certificate ) throws CertificateException {
        final BASE64Encoder base64encoder = new BASE64Encoder();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final PrintStream ps = new PrintStream(baos);
        try {
            ps.println();
            base64encoder.encodeBuffer(certificate.getEncoded(), ps);
//            ps.flush();
//            baos.flush();
        } catch (IOException ioex) {
            throw new CertificateException( "Cannot dump certificate", ioex );
        } finally {
            try {
                baos.close();
            } catch (IOException ex) {
                // ignore it
            }
        }
        sb.append( NbBundle.getMessage( KeyStoreFormatter.class, "dumpCert", baos.toString() ) );
        return sb;
    }
    
    /**
     * Print the certificate in a human readable format
     *
     * @param sb write the formatted certificate here
     * @param x509certificate the certificate
     * @return StringBuilder
     */
    public StringBuilder printX509Cert( StringBuilder sb, X509Certificate x509certificate ) throws CertificateException, NoSuchAlgorithmException {
        Object aobj[] = {
            x509certificate.getSubjectDN().toString(),
            x509certificate.getIssuerDN().toString(),
            x509certificate.getSerialNumber().toString(16),
            x509certificate.getNotBefore(),
            x509certificate.getNotAfter(),
            getCertFingerPrint("MD5", x509certificate),
            getCertFingerPrint("SHA1", x509certificate)
        };
        sb.append( NbBundle.getMessage( KeyStoreFormatter.class, "printX509Cert", aobj ) );
        
        return sb;
    }
    
    /**
     * Return hash of the certificate
     *
     * @param hashAlg the hash algorithm, like 'MD5', or 'SHA1'.
     * @param certificiate the certificate
     * @return StringBuilder destination of hash encoded fingerprint
     */
    public StringBuilder getCertFingerPrint(String hashAlg, Certificate certificate) throws CertificateEncodingException, NoSuchAlgorithmException {
        byte abyte0[] = certificate.getEncoded();
        MessageDigest messagedigest = MessageDigest.getInstance(hashAlg);
        byte abyte1[] = messagedigest.digest(abyte0);
        return toHexString(abyte1);
    }
    
    
    private StringBuilder toHexString(byte []abyte0) {
        StringBuilder sb = new StringBuilder();
        int i = abyte0.length;
        for(int j = 0; j < i; j++) {
            byte2hex(sb, abyte0[j] );
            if(j < i - 1) {
                sb.append(":");
            }
        }
        return sb;
    }
    
    private StringBuilder byte2hex(StringBuilder sb, byte byte0 ) {
        final char ac[] = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'
        };
        int i = (byte0 & 0xf0) >> 4;
        int j = byte0 & 0xf;
        sb.append(ac[i]);
        sb.append(ac[j]);
        return sb;
    }
}
