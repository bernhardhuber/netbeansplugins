/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.localenb.options;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author berni3
 */
public class StringsStringEncoderDecoderTest {

    private StringsStringEncoderDecoder instance;

    @BeforeClass
    public static void setUpClass() {
    }

    @Before
    public void setUp() {
        instance = new StringsStringEncoderDecoder();
    }

    @Test
    public void testEncodeArrayStringDecodeToArrayString() {
        String encoded = instance.encodeArrayString(new String[]{"a", "b", "c"});
        String[] result = instance.decodeToArrayString(encoded);
        assertEquals(3, result.length);
        assertEquals("a", result[0]);
        assertEquals("b", result[1]);
        assertEquals("c", result[2]);
    }

    @Test
    public void testEncodeArrayStringDecodeToArrayString_empty() {
        String encoded = instance.encodeArrayString(new String[0]);
        assertEquals(0, encoded.length());
        String[] result = instance.decodeToArrayString(encoded);
        assertEquals(0, result.length);
    }

    @Test
    public void testEncodeListStringDecodeToListString() {
        String encoded = instance.encodeListString(Arrays.asList("a", "b", "c"));
        List<String> result = instance.decodeToListString(encoded);
        assertEquals(3, result.size());
        assertEquals("a", result.get(0));
        assertEquals("b", result.get(1));
        assertEquals("c", result.get(2));
    }

    @Test
    public void testEncodeListStringDecodeToListString_empty() {
        String encoded = instance.encodeListString(Collections.emptyList());
        assertEquals(0, encoded.length());
        List<String> result = instance.decodeToListString(encoded);
        assertEquals(0, result.size());
    }
}
