/*
 * LabelValueBean.java
 *
 * Created on 21. Februar 2006, 19:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.timezone.helper;

import java.io.Serializable;

/**
 * A generic bean for lists, and combo box providing a label,
 * and an arbitrary object associated with that label
 * @author HuberB1
 */
public class LabelValueBean<T> implements Serializable, Comparable {
    private static final long serialVersionUID = 20060223213600L;
    
    private String label;
    private T value;
    
    /** Creates a new instance of LabelValueBean */
    public LabelValueBean() {
    }
    
    /** Creates a new instance of LabelValueBean */
    public LabelValueBean(String newLabel, T newValue) {
        this.setLabelAndValue(newLabel,newValue);
    }
    
    public void setLabel( String newLabel ) {
        this.label = newLabel;
    }
    
    public String getLabel() {
        return this.label;
    }
    public T getValue() {
        return value;
    }
    public void setValue( T newValue ) {
        this.value = newValue;
    }
    
    private void setLabelAndValue( String newLabel, T newValue ) {
        this.label = newLabel;
        this.value = newValue;
    }
    
    /**
     * Show the label value, as the ComboBox uses toString() for reendering
     *
     * @return String the label value
     */
    @Override
    public String toString() {
        return this.label;
    }
    
    @Override
    public int hashCode() {
        return this.getValue().hashCode();
    }
    @Override
    public boolean equals( Object obj ) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj instanceof LabelValueBean) {
            LabelValueBean other = (LabelValueBean)obj;
            return this.getValue().equals( other.getValue() );
        }
        return false;
    }
    @Override
    public int compareTo(Object o) {
        if (o == this) {
            return 0;
        }
        if (o != null && o instanceof LabelValueBean) {
            LabelValueBean lvb = (LabelValueBean)o;
            if (this.getLabel() != null && lvb.getLabel() != null) {
                return this.getLabel().compareTo( lvb.getLabel() );
            }
        }
        return 0;
    }
}
