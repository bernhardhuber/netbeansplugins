/*
 * Copyright 2017 Bernhard Huber
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.huberb.regexpnb.task;

import org.huberb.regexpnb.task.IRegexpTask;
import org.huberb.regexpnb.task.RegexpTask;
import java.util.regex.Matcher;
import org.huberb.regexpnb.task.IRegexpTask.RegexpTaskRequest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author berni3
 */
public class RegexpTaskTest {

    public RegexpTaskTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of doProcess method, of class RegexpTask.
     */
    @Test
    public void testDoProcess() {

        IRegexpTask.RegexpTaskRequest req = new RegexpTaskRequest("brown",
                "The brown fox jumps over the fence",
                0,
                (Matcher m) -> m.find());

        RegexpTask instance = new RegexpTask();
        IRegexpTask.RegexpTaskResponse result = instance.doProcess(req);
        assertNotNull(result);
        assertEquals("", result.getResult());
    }

    /**
     * Test of doProcess method, of class RegexpTask.
     */
    @Test
    public void testDoProcess_find() {

        IRegexpTask.RegexpTaskRequest req = new RegexpTaskRequest("brown",
                "The brown fox jumps over the fence",
                0,
                (Matcher m) -> m.find());

        RegexpTask instance = new RegexpTask();
        IRegexpTask.RegexpTaskResponse result = instance.doProcess(req);
        assertNotNull(result);
        assertEquals("", result.getResult());
    }

    /**
     * Test of doProcess method, of class RegexpTask.
     */
    @Test
    public void testDoProcess_match() {

        IRegexpTask.RegexpTaskRequest req = new RegexpTaskRequest("brown",
                "The brown fox jumps over the fence",
                0,
                (Matcher m) -> m.matches());

        RegexpTask instance = new RegexpTask();
        IRegexpTask.RegexpTaskResponse result = instance.doProcess(req);
        assertNotNull(result);
        assertEquals("", result.getResult());
    }

}
