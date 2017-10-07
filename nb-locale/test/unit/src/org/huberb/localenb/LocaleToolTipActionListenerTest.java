/*
 * LocaleToolTipActionListenerTest.java
 * JUnit based test
 *
 * Created on 19. Mai 2007, 10:21
 */

package org.huberb.localenb;

import junit.framework.*;
import java.awt.event.ActionEvent;
import java.util.Locale;
import javax.swing.JComboBox;
import org.huberb.localenb.helper.LocaleComboBoxModel;

/**
 *
 * @author HuberB1
 */
public class LocaleToolTipActionListenerTest extends TestCase {
    
    public LocaleToolTipActionListenerTest(String testName) {
        super(testName);
    }
    
    /**
     * Test of actionPerformed method, of class org.huberb.localenb.LocaleToolTipActionListener.
     */
    public void testActionPerformed() {
        LocaleComboBoxModel lcbm = new LocaleComboBoxModel();
        lcbm.setSelectedItem( Locale.getDefault() );
        JComboBox jcb = new JComboBox(lcbm);
        
        ActionEvent e = new ActionEvent(jcb, 0, "" );
        LocaleToolTipActionListener instance = new LocaleToolTipActionListener();
        
        instance.actionPerformed(e);
        
        String tooltip = jcb.getToolTipText();
        assertNotNull( tooltip );
        
    }
    
    /**
     * Test of formatLocale method, of class org.huberb.localenb.LocaleToolTipActionListener.
     */
    public void testFormatLocale() {
        Locale selectedLocale = Locale.getDefault();
        LocaleToolTipActionListener instance = new LocaleToolTipActionListener();
        
        String result = instance.formatLocale(selectedLocale);
        assertNotNull( result );
        assertTrue( result.length() > 0 );
        assertTrue( result.startsWith( "<html" ) );
    }
    
    /**
     * Test of getToolTipMessagePattern method, of class org.huberb.localenb.LocaleToolTipActionListener.
     */
    public void testGetToolTipMessagePattern() {
        LocaleToolTipActionListener instance = new LocaleToolTipActionListener();
        
        String result = instance.getToolTipMessagePattern();
        assertNotNull( result );
        assertTrue( result.length() > 0 );
        assertTrue( result.startsWith( "<html" ) );
    }
    
}
