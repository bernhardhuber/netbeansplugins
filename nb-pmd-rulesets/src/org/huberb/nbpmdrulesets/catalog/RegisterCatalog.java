/*
 * RegisterCatalog.java
 *
 * Created on 02. Juni 2007, 23:46
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbpmdrulesets.catalog;

import java.awt.Image;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.netbeans.modules.xml.catalog.spi.CatalogDescriptor;
import org.netbeans.modules.xml.catalog.spi.CatalogListener;
import org.netbeans.modules.xml.catalog.spi.CatalogReader;
import org.openide.util.Utilities;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Catalog to register Schema files to runtime tab
 *
 * @author HuberB1
 *
 */
public class RegisterCatalog implements CatalogReader, CatalogDescriptor, EntityResolver {
    // RULESET
    private static final String RULESET_XSD = "ruleset_xml_schema.xsd";
    private static final String RULESET = "http://pmd.sf.net/ruleset/1.0.0";
    private static final String RULESET_URL = "nbres:/org/huberb/nbpmdrulesets/resources/ruleset_xml_schema.xsd";
    private static final String RULESET_ID = "SCHEMA:" + RULESET;
    
    /** Creates a new instance of RegisterCatalog */
    public RegisterCatalog() {
    }
    
    @Override
    public Iterator getPublicIDs() {
        List<String> list = new ArrayList<>();
        list.add(RULESET_ID);
        
        return list.listIterator();
    }
    
    @Override
    public void refresh() {
    }
    
    @Override
    public String getSystemID(String publicId) {
        if(publicId.equals(RULESET_ID)) {
            return RULESET_URL;
        } else {
            return null;
        }
    }
    
    @Override
    public String resolveURI(String string) {
        return null;
    }
    
    @Override
    public String resolvePublic(String string) {
        return null;
    }
    
    @Override
    public void addCatalogListener(CatalogListener catalogListener) {
    }
    
    @Override
    public void removeCatalogListener(CatalogListener catalogListener) {
    }
    
    @Override
    public Image getIcon(int i) {
        return Utilities.loadImage("org/huberb/nbpmdrulesets/resources/PMDOptionsSettingsIcon.gif");
    }
    
    @Override
    public String getDisplayName() {
        return "PMD Ruleset Catalog";
    }
    
    @Override
    public String getShortDescription() {
        return "XML Catalog for PMD Ruleset Schema";
    }
    
    @Override
    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
    }
    
    @Override
    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
    }
    
    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        if (RULESET.equals(systemId)){
            return new org.xml.sax.InputSource(RULESET_URL);
        }
        if (systemId != null && systemId.endsWith(RULESET_XSD)){
            return new org.xml.sax.InputSource(RULESET_URL);
        }
        
        return null;
    }
}
