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
import java.util.regex.Pattern;
import org.huberb.regexpnb.ui.RegexpPanel;
import org.openide.util.NbBundle;

/**
 *
 * @author berni3
 */
public class RegexpTask implements IRegexpTask {

    @Override
    public RegexpTaskResponse doProcess(RegexpTaskRequest req) {
        final String patternAsString = req.getPattern();
        final String inputAsString = req.getInputAsString();
        final int flags = req.getFlags();
        final Pattern pattern = Pattern.compile(patternAsString, flags);
        final Matcher matcher = pattern.matcher(inputAsString);

        final boolean matchesResult = req.getF().apply(matcher);
        final StringBuilder sb = buildResult(matcher, matchesResult);
        final RegexpTaskResponse resp = new RegexpTaskResponse(
                matchesResult,
                matcher, pattern,
                sb.toString());

        return resp;
    }

    /**
     * Build a result string from the matcher result
     *
     * @param matcher inspect the matcher props for build the result string
     * @param matchesResult the matcher result
     * @return StringBuilder the matcher result as an human readable result
     * string
     */
    protected StringBuilder buildResult(Matcher matcher, boolean matchesResult) {
        final StringBuilder sb = new StringBuilder();
        Object[] args;
        //---
        args = new Object[]{matchesResult, matcher.hasAnchoringBounds(), matcher.hasTransparentBounds()};
        sb.append(NbBundle.getMessage(RegexpPanel.class, "buildResultSummaryPatternRegexpPanel", args));
        if (matchesResult) {
            final String group = matcher.group();
            final int groupCount = matcher.groupCount();
            args = new Object[]{group, groupCount};
            sb.append(NbBundle.getMessage(RegexpPanel.class, "buildResultGroupPatternRegexpPanel", args));
            for (int i = 0; i <= groupCount; i++) {
                String groupDetail = matcher.group(i);
                args = new Object[]{i, groupDetail};
                sb.append(NbBundle.getMessage(RegexpPanel.class, "buildResultGroupMatchingPatternRegexpPanel", args));
            }
        }
        return sb;
    }

}
