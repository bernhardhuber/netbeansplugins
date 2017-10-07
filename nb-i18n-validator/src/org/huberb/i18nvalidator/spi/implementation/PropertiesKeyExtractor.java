/*
 * PropertiesKeyExtractor.java
 *
 * Created on 23. Mai 2007, 20:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.spi.implementation;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.huberb.i18nvalidator.api.ContextComposite;
import org.huberb.i18nvalidator.spi.ExtractorIF;
import org.huberb.i18nvalidator.api.FindInfoComposite;
import org.huberb.i18nvalidator.api.FindItem;
import org.huberb.i18nvalidator.api.ResourceLocation;

/**
 * Extract the property key from a property file.
 *
 * @author HuberB1
 */
public class PropertiesKeyExtractor implements ExtractorIF {
    private URL propertiesUrl;
    
    /** Creates a new instance of PropertiesKeyExtractor */
    public PropertiesKeyExtractor() {
    }
    /** Creates a new instance of PropertiesKeyExtractor */
    public PropertiesKeyExtractor(URL propertiesUrl) {
         this.propertiesUrl = propertiesUrl;
    }
    
    public URL getPropertiesUrl() {
        return propertiesUrl;
    }
    
    public void setPropertiesUrl(URL propertiesUrl) {
        this.propertiesUrl = propertiesUrl;
    }
    
    
    protected Properties loadProperties( ) throws IOException {
        Properties props = new Properties();
        InputStream is = this.propertiesUrl.openStream();
        try {
            props.load(is);
        } finally {
            is.close();
        }
        return props;
    }
    
    /**
     * Extract a FindInfoComponent
     */
    public FindInfoComposite extract() {
        FindInfoComposite fic = new FindInfoComposite();
        
        try {
            Properties props = loadProperties();
            fic = createFromProperties( props );
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fic;
    }
    
    protected FindInfoComposite createFromProperties( Properties propertis ) {
        FindInfoComposite fic = new FindInfoComposite();
        
        ResourceLocation rl = new ResourceLocation(propertiesUrl);
        ContextComposite cc = new ContextComposite(rl);
        for (Iterator<Map.Entry<Object,Object>> i = propertis.entrySet().iterator(); i.hasNext(); ) {
            Map.Entry me = i.next();
            String pattern = createPattern( (String)me.getKey() );
            FindItem findItem = new FindItem(pattern);
            cc.addItem(findItem);
        }
        fic.addItem( cc );
        return fic;
    }

    /**
     * Create a search pattern from a property key
     *
     * @param key the property key
     * @return String the search pattern
     */
    protected String createPattern( String key ) {
        final StringBuilder sb = new StringBuilder();
        sb.append( "\"" );
        sb.append( key );
        sb.append( "\"" );
        
        return sb.toString();
    }
}
