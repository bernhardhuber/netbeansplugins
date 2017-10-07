package org.huber.keytool.ui.wizard.genkey;

import org.huber.keytool.model.KeyAlgCertAlgBean;
import org.huber.keytool.model.LabelValueBean;

/**
 * Encapsulate a label with the KeyAlgCertAlgBean object.
 */
public class LabelKeyAlgCertAlgBean extends LabelValueBean<KeyAlgCertAlgBean> implements Comparable {
    public LabelKeyAlgCertAlgBean(String label, KeyAlgCertAlgBean kaca) {
        super( label, kaca );
    }
    
    public int compareTo(Object o) {
        int compareTo = 0;
        
        if (o == this) {
            compareTo = 0;
        } else {
            final LabelKeyAlgCertAlgBean other = (LabelKeyAlgCertAlgBean)o;
            compareTo = this.getLabel().compareTo( other.getLabel() );
        }
        return compareTo;
    }
    
    
}