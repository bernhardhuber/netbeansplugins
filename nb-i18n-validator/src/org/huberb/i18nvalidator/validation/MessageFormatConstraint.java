/*
 * MessageFormatConstraint.java
 *
 * Created on 21. Mai 2007, 18:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.validation;

import java.text.DateFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author HuberB1
 */
public class MessageFormatConstraint implements ConstraintIF {
    private Locale locale;
    private String result;
    
    /** Creates a new instance of MessageFormatConstraint */
    public MessageFormatConstraint() {
        this.setLocale(Locale.getDefault());
    }
    
    public Locale getLocale() {
        return locale;
    }
    
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    public String getResult() {
        return result;
    }
    
    
    public void constraint( Object obj ) throws ValidatorException {        
        String pattern = (String)obj;
        try {
            MessageFormat mf = new MessageFormat(pattern, locale);
            
            Format[] formats = mf.getFormatsByArgumentIndex();
            Object[] args = new Object[formats.length];
            for (int i = 0; i < formats.length; i++ ) {
                Format format = formats[i];
                args[i] = createRandomArguments(format);
            }
            this.result = mf.format(args);
        } catch (IllegalArgumentException iae) {
            throw new ValidatorException( "Validation of pattern " + pattern + " failed!", iae );
        }
    }
    
    /**
     * Create random arguments for a message format
     */
    protected Object createRandomArguments(Format format) {
        Object value = null;
        if (format instanceof NumberFormat) {
            value = (Math.random() - 0.5) * 10000.0;
        } else if (format instanceof DateFormat) {
            value = new Date();
        } else {
            value = "XXX";
        }
        return value;
    }
}
