package org.huber.keytool.ui.wizard.genkey;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Base64.Encoder;
import org.huber.keytool.model.KeyAlgCertAlgBean;

//import sun.misc.BASE64Encoder;
/**
 * Basic certificate creation
 */
public class GenerateCertificateAndKey {

    private PrivateKey privKey;
    private X509Certificate[] chain;

    public GenerateCertificateAndKey() {
    }

    public PrivateKey getPrivKey() {
        return this.privKey;
    }

    public X509Certificate[] getChain() {
        return this.chain;
    }

    public void generateCertificate(GenKeyForm genKeyForm) throws NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException, SignatureException, NoSuchProviderException {
        final int keySize = genKeyForm.getKeySize();
        final long validity = genKeyForm.getValidityInSecs();

        final KeyAlgCertAlgBean kacab = genKeyForm.getKeyAlgCertAlg();
        final String keyAlg = kacab.getKeyAlg();
        final String certAlg = kacab.getCertAlg();

        //sun.security.x509.CertAndKeyGen keypair = new sun.security.x509.CertAndKeyGen("RSA", "MD5WithRSA" );
        sun.security.x509.CertAndKeyGen keypair = new sun.security.x509.CertAndKeyGen(keyAlg, certAlg);

        final String name = genKeyForm.getX500PrincipalAsString();
        sun.security.x509.X500Name x500Name = new sun.security.x509.X500Name(name);

        keypair.generate(keySize);

        final PrivateKey privKey = keypair.getPrivateKey();
        final X509Certificate[] chain = new X509Certificate[1];
        chain[0] = keypair.getSelfCertificate(x500Name, validity);

        this.privKey = privKey;
        this.chain = chain;

        final X509Certificate aX509Certificate = chain[0];
        // dumpCert(true, aX509Certificate , System.out );
    }

    private void dumpCert(boolean rfc, Certificate certificate, PrintStream printstream) throws IOException, CertificateException {
        if (rfc) {
            final Encoder base64encoder = Base64.getEncoder();
            final byte[] encoded = base64encoder.encode(certificate.getEncoded());
            printstream.println("-----BEGIN CERTIFICATE-----");
            printstream.write(encoded, 0, encoded.length);
            printstream.println("-----END CERTIFICATE-----");
        } else {
            printstream.write(certificate.getEncoded());
        }
    }
}
