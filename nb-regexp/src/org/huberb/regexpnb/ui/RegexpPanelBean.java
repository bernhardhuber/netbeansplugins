/*
 * RegexpPanelBean.java
 *
 * Created on 22. Oktober 2005, 09:50
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.regexpnb.ui;

import java.io.Serializable;

/**
 *
 * @author HuberB1
 */
public class RegexpPanelBean implements Serializable {
    private final static long serialVersionUID = 20051022095200L;
    
    /** Creates a new instance of RegexpPanelBean */
    public RegexpPanelBean() {
    }

    /** Creates a new instance of RegexpPanelBean */
    public RegexpPanelBean(String newName) {
        if (name == null) {
            throw new IllegalArgumentException( "name is null, name is invalid");
        }
        this.name = newName;
    }
    
    public RegexpPanelBean( String newName, String newDescription,
            String newRegularExpressionPattern,
            String newInputString ) {
        this( newName );
        this.setDescription( newDescription );
        this.setRegularExpressionPattern( newRegularExpressionPattern );
        this.setInputString( newInputString );
    }
    
    /**
     * Holds value of property inputString.
     */
    private String inputString;

    /**
     * Getter for property inputString.
     * @return Value of property inputString.
     */
    public String getInputString() {
        return this.inputString;
    }

    /**
     * Setter for property inputString.
     * @param inputString New value of property inputString.
     */
    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    /**
     * Holds value of property regularExpressionPattern.
     */
    private String regularExpressionPattern;

    /**
     * Getter for property regularExpressionPattern.
     * @return Value of property regularExpressionPattern.
     */
    public String getRegularExpressionPattern() {
        return this.regularExpressionPattern;
    }

    /**
     * Setter for property regularExpressionPattern.
     * @param regularExpressionPattern New value of property regularExpressionPattern.
     */
    public void setRegularExpressionPattern(String regularExpressionPattern) {
        this.regularExpressionPattern = regularExpressionPattern;
    }

    /**
     * Holds value of property name.
     */
    private String name;

    /**
     * Getter for property name.
     * @return Value of property name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for property name.
     * @param name New value of property name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Holds value of property description.
     */
    private String description;

    /**
     * Getter for property description.
     * @return Value of property description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for property description.
     * @param description New value of property description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
    @Override
    public boolean equals( Object obj ) {
        boolean isEqual = false;
        if (obj instanceof RegexpPanelBean) {
            RegexpPanelBean rpb = (RegexpPanelBean)obj;
            isEqual = rpb.getName().equals( this.getName() );
        }
        return isEqual;
    }

    // TODO add match, and find methods
    
}
