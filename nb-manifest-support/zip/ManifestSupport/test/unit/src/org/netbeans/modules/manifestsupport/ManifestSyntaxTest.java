package org.netbeans.modules.manifestsupport;
/*
 * ManifestSyntaxTest.java
 * JUnit based test
 *
 * Created on July 20, 2005, 12:49 PM
 */

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import junit.framework.TestCase;
import org.netbeans.editor.Syntax;
import org.netbeans.editor.TokenID;
import org.netbeans.modules.manifestsupport.*;

public class ManifestSyntaxTest extends TestCase {
    
    public ManifestSyntaxTest(String testName) {
        super(testName);
    }
    
    public void testNextToken() {
        doParse("Manifest-Version:1.0", Arrays.asList(new TokenID[] {
            ManifestTokenContext.NAME,
            ManifestTokenContext.COLON,
            ManifestTokenContext.VALUE,
        }));
    }
        
    public void doParse(String m, List expected) {
        Syntax s = new ManifestSyntax();
        s.load(null, m.toCharArray(), 0, m.length(), true, m.length());
        
        TokenID token = null;
        Iterator i = expected.iterator();
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
