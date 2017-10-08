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

import java.util.regex.Matcher;
import org.huberb.regexpnb.task.IRegexpTask.RegexpTaskRequest;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author berni3
 */
public class RegexpTaskTest {

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
        assertEquals(true, result.isMatches());
        assertEquals("matchesResult: true, hasAnchoringBounds: true, hasTransparentBounds: false\n"
                + "groupSummary: brown, groupCount: 0\n"
                + "groupDetail 0: brown\n", result.getSummary());
        assertEquals(4, result.getMatcher().start());
        assertEquals(9, result.getMatcher().end());
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
        assertEquals(true, result.isMatches());
        assertEquals("matchesResult: true, hasAnchoringBounds: true, hasTransparentBounds: false\n"
                + "groupSummary: brown, groupCount: 0\n"
                + "groupDetail 0: brown\n", result.getSummary());
        assertEquals(4, result.getMatcher().start());
        assertEquals(9, result.getMatcher().end());
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
        assertEquals(false, result.isMatches());
        assertEquals("matchesResult: false, hasAnchoringBounds: true, hasTransparentBounds: false\n", result.getSummary());
    }

}
