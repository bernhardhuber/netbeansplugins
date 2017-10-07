package org.huberb.i18nvalidator.api;

import java.io.Serializable;
import java.util.BitSet;

/**
 *
 * @author HuberB1
 */
public class FindItem extends AbstractComponent implements Serializable {
    protected static final long serialVersionUID = 20070523L;
    
    private String pattern;
    private BitSet modifiers;
    
    /** Creates a new instance of FindItem */
    public FindItem() {
        this("");
    }
    
    /** Creates a new instance of FindItem */
    public FindItem(String pattern) {
        this.pattern = pattern;
        this.modifiers = new BitSet();
    }
    
    public String getPattern() {
        return pattern;
    }
    
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    
    public void setModifier(Modifier modifier, boolean value) {
        this.modifiers.set( modifier.getMode(), value );
    }
    public boolean isModifierSet( Modifier modifier ) {
        return this.modifiers.get( modifier.getMode() );
    }
    
    @Override
    public int hashCode() {
        return this.pattern.hashCode() ^
                this.modifiers.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj instanceof FindItem) {
            FindItem otherObj = (FindItem)obj;
            
            boolean equals = false;
            equals = equals || this.getPattern().equals(otherObj.getPattern());
            equals = equals || this.modifiers.equals(otherObj.modifiers);
            return equals;
        }
        return false;
    }
    
    public static enum Modifier {
        IgnoreCase(0x01),
        Regexp(0x03);
        
        Modifier(int mode) {
            this.mode = mode;
        }
        private int mode;
        
        public int getMode() {
            return mode;
        }
        
    }
}