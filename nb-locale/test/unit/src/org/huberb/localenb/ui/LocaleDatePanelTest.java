/*
 * LocaleDatePanelTest.java
 * JUnit based test
 *
 * Created on 19. Mai 2007, 15:33
 */

package org.huberb.localenb.ui;

import junit.framework.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author HuberB1
 */
public class LocaleDatePanelTest extends TestCase {
    
    public LocaleDatePanelTest(String testName) {
        super(testName);
    }

    /**
     * Test of format method, of class org.huberb.localenb.ui.LocaleDatePanel.
     */
    public void testFormat() {
        Locale selectedLocale = Locale.getDefault();
        LocaleDatePanel instance = new LocaleDatePanel();
        instance.setPatterns( new String[] { "yyyy-MM-dd HH:mm:ss" } );
        
        instance.format(selectedLocale);

    }

    
}
