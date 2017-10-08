/*
 * RandomPasswordSuggestionerTest.java
 * JUnit based test
 *
 * Created on 24. Februar 2007, 07:28
 */
package org.huberb.randompassword.helper;

import java.util.ArrayList;
import java.util.List;
import junit.framework.AssertionFailedError;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HuberB1
 */
public class RandomPasswordSuggestionerTest {

    /**
     * Test of suggestPassword method, of class RandomPasswordSuggestioner.
     */
    @Test
    public void testSuggestPassword1() {

        List<CharacterClass> allowedCharacterClasses = new ArrayList<>();
        allowedCharacterClasses.add(CharacterClass.DIGITS);
        allowedCharacterClasses.add(CharacterClass.UPPER_CASE);
        RandomPasswordSuggestioner instance = new RandomPasswordSuggestioner();

        for (int i = 1; i <= 100; i++) {
            int passwordLength = i;
            String result = instance.suggestPassword(passwordLength, allowedCharacterClasses);

            assertEquals(passwordLength, result.length());
            assertContainsOnly(result, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        }
    }

    /**
     * Test of suggestPassword method, of class RandomPasswordSuggestioner.
     */
    @Test
    public void testSuggestPassword2() {
        RandomPasswordSuggestioner instance = new RandomPasswordSuggestioner();

        for (int i = 1; i <= 100; i++) {
            int passwordLength = i;
            String result = instance.suggestPassword(passwordLength, "abc");

            assertEquals(passwordLength, result.length());
            assertContainsOnly(result, "abc");
        }
    }

    /**
     * Assert that text contains only characters defined in allowed
     */
    private void assertContainsOnly(String text, String allowed) {

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int indexOf = allowed.indexOf(c);

            boolean containsOnly = (indexOf > -1);
            if (!containsOnly) {
                throw new AssertionFailedError("Bad character '" + c
                        + "' at position " + i
                        + " in text '" + text + "', "
                        + " allowed '" + allowed + "'");
            }
        }
    }
}
