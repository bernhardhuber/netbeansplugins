/*
 * SimpleRenderVisitorTest.java
 * JUnit based test
 *
 * Created on 02. Juni 2007, 14:08
 */
package org.huberb.i18nvalidator.spi.implementation;

import java.net.URL;
import org.huberb.i18nvalidator.api.ContextComposite;
import org.huberb.i18nvalidator.api.ResourceLocation;
import org.huberb.i18nvalidator.api.SearchInfoComposite;
import org.huberb.i18nvalidator.api.SearchItem;
import org.junit.Test;

/**
 *
 * @author HuberB1
 */
public class SimpleRenderVisitorTest {

    /**
     * Test of _visit method, of class
     * org.huberb.i18nvalidator.spi.implementation.SimpleRenderVisitor.
     */
    @Test
    public void testVisit() {
        URL resourceUrl = this.getClass().getResource("test1.properties");

        SearchInfoComposite sic = new SearchInfoComposite();
        sic.addItem(new SearchItem("T", "S"));
        sic.addItem(new ResourceLocation(resourceUrl));
        ContextComposite cc = new ContextComposite(new ResourceLocation(resourceUrl));
        sic.addItem(cc);

        SimpleRenderVisitor instance = new SimpleRenderVisitor();

        cc.accept(instance);
    }

}
