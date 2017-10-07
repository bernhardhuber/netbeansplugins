/*
 * PropertiesKeyExtractorTest.java
 * JUnit based test
 *
 * Created on 25. Mai 2007, 09:26
 */

package org.huberb.i18nvalidator.spi.implementation;

import java.io.IOException;
import java.net.URL;
import junit.framework.*;
import org.huberb.i18nvalidator.api.ContextComposite;
import org.huberb.i18nvalidator.api.FindInfoComposite;
import org.huberb.i18nvalidator.api.FindItem;
import org.huberb.i18nvalidator.api.ResourceLocation;

/**
 *
 * @author HuberB1
 */
public class PropertiesKeyExtractorTest extends TestCase {
    
    public PropertiesKeyExtractorTest(String testName) {
        super(testName);
    }


    /**
     * Test of extract method, of class org.huberb.i18nvalidator.matcher.PropertiesKeyExtractor.
     */
    public void testExtract() throws IOException {
        URL url = createUrl();
        PropertiesKeyExtractor instance = new PropertiesKeyExtractor(url);
        
        FindInfoComposite result = instance.extract();
        assertNotNull(result);
        
        assertEquals( 1, result.getItems().size() );
        ContextComposite cc = (ContextComposite)result.getItems().get(0);
        ResourceLocation rl = (ResourceLocation)cc.getContext();
        
        assertEquals( 2, cc.getItems().size() );
        FindItem fitem;
        fitem = (FindItem) cc.getItems().get(0);
        assertTrue( fitem.getPattern().matches( "\"key[12]\"" ) );
        fitem = (FindItem) cc.getItems().get(1);
        assertTrue( fitem.getPattern().matches( "\"key[12]\"" ) );
    }

    protected URL createUrl() {
        // load the props from classpath
        URL test1Properties = this.getClass().getResource("test1.properties");
        return test1Properties;
    }
}
