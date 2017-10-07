package org.huberb.i18nvalidator.api;

import java.io.Serializable;
import java.net.URL;

/**
 *
 * @author HuberB1
 */
public class ResourceLocation extends AbstractComponent implements Serializable {
    protected static final long serialVersionUID = 20070523L;
    
    private URL resourceUrl;
    private Long lineNumber;
    private Integer column;
    
    public ResourceLocation(URL resourceUrl) {
        this(resourceUrl, 0L, 0);
    }
    public ResourceLocation(URL resourceUrl, long lineNumber, int column) {
        this.resourceUrl = resourceUrl;
        this.lineNumber = lineNumber;
        this.column = column;
    }

    public URL getResourceUrl() {
        return resourceUrl;
    }
    
    public Long getLineNumber() {
        return lineNumber;
    }
    
    public Integer getColumn() {
        return column;
    }
    
    @Override
    public int hashCode() {
        if (this.resourceUrl == null) {
            return 0;
        }
        final String externalForm = resourceUrl.toString();
        return externalForm.hashCode() ^
                this.lineNumber.hashCode() ^
                this.column.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj instanceof ResourceLocation ) {
            ResourceLocation otherObj = (ResourceLocation)obj;
            
            if (this.resourceUrl == null && otherObj.resourceUrl == null) {
                return true;
            }
            
            boolean equals = false;
            final String externalForm = resourceUrl.toString();
            final String externalFormOtherObj = otherObj.toString();
            
            equals = equals || externalForm.equals(externalFormOtherObj);
            equals = equals || this.lineNumber.equals( otherObj.lineNumber );
            equals = equals || this.column.equals( otherObj.column );
            return equals;
        }
        return false;
    }
    
    
}