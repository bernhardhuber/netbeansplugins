/*
 * PolicySyntaxTest.java
 * JUnit based test
 *
 * Created on 31. Juli 2006, 14:06
 */
package org.netbeans.modules.policysupport;

import java.util.Arrays;
import java.util.Iterator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.netbeans.editor.Syntax;
import org.netbeans.editor.TokenID;

/**
 *
 * @author HuberB1
 */
public class PolicySyntaxTest {

    /**
     * Test of parseToken method, of class
     * org.netbeans.modules.policysupport.PolicySyntax.
     */
    @Test
    public void testParseToken1() {
        final String s = ""
                + "grant codeBase \"file:${java.home}/lib/-\" {" + "\n"
                + "    permission java.security.AllPermission;" + "\n"
                + "};";
        doParse(s, new TokenID[]{
            PolicyTokenContext.KEYWORD, // grant
            PolicyTokenContext.WHITESPACE,
            PolicyTokenContext.KEYWORD, // codeBase
            PolicyTokenContext.WHITESPACE,
            PolicyTokenContext.STRING,
            PolicyTokenContext.WHITESPACE,
            PolicyTokenContext.SPECIALCHAR, // {

            PolicyTokenContext.WHITESPACE,
            PolicyTokenContext.KEYWORD, // permission
            PolicyTokenContext.WHITESPACE,
            PolicyTokenContext.PERMISSION, // java.security.AllPermission
            PolicyTokenContext.SPECIALCHAR, // ;
            PolicyTokenContext.WHITESPACE,
            PolicyTokenContext.SPECIALCHAR, // }
            PolicyTokenContext.SPECIALCHAR, // ;
        });
    }

    @Test
    public void testParseToken2() {
        final String s = ""
                + "\"read\";";
        doParse(s, new TokenID[]{
            PolicyTokenContext.STRING,
            PolicyTokenContext.SPECIALCHAR, // ;
        });
    }

    @Test
    public void testParseToken3() {
        final String s = ""
                + "/* Policy definition file */\n"
                + "\n"
                + "// These permissions apply to javac" + "\n"
                + "grant codeBase \"file:${java.home}/jre/lib/ext/-\" {" + "\n"
                + "    permission java.security.AllPermission;" + "\n"
                + "};";

        doParse(s, new TokenID[]{
            PolicyTokenContext.COMMENT,
            PolicyTokenContext.WHITESPACE,
            PolicyTokenContext.COMMENT,
            PolicyTokenContext.KEYWORD, // grant
            PolicyTokenContext.WHITESPACE,
            PolicyTokenContext.KEYWORD, // codeBase
            PolicyTokenContext.WHITESPACE,
            PolicyTokenContext.STRING,
            PolicyTokenContext.WHITESPACE,
            PolicyTokenContext.SPECIALCHAR, // {

            PolicyTokenContext.WHITESPACE,
            PolicyTokenContext.KEYWORD, // permission
            PolicyTokenContext.WHITESPACE,
            PolicyTokenContext.PERMISSION, // java.security.AllPermission
            PolicyTokenContext.SPECIALCHAR, // ;
            PolicyTokenContext.WHITESPACE,
            PolicyTokenContext.SPECIALCHAR, // }
            PolicyTokenContext.SPECIALCHAR, // ;
        });
    }

    /**
     * Test of isNewline method, of class
     * org.netbeans.modules.policysupport.PolicySyntax.
     */
    @Test
    public void testIsNewline() {
        char c = '\n';
        PolicySyntax instance = new PolicySyntax();

        boolean expResult = true;
        boolean result = instance.isNewline(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of isWhitespace method, of class
     * org.netbeans.modules.policysupport.PolicySyntax.
     */
    @Test
    public void testIsWhitespace() {
        char c = ' ';
        PolicySyntax instance = new PolicySyntax();

        boolean expResult = true;
        boolean result = instance.isWhitespace(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of isSpecialChar method, of class
     * org.netbeans.modules.policysupport.PolicySyntax.
     */
    @Test
    public void testIsSpecialChar() {
        char c = ';';
        PolicySyntax instance = new PolicySyntax();

        boolean expResult = true;
        boolean result = instance.isSpecialChar(c);
        assertEquals(expResult, result);
    }

    private void doParse(String m, TokenID[] expected) {
        Syntax s = new PolicySyntax();
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
        System.out.println("---");
    }

}
