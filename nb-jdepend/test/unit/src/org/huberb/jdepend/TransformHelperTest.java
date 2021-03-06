/*
 * TransformHelperTest.java
 * JUnit based test
 *
 * Created on 13. August 2006, 19:09
 */
package org.huberb.jdepend;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

/**
 *
 * @author HuberB1
 */
public class TransformHelperTest {

    /**
     * Test of transformResult method, of class
     * org.huberb.jdepend.misc.TransformHelper.
     */
    @Test
    public void testTransformResult() {

        String result_2
                = "<?xml version=\"1.0\"?>"
                + "<JDepend>"
                + "<Packages>"
                + "<Package name=\"com.toy.anagrams.lib\">"
                + " <Stats>"
                + "  <TotalClasses>1</TotalClasses>"
                + "  <ConcreteClasses>1</ConcreteClasses>"
                + "  <AbstractClasses>0</AbstractClasses>"
                + "  <Ca>1</Ca>"
                + "  <Ce>0</Ce>"
                + "  <A>0</A>"
                + "  <I>0</I>"
                + "  <D>1</D>"
                + "  <V>1</V>"
                + " </Stats>"
                + " <AbstractClasses>"
                + " </AbstractClasses>"
                + " <ConcreteClasses>"
                + "  <Class sourceFile=\"WordLibrary.java\">"
                + "  com.toy.anagrams.lib.WordLibrary"
                + "  </Class>"
                + " </ConcreteClasses>"
                + " <DependsUpon>"
                + " </DependsUpon>"
                + " <UsedBy>"
                + "  <Package>com.toy.anagrams.ui</Package>"
                + " </UsedBy>"
                + "</Package>"
                + "<Package name=\"com.toy.anagrams.ui\">"
                + " <Stats>"
                + "  <TotalClasses>2</TotalClasses>"
                + "  <ConcreteClasses>2</ConcreteClasses>"
                + "  <AbstractClasses>0</AbstractClasses>"
                + "  <Ca>0</Ca>"
                + "  <Ce>1</Ce>"
                + "  <A>0</A>"
                + "  <I>1</I>"
                + "  <D>0</D>"
                + "  <V>1</V>"
                + " </Stats>"
                + " <AbstractClasses>"
                + " </AbstractClasses>"
                + " <ConcreteClasses>"
                + "  <Class sourceFile=\"About.java\">"
                + "   com.toy.anagrams.ui.About"
                + "  </Class>"
                + "  <Class sourceFile=\"Anagrams.java\">"
                + "   com.toy.anagrams.ui.Anagrams"
                + "  </Class>"
                + " </ConcreteClasses>"
                + " <DependsUpon>"
                + "  <Package>com.toy.anagrams.lib</Package>"
                + " </DependsUpon>"
                + " <UsedBy>"
                + " </UsedBy>"
                + "</Package>"
                + "</Packages>"
                + "<Cycles>"
                + "</Cycles>"
                + "</JDepend>";
        TransformHelper instance = new TransformHelper();

        String result = instance.transformResult(result_2);
        assertNotNull(result);
        assertTrue(result.length() > 0);
    }

    /**
     * Test of dumpResultToOutput method, of class
     * org.huberb.jdepend.misc.TransformHelper.
     */
    @Test
    public void testDumpResultToOutput() {
    }

}
