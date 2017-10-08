/*
 * LocaleMessagePanelTest.java
 * JUnit based test
 *
 * Created on 19. Mai 2007, 15:36
 */

package org.huberb.localenb.ui;

import java.util.Locale;
import org.junit.Test;

/**
 *
 * @author HuberB1
 */
public class LocaleMessagePanelTest{
    
    /**
     * Test of format method, of class org.huberb.localenb.ui.LocaleMessagePanel.
     */
    @Test
    public void testFormat() {
        Locale selectedLocale = Locale.getDefault();
        LocaleMessagePanel instance = new LocaleMessagePanel();
        instance.setPatterns( new String[] {"ABC"} );
        
        instance.format(selectedLocale);        
    }

    
}
