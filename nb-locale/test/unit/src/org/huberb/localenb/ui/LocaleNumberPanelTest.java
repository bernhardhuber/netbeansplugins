/*
 * LocaleNumberPanelTest.java
 * JUnit based test
 *
 * Created on 19. Mai 2007, 15:37
 */
package org.huberb.localenb.ui;

import java.util.Locale;
import org.junit.Test;

/**
 *
 * @author HuberB1
 */
public class LocaleNumberPanelTest {

    /**
     * Test of format method, of class org.huberb.localenb.ui.LocaleNumberPanel.
     */
    @Test
    public void testFormat() {
        Locale selectedLocale = Locale.getDefault();
        LocaleNumberPanel instance = new LocaleNumberPanel();
        instance.setPatterns(new String[]{"#"});

        instance.format(selectedLocale);
    }

}
