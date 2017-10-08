/*
 * ProgressHandleHelperTest.java
 * JUnit based test
 *
 * Created on 14. September 2006, 23:38
 */
package org.huberb.jdepend.misc;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author HuberB1
 */
public class ProgressHandleHelperTest {

    private ProgressHandleHelper instance;

    @Before
    public void setUp() throws Exception {
        this.instance = new ProgressHandleHelper(5, "test");
    }

    /**
     * Test of progress method, of class
     * org.huberb.jdepend.misc.ProgressHandleHelper.
     */
    @Test
    public void testProgress1() {
        instance.progress();
        instance.finish();
    }

    /**
     * Test of progress method, of class
     * org.huberb.jdepend.misc.ProgressHandleHelper.
     */
    @Test
    public void testProgress2() {
        for (int i = 0; i < 10; i++) {
            instance.progress();
        }
        instance.finish();
    }

    /**
     * Test of finish method, of class
     * org.huberb.jdepend.misc.ProgressHandleHelper.
     */
    @Test
    public void testFinish() {
        instance.finish();
    }

}
