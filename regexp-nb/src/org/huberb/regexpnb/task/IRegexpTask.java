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

import java.util.function.Function;
import java.util.regex.Matcher;

/**
 *
 * @author berni3
 */
interface IRegexpTask {

    public static class RegexpTaskRequest {

        private final String pattern;
        private final String inputAsString;
        private final int flags;
        private final Function<Matcher, Boolean> f;

        public RegexpTaskRequest(String pattern, String inputAsString, int flags, Function<Matcher, Boolean> f) {
            this.pattern = pattern;
            this.inputAsString = inputAsString;
            this.flags = flags;
            this.f = f;
        }

        public String getPattern() {
            return pattern;
        }

        public String getInputAsString() {
            return inputAsString;
        }

        public int getFlags() {
            return flags;
        }

        public Function<Matcher, Boolean> getF() {
            return f;
        }

    }

    public static class RegexpTaskResponse {

        String result;

        public RegexpTaskResponse(String result) {
            this.result = result;
        }

        public String getResult() {
            return result;
        }
    }

    RegexpTaskResponse doProcess(RegexpTaskRequest req);

}
