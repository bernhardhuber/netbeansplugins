/*
 * CertificateRequestFileFilterFactory.java
 *
 * Created on 17. September 2006, 22:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.ui.filefilter;

import javax.swing.JFileChooser;
import org.openide.util.NbBundle;

/**
 *
 * @author HuberB1
 */
public class CertificateRequestFileFilterFactory {
    private final static AbstractFilter csr;
    private final static AbstractFilter certReq;
    static {
        csr = new CsrFilter();
        certReq = new CertReqFilter();
    }
    
    /** Creates a new instance of CertificateRequestFileFilterFactory */
    protected CertificateRequestFileFilterFactory() {
    }

    /**
     * Add the file filter to the file chooser
     *
     * @param fc the file chooser
     */
    public static void addChoosableFileFilter( JFileChooser fc ) {
        fc.addChoosableFileFilter( csr );
        fc.addChoosableFileFilter( certReq );
    }
    
    /**
     * Certificate Request File Filter
     */
    public static class CsrFilter extends AbstractFilter {
        private static final String EXTENSION = ".csr";
        
        public CsrFilter() {
            super( EXTENSION, NbBundle.getMessage(CsrFilter.class, "DESC_CSRFileFilter") );
        }
    }
    /**
     * Certificate Request File Filter
     */
    public static class CertReqFilter extends AbstractFilter {
        private static final String EXTENSION = ".certreq";
        
        public CertReqFilter() {
            super( EXTENSION, NbBundle.getMessage(CertReqFilter.class, "DESC_CERTREQFileFilter") );
        }
    }
}
