/*
 * ConstraintIF.java
 *
 * Created on 21. Mai 2007, 18:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.validation;

/**
 *
 * @author HuberB1
 */
public interface ConstraintIF {

    public void constraint( Object obj ) throws ValidatorException;
    
}
