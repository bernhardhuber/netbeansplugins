/*
 * ManifestSyntaxTest.java
 * JUnit based test
 *
 * Created on 26. Juli 2006, 11:34
 */

package org.netbeans.modules.manifestsupport;

import java.util.Arrays;
import java.util.Iterator;
import junit.framework.TestCase;

import org.netbeans.editor.Syntax;
import org.netbeans.editor.TokenID;

public class ManifestSyntaxTest extends TestCase {
    
    public ManifestSyntaxTest(String testName) {
        super(testName);
    }
    
    public void testNextToken1() {
        doParse("Manifest-Version: 1.0", new TokenID[] {
            ManifestTokenContext.NAME,
            ManifestTokenContext.COLON,
            ManifestTokenContext.VALUE,
        });
    }
    
    public void testNextToken2() {
        doParse("Manifest-Version: 1.0\n\n" +
                "OpenIDE-Module: org.netbeans.modules.manifestsupport\n",
                new TokenID[] {
            ManifestTokenContext.NAME,
            ManifestTokenContext.COLON,
            ManifestTokenContext.VALUE,
            ManifestTokenContext.END_OF_LINE,
            ManifestTokenContext.END_OF_LINE,
            ManifestTokenContext.NAME,
            ManifestTokenContext.COLON,
            ManifestTokenContext.VALUE,
            ManifestTokenContext.END_OF_LINE,
        });
    }
    public void testNextToken3() {
        doParse("Manifest-Version: 1.0\r\r" +
                "OpenIDE-Module: org.netbeans.modules.manifestsupport\r",
                new TokenID[] {
            ManifestTokenContext.NAME,
            ManifestTokenContext.COLON,
            ManifestTokenContext.VALUE,
            ManifestTokenContext.END_OF_LINE,
            ManifestTokenContext.END_OF_LINE,
            ManifestTokenContext.NAME,
            ManifestTokenContext.COLON,
            ManifestTokenContext.VALUE,
            //ManifestTokenContext.END_OF_LINE,
        });
    }
    
    private void doParse(String m, TokenID[] expected) {
        Syntax s = new ManifestSyntax();
        s.load(null, m.toCharArray(), 0, m.length(), true, m.length());
        
        TokenID token = null;
        Iterator i = Arrays.asList(expected).iterator();
        do {
            token = s.nextToken();
            if (token != null) {
                if (!i.hasNext()) {
                    fail("More tokens returned than expected.");
                } else {
                    assertSame("Tokens differ", i.next(), token);
                }
            } else {
                assertFalse("More tokens expected than returned.", i.hasNext());
            }
            System.out.println(token);
        } while (token != null);
    }
}
