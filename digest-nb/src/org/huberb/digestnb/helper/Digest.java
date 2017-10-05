package org.huberb.digestnb.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 * Encapsulate building a digest.
 *
 * @author  HuberB1
 */
public class Digest {
    private static final Logger logger = Logger.getLogger( Digest.class.getName() );
    
    private Digest() {
    }
    
    /**
     * Build a digest and encode the digest as hex string.
     *
     * @param credentials the secret
     * @param algorithm used for digesting, eg. MD5, SHA
     * @return String the encoded digest of the crediential input parameter
     */
    public final static String getHexDigest(String credentials, String algorithm) {
        byte[] hash = getDigest( credentials, algorithm );
        final String hexDigest = HexUtils.convert( hash );
        return hexDigest;
    }
    /**
     * Build a digest and encode the digest as base64 string.
     *
     * @param credentials the secret
     * @param algorithm used for digesting, eg. MD5, SHA
     * @return String the encoded digest of the crediential input parameter
     */
    public final static String getBase64Digest(String credentials, String algorithm) {
        byte[] hash = getDigest( credentials, algorithm );
        byte[] hashBase64 = Base64.encode( hash );
        return new String( hashBase64 );
    }
    
    /**
     * Build a digest.
     *
     * @param credentials the secret
     * @param algorithm used for digesting, eg. MD5, SHA
     * @return bytes[] the digest of the crediential input parameter
     */
    public final static byte[] getDigest(String credentials, String algorithm) {
        byte[] hash = null;
        try {
            // Obtain a new message digest with "digest" encryption
            MessageDigest md = (MessageDigest) MessageDigest.getInstance(algorithm);
            // encode the credentials
            md.update(credentials.getBytes());
            // Digest the credentials and return as hexadecimal
            hash = md.digest();
        } catch(NoSuchAlgorithmException nsae) {
            logger.throwing( Digest.class.getName(), "getDigest", nsae );
        }
        return hash;
    }
    
    public static void main( String[] args ) {
        String hash;
        hash = getBase64Digest("secret", "sha" );
        System.out.println( "hash bash64 " + hash );
        
        hash = getHexDigest("secret", "sha" );
        System.out.println( "hash hex " + hash );
        
    }
}
