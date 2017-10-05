package org.huberb.nbjaranalyzer.options;

import java.io.Serializable;
import org.openide.filesystems.FileObject;

/**
 * This class describes an element in XsltFileObjectComboBoxModel
 */
public class LabelFileObjectBean implements Serializable {
    protected static final long serialVersionUID = 20060914091200L;    
    
    /** Create a new instance of LabelFileObjectBean
     */
    public LabelFileObjectBean() {
        this( null, null );
    }
    
    /** Create a new instance of LabelFileObjectBean
     */
    public LabelFileObjectBean(FileObject fo) {
        this( fo.getNameExt(), fo );
    }
    /** Create a new instance of LabelFileObjectBean
     */
    public LabelFileObjectBean(String label, FileObject fo) {
        this.setLabel(label);
        this.setFileObject( fo );
    }
    
    /**
     * Holds value of property label.
     */
    private String label;

    /**
     * Getter for property label.
     * @return Value of property label.
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Setter for property label.
     * @param label New value of property label.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Holds value of property fileObject.
     */
    private FileObject fileObject;

    /**
     * Getter for property fileObject.
     * @return Value of property fileObject.
     */
    public FileObject getFileObject() {
        return this.fileObject;
    }

    /**
     * Setter for property fileObject.
     * @param fileObject New value of property fileObject.
     */
    public void setFileObject(FileObject fileObject) {
        this.fileObject = fileObject;
    }

    //---
    public int hashCode() {
        return this.getFileObject().hashCode();
    }
    
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof LabelFileObjectBean) {
            LabelFileObjectBean other = (LabelFileObjectBean)o;
            return this.getFileObject().equals( other.getFileObject() );
        }
        return false;
    }
    
    public String toString() {
        return this.getLabel();
    }
}