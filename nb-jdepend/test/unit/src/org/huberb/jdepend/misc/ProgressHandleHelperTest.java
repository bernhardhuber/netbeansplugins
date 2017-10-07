/*
 * ProgressHandleHelperTest.java
 * JUnit based test
 *
 * Created on 14. September 2006, 23:38
 */

package org.huberb.jdepend.misc;

import junit.framework.*;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;

/**
 *
 * @author HuberB1
 */
public class ProgressHandleHelperTest extends TestCase {
    private ProgressHandleHelper instance;
    public ProgressHandleHelperTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
        this.instance = new ProgressHandleHelper(5,"test");
    }
    
    protected void tearDown() throws Exception {
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(ProgressHandleHelperTest.class);
        
        return suite;
    }
    
    /**
     * Test of progress method, of class org.huberb.jdepend.misc.ProgressHandleHelper.
     */
    public void testProgress1() {
        instance.progress();
        instance.finish();
    }
    
    /**
     * Test of progress method, of class org.huberb.jdepend.misc.ProgressHandleHelper.
     */
    public void testProgress2() {
        for (int i = 0; i < 10; i++ ) {
            instance.progress();
        }
        instance.finish();
    }
    /**
     * Test of finish method, of class org.huberb.jdepend.misc.ProgressHandleHelper.
     */
    public void testFinish() {
        instance.finish();
    }
    
}
