/*
 * ValidatorException.java
 *
 * Created on 19. Mai 2007, 12:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.validation;

/**
 *
 * @author HuberB1
 */
public class ValidatorException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>ValidatorException</code> without detail message.
     */
    public ValidatorException() {
    }
    
    
    /**
     * Constructs an instance of <code>ValidatorException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ValidatorException(String msg) {
        super(msg);
    }
    
    /**
     * Constructs an instance of <code>ValidatorException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ValidatorException(String msg, Throwable t) {
        super(msg, t);
    }
}
