/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.localenb.helper;

import java.util.Locale;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author berni3
 */
public class SortedAvailableLocalesTest {

    public SortedAvailableLocalesTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testSortedAvailableLocales() {
        Locale[] sortedLocales = LocaleComboBoxModel.sortedAvailableLocales();
        assertTrue(sortedLocales.length > 2);

        for (int i = 0; i < sortedLocales.length - 1; i += 1) {
            String dp1 = sortedLocales[i].getDisplayName();
            String dp2 = sortedLocales[i + 1].getDisplayName();

            String m = "" + dp1 + " <=> " + dp2;
            System.out.println(m);

            assertTrue(m, dp1.compareTo(dp2) <= 0);
        }
    }
}
