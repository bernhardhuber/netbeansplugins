/*
 * ManifestSyntax.java
 *
 * Created on July 20, 2005, 10:37 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package org.netbeans.modules.manifestsupport;

import org.netbeans.editor.Syntax;
import org.netbeans.editor.TokenID;
import org.openide.ErrorManager;

/**
 *
 * @author Administrator
 */
public class ManifestSyntax extends Syntax {

    private static final ErrorManager LOGGER = ErrorManager.getDefault().getInstance("org.netbeans.modules.manifestsupport.ManifestSyntax");
    private static final boolean LOG = LOGGER.isLoggable(ErrorManager.INFORMATIONAL);

    private static final int ISI_NAME = 1;
    private static final int ISA_CR = 2;
    private static final int ISA_LF = 3;
    private static final int ISA_CRLF = 4;
    private static final int ISA_AFTER_NAME = 6;
    private static final int ISA_COLON = 7;
    private static final int ISA_AFTER_COLON = 8;
    private static final int ISI_VALUE = 9;
    
    /** Creates a new instance of ManifestSyntax */
    public ManifestSyntax() {
        tokenContextPath = ManifestTokenContext.contextPath;
    }
    
    protected TokenID parseToken() {
        TokenID result = doParseToken();
        if (LOG) {
            LOGGER.log(ErrorManager.INFORMATIONAL, "parseToken: " + result);
        }
        return result;
    }
    
    private TokenID doParseToken() {
        char actChar;
        
        while (offset < stopOffset) {
            actChar = buffer[offset];
            
            switch (state) {
                case INIT:
                    switch (actChar) {
                        case ':':
                            state = ISA_AFTER_COLON;
                            offset++;
                            return ManifestTokenContext.COLON;
                        case '\n':
                            state = INIT;
                            offset++;
                            return ManifestTokenContext.END_OF_LINE;
                        case '\r':
                            state = ISA_CR;
                            break;
                        default:
                            state = ISI_NAME;
                    }
                    break;
                    
                case ISI_NAME:
                    switch (actChar) {
                        case ':':
                        case '\r':
                        case '\n':
                            state = ISA_AFTER_NAME;
                            return ManifestTokenContext.NAME;
                    }
                    break;
                    
                case ISA_CR:
                    if (actChar == '\n') {
                        offset++;
                    }
                    state = INIT;
                    return ManifestTokenContext.END_OF_LINE;
                    
                case ISA_AFTER_NAME:
                    switch (actChar) {
                        case ':':
                            state = ISA_AFTER_COLON;
                            offset++;
                            return ManifestTokenContext.COLON;
                        case '\n':
                            state = INIT;
                            offset++;
                            return ManifestTokenContext.END_OF_LINE;
                        case '\r':
                            state = ISA_CR;
                            break;
                        default:
                            assert false;
                    }
                    break;
                    
                case ISA_AFTER_COLON:
                    switch (actChar) {
                        case '\n':
                            state = INIT;
                            offset++;
                            return ManifestTokenContext.END_OF_LINE;
                        case '\r':
                            state = ISA_CR;
                            break;
                        default:
                            state = ISI_VALUE;
                    }
                    break;
                    
                case ISI_VALUE:
                    switch (actChar) {
                        case '\r':
                        case '\n':
                            state = INIT;
                            return ManifestTokenContext.VALUE;
                    }
                    break;
            }
            
            offset++;
        }
        
        switch (state) {
            case ISI_NAME:
                state = INIT;
                return ManifestTokenContext.NAME;
            case ISI_VALUE:
                state = INIT;
                return ManifestTokenContext.VALUE;
        }
        
        return null;
    }
}
