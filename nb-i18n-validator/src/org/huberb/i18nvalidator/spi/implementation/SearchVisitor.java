package org.huberb.i18nvalidator.spi.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.huberb.i18nvalidator.api.*;
import org.huberb.i18nvalidator.spi.*;

/**
 *
 * @author HuberB1
 */
public class SearchVisitor extends AbstractVisitor {
    
    private String content;
    private SearchInfoComposite searchInfoComposite;
    private Integer numberOfSearchItems;
    
    private RePatternWrapper rePatternWrapper = new RePatternWrapper();
    
    //-------------------------------------------------------------------------
    public SearchVisitor() {
        this("");
    }
    public SearchVisitor(String content) {
        searchInfoComposite = new SearchInfoComposite();
        this.content = content;
        this.numberOfSearchItems = 0;
    }
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
        this.numberOfSearchItems = 0;
    }
    
    
    //-------------------------------------------------------------------------
    /**
     * Get the SearchInfoComposite built by a visit.
     */
    public SearchInfoComposite getSearchInfoComposite() {
        return searchInfoComposite;
    }
    
    private ResourceLocation lastResourceLocation;
    public void _visit( ResourceLocation rl ) {
        this.lastResourceLocation = rl;
    }
    
    public void _visit( FindItem fi ) {
        
        SearchItem si = createSearchItem( this.content, fi );
        if (si != null) {
            ContextComposite cc = new ContextComposite(si);
            if (this.lastResourceLocation != null) {
                cc.addItem( this.lastResourceLocation );
            }
            cc.addItem( fi );
            // callback SearchItem created
            this.searchInfoComposite.addItem( cc );
            incrementNumberOfSearchItems();
            
            this.pcs.firePropertyChange("searchItem", null, si );
            
        }
    }
    
    protected SearchItem createSearchItem( String content, FindItem fi ) {
        SearchItem si = null;
        
        RePatternInfo rpi = new RePatternInfo(fi);
        Pattern rePattern = this.rePatternWrapper.createPattern(rpi);
        Matcher reMatcher = rePattern.matcher(content);
        boolean matcherFind = reMatcher.find();
        if (matcherFind) {
            int startIndex = reMatcher.start();
            int endIndex = reMatcher.end();
            
            int snippetBeginIndex = Math.max( 0, startIndex -100);
            int snippetEndIndex = Math.min( content.length(), endIndex +100 );
            
            String snippet = content.substring( snippetBeginIndex, snippetEndIndex);
            si = new SearchItem( reMatcher.group(), snippet);
        }
        return si;
    }
    
    //-------------------------------------------------------------------------
    public static class RePatternWrapper {
        private Map<RePatternInfo,Pattern> patternMap;
        RePatternWrapper() {
            this.patternMap = new HashMap<RePatternInfo,Pattern>();
        }
        Pattern createPattern(RePatternInfo rpi) {
            Pattern rePattern = this.patternMap.get(rpi);
            if (rePattern == null) {
                rePattern = Pattern.compile(rpi.getPattern(),rpi.getFlags());
                this.patternMap.put( rpi, rePattern );
            }
            return rePattern;
        }
    }
    public static class RePatternInfo {
        private String pattern;
        private int flags;
        
        public RePatternInfo(String pattern) {
            this(pattern,0);
        }
        
        public RePatternInfo(String pattern, int flags ) {
            this.pattern = pattern;
            this.flags = flags;
        }
        
        public RePatternInfo(FindItem fi) {
            int theFlags = 0;
            String thePattern = fi.getPattern();
            if (!fi.isModifierSet(FindItem.Modifier.Regexp)) {
                thePattern = Pattern.quote(thePattern);
            }
            if (fi.isModifierSet(FindItem.Modifier.IgnoreCase)) {
                theFlags = theFlags | Pattern.CASE_INSENSITIVE;
            }
            
            this.pattern = thePattern;
            this.flags = theFlags;
            
        }
        public int getFlags() {
            return flags;
        }
        
        public void setFlags(int flags) {
            this.flags = flags;
        }
        
        public String getPattern() {
            return pattern;
        }
        
        public void setPattern(String pattern) {
            this.pattern = pattern;
        }
        
        @Override
        public int hashCode() {
            return this.pattern.hashCode() ^ this.flags;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj != null && obj instanceof RePatternInfo) {
                RePatternInfo otherObj = (RePatternInfo)obj;
                boolean equals = false;
                equals = equals || this.pattern.equals( otherObj.pattern);
                equals = equals || this.flags == otherObj.flags;
                return equals;
            }
            return false;
        }
        
        
    }
    
    public Integer getNumberOfSearchItems() {
        return this.numberOfSearchItems;
    }
    protected void incrementNumberOfSearchItems() {
        Integer oldNumberOfSearchItems = this.numberOfSearchItems;
        this.numberOfSearchItems++;
        
        this.pcs.firePropertyChange( "numberOfSearchItems", oldNumberOfSearchItems, this.numberOfSearchItems );
    }
}