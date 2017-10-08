/*
 * SearchVisitorTest.java
 * JUnit based test
 *
 * Created on 23. Mai 2007, 21:45
 */
package org.huberb.i18nvalidator.spi.implementation;

import java.io.File;
import java.net.MalformedURLException;
import org.huberb.i18nvalidator.api.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HuberB1
 */
public class SearchVisitorTest {

    /**
     * Test of visit method, of class
     * org.huberb.i18nvalidator.model.SearchVisitor.
     */
    @Test
    public void testVisit() throws MalformedURLException {

        FindInfoComposite fic = new FindInfoComposite();
        File theFile = new File("aFile.txt");
        ResourceLocation rl = new ResourceLocation(theFile.toURL());
        ContextComposite cc = new ContextComposite(rl);
        cc.addItem(new FindItem("B"));
        cc.addItem(new FindItem("1"));
        fic.addItem(cc);

        SearchVisitor instance = new SearchVisitor("ABC\nXYZ\n012\n");
        instance.visit(fic);

        final SearchInfoComposite sic = instance.getSearchInfoComposite();
        assertNotNull(sic);

        assertEquals(2, sic.getItems().size());
        ContextComposite cc2 = (ContextComposite) sic.getItems().get(0);
        SearchItem si = (SearchItem) cc2.getContext();
        assertEquals("B", si.getTitle());

        cc2 = (ContextComposite) sic.getItems().get(1);
        si = (SearchItem) cc2.getContext();
        assertEquals("1", si.getTitle());
    }

}
