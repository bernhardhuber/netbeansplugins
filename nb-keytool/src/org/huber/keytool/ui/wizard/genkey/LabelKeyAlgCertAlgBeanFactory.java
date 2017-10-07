/*
 * LabelKeyAlgCertAlgBeanFactory.java
 *
 * Created on 17. April 2006, 09:48
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.ui.wizard.genkey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.huber.keytool.model.CryptographicServices;
import org.huber.keytool.model.KeyAlgCertAlgBean;

/**
 *
 * @author HuberB1
 */
public class LabelKeyAlgCertAlgBeanFactory {
    
    /** Creates a new instance of LabelKeyAlgCertAlgBeanFactory */
    private LabelKeyAlgCertAlgBeanFactory() {
    }
    
    //---
    
    public static LabelKeyAlgCertAlgBean[] createLabelKeyAlgCertAlgBeans() {
        //final LabelKeyAlgCertAlgBean[] lkacabs = createLabelKeyAlgCertAlgBeansExplictly();
        final LabelKeyAlgCertAlgBean[] lkacabs = createLabelKeyAlgCertAlgBeansFromCryptographicServices();
        
        Arrays.sort( lkacabs );
        return lkacabs;
    }
    
    public static final KeyAlgCertAlgBean RSA = new KeyAlgCertAlgBean( "RSA", "MD5WithRSA");
    public static final KeyAlgCertAlgBean DSA = new KeyAlgCertAlgBean( "DSA", "SHA1WithDSA");
    public static final KeyAlgCertAlgBean DSS = new KeyAlgCertAlgBean( "DSS", "SHA1WithDSA");
        
    public static LabelKeyAlgCertAlgBean createLabelKeyAlgCertAlgBean(KeyAlgCertAlgBean kacab ) {
        final StringBuilder sb = new StringBuilder();
        
        sb.append( kacab.getKeyAlg() );
        sb.append( " : " );
        sb.append( kacab.getCertAlg() );
        LabelKeyAlgCertAlgBean lkacab = new LabelKeyAlgCertAlgBean(sb.toString(), kacab );
        return lkacab;
    }
    private static LabelKeyAlgCertAlgBean[] createLabelKeyAlgCertAlgBeansFromCryptographicServices() {
        final String[] kAlgLabels = CryptographicServices.getCryptoImpls("KeyPairGenerator", false);
        final String[] cAlgLabels = CryptographicServices.getCryptoImpls("Signature", false);
        
        final List<LabelKeyAlgCertAlgBean>l = new ArrayList<LabelKeyAlgCertAlgBean>();
        for (int i = 0; i < kAlgLabels.length; i++ ) {
            for (int j = 0; j < cAlgLabels.length; j++ ) {
                final String kAlgLabel = kAlgLabels[i];
                final String cAlgLabel = cAlgLabels[j];
                if (cAlgLabel.indexOf(kAlgLabel) > -1) {
                    final KeyAlgCertAlgBean kaca = new KeyAlgCertAlgBean(kAlgLabel,cAlgLabel);
//                    final String label = kAlgLabels[i] + " : " + cAlgLabels[j];
//                    final LabelKeyAlgCertAlgBean lkacab = new LabelKeyAlgCertAlgBean(label,kaca);
                    
                    LabelKeyAlgCertAlgBean lkacab = createLabelKeyAlgCertAlgBean(kaca);
                    l.add( lkacab );
                }
            }
        }
        Collections.sort( l );
        return (LabelKeyAlgCertAlgBean[])l.toArray(new LabelKeyAlgCertAlgBean[l.size()]);
    }
}
