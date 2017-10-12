/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.localenb.options;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author berni3
 */
public class StringsStringEncoderDecoder {

    private final String delim = "\\\n";

    public String encodeArrayString(String[] elems) {
        return encodeListString(Arrays.asList(elems));
    }

    public String encodeListString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s).append(delim);
        }
        return sb.toString();
    }

    public String[] decodeToArrayString(String s) {
        List<String> resultAsList = decodeToListString(s);
        String[] result = resultAsList.toArray(new String[resultAsList.size()]);
        return result;
    }

    public List<String> decodeToListString(String s) {
        List<String> result = new ArrayList<>();
        for (StringTokenizer st = new StringTokenizer(s, delim); st.hasMoreTokens();) {
            String elem = st.nextToken();
            String elemTrimmed = elem.trim();
            result.add(elemTrimmed);
        }
        return result;
    }

}
