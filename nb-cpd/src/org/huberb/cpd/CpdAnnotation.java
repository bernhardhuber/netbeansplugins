/*
 * CpdAnnotation.java
 *
 * Created on 22. April 2007, 17:06
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.cpd;

import org.openide.text.Annotation;
import org.openide.util.NbBundle;

/**
 * The CPD annotation.
 *
 * @author HuberB1
 */
public class CpdAnnotation extends Annotation {
    private final String info;
    
    /** Creates a new instance of CpdAnnotation */
    public CpdAnnotation(String info) {
        this.info = info;
    }
    
    public String getAnnotationType() {
        return "org-huberb-cpd-cpd-annotation";
    }
    
    public String getShortDescription() {
        String message = NbBundle.getMessage(CpdAnnotation.class, "INFO_CPDANNOTATION", info);
        return message;
    }
    
    //----
    // Implement equals/hashCode to avoid duplicate annotations for a single
    // CPD duplicate
    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj instanceof CpdAnnotation) {
            CpdAnnotation other = (CpdAnnotation)obj;
            boolean retValue = this.info.equals(other.info);
            
            return retValue;
        }
        return false;
    }
    
    public int hashCode() {
        int retValue;
        retValue = this.info.hashCode();
        return retValue;
    }
    
    
}