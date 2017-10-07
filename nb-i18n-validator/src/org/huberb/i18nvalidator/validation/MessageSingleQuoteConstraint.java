/*
 * MessageSingleQuoteConstraint.java
 *
 * Created on 21. Mai 2007, 18:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.validation;

import java.util.Locale;

/**
 *
 * @author HuberB1
 */
public class MessageSingleQuoteConstraint implements ConstraintIF {
    private Locale locale;
    
    /** Creates a new instance of MessageSingleQuoteConstraint */
    public MessageSingleQuoteConstraint() {
    }
    
    public Locale getLocale() {
        return locale;
    }
    
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    public void constraint( Object obj ) throws ValidatorException {
        final String pattern = (String)obj;
        
        char singleQuote = '\'';
        int countOfQuote = 0;
        
        int fromIndex = 0;
        int patternLength = pattern.length();
        int indexOfSingleQuote = 0;
        while (fromIndex < patternLength && (indexOfSingleQuote = pattern.indexOf(singleQuote,fromIndex)) != -1) {
            fromIndex = indexOfSingleQuote +1;
            countOfQuote += 1;
        }
        if (countOfQuote%2 != 0) {
            throw new ValidatorException("Odd number of single-quote characters are not allowed" );
        }
    }
    
}
