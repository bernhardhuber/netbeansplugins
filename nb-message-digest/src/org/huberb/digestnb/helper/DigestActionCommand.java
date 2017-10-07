/*
 * DigestActionCommand.java
 *
 * Created on 06. Oktober 2005, 23:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.digestnb.helper;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Logger;

/**
 * Encapsualte building an human readable digest text message.
 *
 * @author HuberB1
 */
public class DigestActionCommand implements Serializable {
    private final static long serialVersionUID = 20051006233800L;
    
    private static final Logger logger = Logger.getLogger( DigestActionCommand.class.getName() );
    
    public enum EncodingMode {
        Hex,
        Base64;
        
        private static final long serialVersionUID = 200601281208L;
    }
    
    private EncodingMode encodingMode;
    private String secret;
    private String algorithm;
    
    /**
     * Holds value of property messagePattern.
     */
    private String messagePattern;
    
    /**
     * Creates a new instance of DigestActionCommand.
     *
     * @param newAlogrithm algorithm used for building the digest
     * @param newEncodingMode the digest encoding mode
     * @param newSecret the secret
     */
    public DigestActionCommand(EncodingMode newEncodingMode, String newAlogrithm, String newSecret) {
        this.setEncodingMode( newEncodingMode );
        this.setAlgorithm( newAlogrithm );
        this.setSecret( newSecret );
    }
    
    /**
     * Build the digest message text.
     *
     * @return StringBuffer the build digest as human readable message text, like
     *   <code>secret secret [MD5 (algorithm), Hex (encoding)] : 5ebe2294ecd0e0f08eab7690d2a6ee69"</code>
     */
    public StringBuffer buildDigest() {
        final StringBuffer sb = new StringBuffer();
        try {
            String digestMessage = "";
            String encodingMethod = "";
            
            if (this.encodingMode == EncodingMode.Hex) {
                digestMessage = Digest.getHexDigest( secret, algorithm );
                encodingMethod = "Hex";
            } else if (this.encodingMode == EncodingMode.Base64) {
                digestMessage = Digest.getBase64Digest( secret, algorithm );
                encodingMethod = "Base64";
            }
            
            //final String messageKey = "secret {0} [{1} (algorigthm), {2} (encoding)] : {3}";
            final String messageKey = this.messagePattern;
            final String message = MessageFormat.format( messageKey, new Object[] {
                secret, algorithm, encodingMethod, digestMessage
            });
            sb.append( message );
            
        } catch (Exception e) {
            logger.throwing( this.getClass().getName(), "digestAction", e );
        }
        return sb;
    }
    
    /**
     * Getter for property secret.
     * @return Value of property secret.
     */
    public String getSecret() {
        return this.secret;
    }
    
    /**
     * Setter for property secret.
     * @param secret New value of property secret.
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }
    
    /**
     * Getter for property algorithm.
     * @return Value of property algorithm.
     */
    public String getAlgorithm() {
        return this.algorithm;
    }
    
    /**
     * Setter for property algorithm.
     * @param algorithm New value of property algorithm.
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
    
    public EncodingMode getEncodingMode() {
        return this.encodingMode;
    }
    public void setEncodingMode( EncodingMode newEncodingMode ) {
        this.encodingMode = newEncodingMode;
    }
    
    /**
     * Getter for property messagePattern.
     * @return Value of property messagePattern.
     */
    public String getMessagePattern() {
        return this.messagePattern;
    }
    
    /**
     * Setter for property messagePattern.
     * @param messagePattern New value of property messagePattern.
     */
    public void setMessagePattern(String messagePattern) {
        this.messagePattern = messagePattern;
    }
    
}
