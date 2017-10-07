/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.httppost.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author berni3
 */
public class CSVHelperTest {

    CSVHelper instance;

    @Before
    public void setUp() {
        instance = new CSVHelper();
    }

    /**
     * Test of requestProperties method, of class CSVHelper.
     */
    @Test
    public void testRequestProperties() {
        Map<String, List<String>> requestProperties = null;
        CSVHelper result = instance.requestProperties(requestProperties);
        assertNotNull(result);
    }

    /**
     * Test of values method, of class CSVHelper.
     */
    @Test
    public void testValues() {
        List<String> values = null;
        CSVHelper result = instance.values(values);
        assertNotNull(result);
    }

    /**
     * Test of build method, of class {@link CSVHelper}.
     */
    @Test
    public void testBuild() {
        StringBuilder result = instance.build();
        assertNotNull(result);
    }

    /**
     * Test of build method, of class {@link CSVHelper}.
     */
    @Test
    public void testBuild_values() {
        StringBuilder result = instance.values(Arrays.asList("elem1", "elem2")).build();
        assertNotNull(result);
        assertEquals("elem1,elem2", result.toString());
    }

    /**
     * Test of build method, of class {@link CSVHelper}.
     */
    @Test
    public void testBuild_requestProperties() {
        Map<String, List<String>> requestProperties = new HashMap<>();
        requestProperties.put("key1", Arrays.asList("key1Value1", "key1Value2"));
        requestProperties.put("key2", Arrays.asList("key2Value1", "key2Value2"));
        StringBuilder result = instance.requestProperties(requestProperties).build();
        assertNotNull(result);
        assertEquals("key1: key1Value1,key1Value2\n"
                + "key2: key2Value1,key2Value2\n", result.toString());
    }

}
