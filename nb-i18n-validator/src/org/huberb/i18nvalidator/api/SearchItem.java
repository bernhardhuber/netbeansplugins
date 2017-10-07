/*
 * SearchItem.java
 *
 * Created on 23. Mai 2007, 19:56
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.api;

import java.io.Serializable;

/**
 *
 * @author HuberB1
 */
public class SearchItem extends AbstractComponent implements Serializable {
    protected static final long serialVersionUID = 20070523L;

    private String title;
    private String snippet;
    
    /**
     * Creates a new instance of SearchItem
     */
    public SearchItem(String title) {
        this(title, "" );
    }
    /**
     * Creates a new instance of SearchItem
     */
    public SearchItem(String title, String snippet) {
        this.title = title;
        this.snippet = snippet;
    }

    public String getTitle() {
        return title;
    }

    public String getSnippet() {
        return snippet;
    }

    @Override
    public int hashCode() {
        return this.title.hashCode() ^
                this.snippet.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj instanceof SearchItem) {
            SearchItem otherObj = (SearchItem)obj;
            
            boolean equals = false;
            equals = equals || this.title.equals(otherObj.title);
            equals = equals || this.snippet.equals(otherObj.snippet);
            return equals;
        }
        return false;
    }


}
