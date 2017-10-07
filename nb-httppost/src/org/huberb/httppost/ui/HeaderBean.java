package org.huberb.httppost.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// End of variables declaration

/**
 * A simple bean encapsulating the header name, and header attributes
 */
public class HeaderBean implements Serializable, Comparable {
    private static final long serialVersionUID = 20060925203500L;
    
    private String headerName;
    private String[] headerValues;
    
    /**
     * Creates an new instance of HeaderBean
     */
    public HeaderBean() {
        this( "XXX-Comment", "HttpPost" );
    }
    /**
     * Creates an new instance of HeaderBean
     */
    public HeaderBean(String name, String value) {
        setHeaderName(name);
        this.headerValues = createHeaderValues(value);
    }
    /**
     * Creates an new instance of HeaderBean
     */
    public HeaderBean(String name, String[] values) {
        setHeaderName(name);
        this.headerValues = (String[]) values.clone();
    }
    protected void setHeaderName(String newHeaderName) {
        if (newHeaderName == null) {
            newHeaderName = "";
        }
        this.headerName = newHeaderName;
    }
    public String getHeaderName() {
        return this.headerName;
    }
    public String[] getHeaderValues() {
        String[] values = (String[])this.headerValues.clone();
        return values;
    }
    private static final String DELIM = ",";
    public String getHeaderValuesAsCSV() {
        StringBuilder sb = new StringBuilder();
        final int length = this.headerValues.length;
        for (int i = 0; i < length; i++) {
            sb.append( this.headerValues[i] );
            if (i +1 < length) {
                sb.append( DELIM );
            }
        }
        return sb.toString();
    }
    public List<String> getHeaderValueAsList() {
        final List<String> valuesAsList = (List<String>)Arrays.asList( this.getHeaderValues() );
        return valuesAsList;
    }
    protected String[] createHeaderValues(String value) {
        String[] values = value.split(DELIM);
        return values;
    }
    
    public int hashCode() {
        return this.headerName.hashCode();
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof HeaderBean) {
            HeaderBean other = (HeaderBean)obj;
            return this.getHeaderName().equals( other.getHeaderName() );
        }
        return false;
    }
    public int compareTo(Object o) {
        HeaderBean other = (HeaderBean)o;
        return this.getHeaderName().compareTo( other.getHeaderName() );
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( this.getHeaderName() );
        sb.append( ":" );
        sb.append( this.getHeaderValuesAsCSV() );
        return sb.toString();
    }
}