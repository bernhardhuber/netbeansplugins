/*
 * TimeZoneHelperTest.java
 * JUnit based test
 *
 * Created on 03. März 2006, 08:21
 */
package org.huberb.timezone.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.swing.DefaultComboBoxModel;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

/**
 *
 * @author HuberB1
 */
public class TimeZoneHelperTest {

    /**
     * Test of setSourcePattern method, of class
     * org.huberb.timezone.helper.TimeZoneHelper. Test of getSourcePattern
     * method, of class org.huberb.timezone.helper.TimeZoneHelper.
     */
    @Test
    public void testSetPattern() {
        String newPattern = "yyyy-MM-dd";
        TimeZoneHelper instance = new TimeZoneHelper();

        instance.setSourcePattern(newPattern);

        String expResult = newPattern;
        String result = instance.getSourcePattern();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTimeZoneIds method, of class
     * org.huberb.timezone.helper.TimeZoneHelper.
     */
    @Test
    public void testGetTimeZoneIds() {
        TimeZoneHelper instance = new TimeZoneHelper();

        List<LabelTimeZoneBean> result = instance.getTimeZoneIds();
        assertTrue(result != null);
    }

    /**
     * Test of getDefaultTimeZoneIds method, of class
     * org.huberb.timezone.helper.TimeZoneHelper.
     */
    @Test
    public void testGetDefaultTimeZoneIds() {
        TimeZoneHelper instance = new TimeZoneHelper();

        LabelTimeZoneBean result = instance.getDefaultTimeZoneIds();
        assertTrue(result != null);
    }

    /**
     * Test of getTimeZoneComboBoxModel method, of class
     * org.huberb.timezone.helper.TimeZoneHelper.
     */
    @Test
    public void testGetTimeZoneComboBoxModel() {
        TimeZoneHelper instance = new TimeZoneHelper();

        DefaultComboBoxModel expResult = null;
        DefaultComboBoxModel result = instance.getTimeZoneComboBoxModel();
        assertTrue(result != null);

    }

    /**
     * Test of convertToDate method, of class
     * org.huberb.timezone.helper.TimeZoneHelper.
     */
    @Test
    public void testConvertToDate() {
        String dateAsString = "2006.03.03 09:52:00";
        TimeZoneHelper instance = new TimeZoneHelper();
        instance.setSourcePattern("yyyy.MM.dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();
        cal.set(2006, Calendar.MARCH, 3, 9, 52, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date expResult = cal.getTime();
        Date result = instance.convertToDate(dateAsString);
        assertEquals(expResult, result);
    }

    /**
     * Test of convertToTimeZone method, of class
     * org.huberb.timezone.helper.TimeZoneHelper.
     */
    @Test
    public void testConvertToTimeZone() {
        String tzAsString = "Europe/Vienna";
        TimeZoneHelper instance = new TimeZoneHelper();

        TimeZone expResult = TimeZone.getTimeZone(tzAsString);
        TimeZone result = instance.convertToTimeZone(tzAsString);
        assertEquals(expResult, result);
    }

    /**
     * Test of formatDate method, of class
     * org.huberb.timezone.helper.TimeZoneHelper.
     */
    @Test
    public void testFormatDate() {
        final String pattern = "yyyy.MM.dd HH:mm:ss z Z";
        final Calendar cal = Calendar.getInstance();
        cal.set(2006, Calendar.MARCH, 3, 9, 55, 0);
        cal.set(Calendar.MILLISECOND, 0);

        final Date date = cal.getTime();
        final TimeZone targetTimeZone = TimeZone.getTimeZone("Europe/Vienna");

        final TimeZoneHelper instance = new TimeZoneHelper();
        instance.setSourcePattern(pattern);
        instance.setTargetPattern(pattern);

        final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String expResult = sdf.format(date);

        String result = instance.formatDate(date, targetTimeZone);
        assertEquals(expResult, result);
    }

}
