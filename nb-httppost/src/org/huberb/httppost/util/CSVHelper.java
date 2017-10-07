/*
 * CSVHelper.java
 *
 * Created on 26. September 2006, 19:17
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.httppost.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HuberB1
 */
public class CSVHelper {

    private Map<String, List<String>> requestProperties;
    private List<String> values;

    /**
     * Creates a new instance of CSVHelper
     */
    public CSVHelper() {
    }

    private final String DELIM1 = ": ";
    private final String DELIM2 = ",";
    private final String DELIM3 = "\n";

    public CSVHelper requestProperties(Map<String, List<String>> requestProperties) {
        this.requestProperties = requestProperties;
        return this;
    }

    public CSVHelper values(List<String> values) {
        this.values = values;
        return this;
    }

    public StringBuilder build() {
        final StringBuilder sb;
        if (this.requestProperties != null) {
            sb = flattenHttpHeaderMap(requestProperties);
        } else if (this.values != null) {
            sb = flattenList(values);
        } else {
            sb = new StringBuilder();
        }
        return sb;
    }

    protected StringBuilder flattenHttpHeaderMap(Map<String, List<String>> requestProperties) {
        final StringBuilder sb = new StringBuilder();
        if (requestProperties == null) {
            return sb;
        }

        for (Iterator<Map.Entry<String, List<String>>> i = requestProperties.entrySet().iterator(); i.hasNext();) {
            final Map.Entry<String, List<String>> me = i.next();
            final String key = me.getKey();
            if (key != null) {
                sb.append(key);
                sb.append(DELIM1);
                final List<String> values = me.getValue();
                sb.append(flattenList(values));
                sb.append(DELIM3);
            }
        }
        return sb;
    }

    protected StringBuilder flattenList(List<String> values) {
        final StringBuilder sb = new StringBuilder();
        if (values != null) {
            int i = 0;
            for (String value : values) {
                sb.append(value);
                i += 1;
                if (i < values.size()) {
                    sb.append(DELIM2);
                }
            }
        }
        return sb;
    }
}
