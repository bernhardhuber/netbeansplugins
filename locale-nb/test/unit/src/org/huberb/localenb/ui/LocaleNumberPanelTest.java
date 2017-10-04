/*
 * LocaleNumberPanelTest.java
 * JUnit based test
 *
 * Created on 19. Mai 2007, 15:37
 */

package org.huberb.localenb.ui;

import junit.framework.*;
import java.util.Locale;

/**
 *
 * @author HuberB1
 */
public class LocaleNumberPanelTest extends TestCase {
    
    public LocaleNumberPanelTest(String testName) {
        super(testName);
    }

    /**
     * Test of format method, of class org.huberb.localenb.ui.LocaleNumberPanel.
     */
    public void testFormat() {
        Locale selectedLocale = Locale.getDefault();
        LocaleNumberPanel instance = new LocaleNumberPanel();
        instance.setPatterns( new String[] {"#"} );
        
        instance.format(selectedLocale);        
    }

}
