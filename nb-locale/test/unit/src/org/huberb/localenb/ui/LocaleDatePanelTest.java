/*
 * LocaleDatePanelTest.java
 * JUnit based test
 *
 * Created on 19. Mai 2007, 15:33
 */

package org.huberb.localenb.ui;

import java.util.Locale;
import org.junit.Test;

/**
 *
 * @author HuberB1
 */
public class LocaleDatePanelTest  {
    
    /**
     * Test of format method, of class org.huberb.localenb.ui.LocaleDatePanel.
     */
    @Test
    public void testFormat() {
        Locale selectedLocale = Locale.getDefault();
        LocaleDatePanel instance = new LocaleDatePanel();
        instance.setPatterns( new String[] { "yyyy-MM-dd HH:mm:ss" } );
        
        instance.format(selectedLocale);

    }

    
}
