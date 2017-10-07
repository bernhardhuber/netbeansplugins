package org.huber.keytool.ui;

import java.io.Serializable;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;


// alias, creationDate, type [keyEntry,trustedCertEntry]
class KeyStoreEntry implements Serializable, Comparable {
    protected static final long serialVersionUID = 20060916120100L;
    
    enum Type {
        Unknown,
        Certificate,
        Key
    }
    
    private String alias;
    private long creationDateTime;
    private Type type;
    private long notBeforeTime;
    private long notAfterTime;    
    public KeyStoreEntry() {
    }
    
    public KeyStoreEntry(String alias, Date creationDate, Type type, Certificate certificate) {
        this.alias = alias;
        this.creationDateTime = creationDate.getTime();
        this.type = type;
        
        if (certificate instanceof X509Certificate) {
            final X509Certificate x509Certificate = (X509Certificate)certificate;
            this.notBeforeTime = x509Certificate.getNotBefore().getTime();
            this.notAfterTime = x509Certificate.getNotAfter().getTime();
        }
    }
    public String getAlias() {
        return this.alias;
    }
    public Date getCreationDate() {
        return new Date(this.creationDateTime);
    }
    public String getType() {
        return String.valueOf(this.type);
    }
    
    public Date getNotBefore() {
        return new Date(this.notBeforeTime);
    }
    public Date getNotAfter() {
        return new Date(this.notAfterTime);
    }
    
    //----
    public int compareTo(Object obj) {
        int compareTo = 0;
        
        if (this == obj) {
            compareTo = 0;
        } else {
            if (this.alias != null && obj != null && obj instanceof KeyStoreEntry) {
                final KeyStoreEntry other = (KeyStoreEntry)obj;
                compareTo = this.alias.compareTo( other.alias );
            }
        }
        return compareTo;
    }
}