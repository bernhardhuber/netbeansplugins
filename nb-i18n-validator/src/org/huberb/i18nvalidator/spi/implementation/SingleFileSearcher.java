/*
 * SingleFileSearcher.java
 *
 * Created on 23. Mai 2007, 21:07
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.spi.implementation;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import org.huberb.i18nvalidator.api.ContextComposite;
import org.huberb.i18nvalidator.api.FindInfoComposite;
import org.huberb.i18nvalidator.api.ResourceLocation;
import org.huberb.i18nvalidator.api.SearchInfoComposite;
import org.huberb.i18nvalidator.spi.SearcherIF;

/**
 *
 * @author HuberB1
 */
public class SingleFileSearcher implements SearcherIF {
    private File theFile;
    private SearchVisitor searchVisitor;
    
    /**
     * Creates a new instance of SingleFileSearcher
     */
    public SingleFileSearcher(File theFile) {
        this.theFile = theFile;
        this.searchVisitor = new SearchVisitor();
    }

    public SearchVisitor getSearchVisitor() {
        return searchVisitor;
    }
    
    
    public SearchInfoComposite search(FindInfoComposite fic) {
        
        SearchInfoComposite totalSic = new SearchInfoComposite();
        try {
            FileReader fr = new FileReader(theFile);
            scan( fr, fic, totalSic );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return totalSic;
    }
    
    protected void scan(Reader reader, FindInfoComposite fic, SearchInfoComposite totalSic) throws IOException {
        LineNumberReader lnr = new LineNumberReader( reader );
        try {
            
            String line;
            while ((line = lnr.readLine()) != null) {
                this.searchVisitor.setContent( line );
                
                fic.accept(this.searchVisitor);
                SearchInfoComposite sic = searchVisitor.getSearchInfoComposite();
                int numberOfSearchItems = searchVisitor.getNumberOfSearchItems();
                if (numberOfSearchItems > 0 && sic != null && sic.getSize() > 0) {
                    int lineNumber = lnr.getLineNumber();
                    ResourceLocation rl = new ResourceLocation(theFile.toURL(), lineNumber, 0 );
                    
                    ContextComposite cc = new ContextComposite(rl);
                    cc.addItem(sic);
                    
                    totalSic.addItem( cc );
                }
            }
        } finally {
            lnr.close();
        }
        
    }
}
