/*
 * LocaleMessagePanelTest.java
 * JUnit based test
 *
 * Created on 19. Mai 2007, 15:36
 */

package org.huberb.localenb.ui;

import junit.framework.*;
import java.text.Format;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author HuberB1
 */
public class LocaleMessagePanelTest extends TestCase {
    
    public LocaleMessagePanelTest(String testName) {
        super(testName);
    }

    /**
     * Test of format method, of class org.huberb.localenb.ui.LocaleMessagePanel.
     */
    public void testFormat() {
        Locale selectedLocale = Locale.getDefault();
        LocaleMessagePanel instance = new LocaleMessagePanel();
        instance.setPatterns( new String[] {"ABC"} );
        
        instance.format(selectedLocale);        
    }

    
}
