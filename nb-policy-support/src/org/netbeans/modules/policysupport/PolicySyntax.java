/*
 * PolicySyntax.java
 *
 * Created on July 20, 2005, 10:37 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package org.netbeans.modules.policysupport;

import java.util.Arrays;
import org.netbeans.editor.Syntax;
import org.netbeans.editor.TokenID;
import org.openide.ErrorManager;

/**
 *
 * @author HuberB1
 */
public class PolicySyntax extends Syntax {
    
    private static final ErrorManager LOGGER = ErrorManager.getDefault().getInstance("org.netbeans.modules.policysupport.PolicySyntax");
    private static final boolean LOG = LOGGER.isLoggable(ErrorManager.INFORMATIONAL);
    
    
    /**
     * Creates a new instance of PolicySyntax
     */
    public PolicySyntax() {
        tokenContextPath = PolicyTokenContext.contextPath;
    }
    
    protected TokenID parseToken() {
        final TokenID result = doParseToken();
        if (LOG) {
            LOGGER.log(ErrorManager.INFORMATIONAL, "parseToken: " + result);
        }
        return result;
    }
    
    private static final int ISI_COMMENT = 10;
    private static final int ISI_KEYWORD = 11;
    private static final int ISI_STRING = 12;
    private static final int ISI_NEWLINE = 13;
    private static final int ISI_WHITESPACE = 14;
    //private static final int ISA_PERMISSION = 15;
    private static final int ISI_SPECIALCHAR = 16;
    //private static final int ISB_STRING = 17;
    
    private TokenID doParseToken() {
        // TODO check special char, and permission class
        while (offset < stopOffset) {
            final char actChar = buffer[offset];
            
            switch (state) {
                case INIT:
                    if (isNewline(actChar) || isWhitespace(actChar)) {
                        state = ISI_WHITESPACE;
                    } else if (actChar == '/') {
                        state = ISI_COMMENT;
                    } else if (isSpecialChar(actChar)) {
                        state = ISI_SPECIALCHAR;
                    } else if (actChar == '"') {
                        state = ISI_STRING;
                    } else {
                        state = ISI_KEYWORD;
                    }
                    break;
                case ISI_COMMENT:
                    if (isNewline(actChar)) {
                        offset++;
                        state = ISI_NEWLINE;
                        return PolicyTokenContext.COMMENT;
                    }
                    break;
                case ISI_SPECIALCHAR:
                    state = INIT;
                    return PolicyTokenContext.SPECIALCHAR;
                case ISI_KEYWORD:
                    if (isNewline(actChar) || isWhitespace(actChar) || isSpecialChar(actChar)) {
                        state = INIT;
                        
                        final TokenID tid = isKeyword(buffer, tokenOffset, offset - tokenOffset);
                        if(tid != null) {
                            return tid;
                        } else {
                            if (countOfKeywords > 0) {
                                countOfKeywords = 0;
                                return PolicyTokenContext.PERMISSION;
                            }
                        }
                        return PolicyTokenContext.KEYWORD;
                    }
                    break;
                case ISI_STRING:
                    if (actChar == '"') {
                        offset++;
                        state = INIT;
                        return PolicyTokenContext.STRING;
                    }
                    break;
                case ISI_NEWLINE:
                case ISI_WHITESPACE:
                    if (isNewline(actChar) || isWhitespace(actChar)) {
                        state = ISI_WHITESPACE;
                    } else {
                        state = INIT;
                        return PolicyTokenContext.WHITESPACE;
                    }
                    break;
            }
            offset++;
        }
        if (lastBuffer) {
            switch (state) {
                case ISI_SPECIALCHAR:
                    return PolicyTokenContext.SPECIALCHAR;
                case ISI_WHITESPACE:
                    return PolicyTokenContext.WHITESPACE;
            }
        }
        return null;
    }
    
    protected boolean isNewline( char c ) {
        final boolean isNewline = (c == '\r' || c == '\n');
        return isNewline;
    }
    protected boolean isWhitespace( char c ) {
        final boolean isWhitespace = (c == '\t' || c == ' ');
        return isWhitespace;
    }
    protected boolean isSpecialChar( char c ) {
        final boolean isSpecialChar = (c == ',' || c == ';' || c == '{' ||c == '}' );
        return isSpecialChar;
    }
    
    private int countOfKeywords = 0;
    private static final String[] sortedkeywords = {
        "codeBase", // 0
        "grant", // 1
        "keystore", // 2
        "keystorePasswordURL", // 3
        "permission", // 4
        "principal",
        "signedBy",
    };
    
    protected TokenID isKeyword(char[] buffer, int offset, int len ) {
        final String keywordCandidate = new String(buffer, offset, len);
        
        int foundIndex = Arrays.binarySearch( sortedkeywords, keywordCandidate );
        if (foundIndex >= 0) {
            if (foundIndex == 4) {
                countOfKeywords += 1;
            }
            return PolicyTokenContext.KEYWORD;
        }
        
        return null;
    }
}
