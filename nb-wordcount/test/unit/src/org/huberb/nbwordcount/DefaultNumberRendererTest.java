/*
 * DefaultNumberRendererTest.java
 * JUnit based test
 *
 * Created on 25. Jänner 2006, 21:42
 */

package org.huberb.nbwordcount;

import java.util.Locale;
import junit.framework.*;

/**
 *
 * @author HuberB1
 */
public class DefaultNumberRendererTest extends TestCase {
    
    public DefaultNumberRendererTest(String testName) {
        super(testName);
    }
    
    private Locale theJVMDefaultLocale;
    
    protected void setUp() throws Exception {
        super.setUp();
        this.theJVMDefaultLocale = Locale.getDefault();
    }
    protected void tearDown() throws Exception {
        Locale.setDefault(this.theJVMDefaultLocale);
        super.tearDown();
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(DefaultNumberRendererTest.class);
        
        return suite;
    }

    /**
     * Test of setValue method, of class org.huberb.nbwordcount.DefaultNumberRenderer.
     */
    public void testSetValue() {
        Locale.setDefault( Locale.GERMAN );
        DefaultNumberRenderer instance = new DefaultNumberRenderer();
        
        Object value = new Double( 1.12 );
        instance.setValue(value);
        assertEquals( "1,12", instance.getText() );
        
        value = new Double( 1.4 );
        instance.setValue( value );
        assertEquals( "1,4", instance.getText() );
        
        value = new Double( 1.1234567890123 );
        instance.setValue(value);
        assertEquals( "1,12", instance.getText() );
    }

    /**
     * Test of setValue method, of class org.huberb.nbwordcount.DefaultNumberRenderer.
     */
    public void testSetValueLong() {        
        Locale.setDefault( Locale.GERMAN );
        DefaultNumberRenderer instance = new DefaultNumberRenderer();
        
        Object value;
        
        value = new Long( 12 );
        instance.setValue(value);
        assertEquals( "12", instance.getText() );
    }
    
    /**
     * Test of setPattern method, of class org.huberb.nbwordcount.DefaultNumberRenderer.
     * Test of getPattern method, of class org.huberb.nbwordcount.DefaultNumberRenderer.
     */
    public void testSetPattern() {
        String newPattern = "0.000";
        
        Locale.setDefault( Locale.GERMAN );
        DefaultNumberRenderer instance = new DefaultNumberRenderer();
        
        instance.setPattern(newPattern);

        assertEquals( newPattern, instance.getPattern() );
        
        instance.setValue( new Double( 1.1 ) );
        assertEquals( "1,100", instance.getText() );
    }


    public static void main(java.lang.String[] argList) {
        junit.textui.TestRunner.run(suite());
    }
    
}
