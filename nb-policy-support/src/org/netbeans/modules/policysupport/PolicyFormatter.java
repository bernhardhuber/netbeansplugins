/*
 * PolicyFormatter.java
 *
 * Created on 03. September 2006, 18:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.modules.policysupport;

import java.io.IOException;
import java.io.Writer;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.netbeans.editor.BaseDocument;
import org.netbeans.editor.Syntax;
import org.netbeans.editor.ext.ExtFormatter;

/**
 * The policy formatter.
 *
 * @author HuberB1
 */
public class PolicyFormatter extends ExtFormatter {
    
    /** 
     * Creates a new instance of PolicyFormatter.
     *
     * @param kitClass the editor kit class.
     */
    public PolicyFormatter(Class kitClass) {
        super( kitClass );
    }
    
    /**
     * Determines whether the specified syntax is supported by this formatter.
     *
     * @param syntax does this formatter accept this syntax
     * @return boolean true if this syntax is <code>PolicySyntax</code>, else false
     */
    protected boolean acceptSyntax(Syntax syntax) {
        return (syntax instanceof PolicySyntax);
    }
    
    /**
     * Reformats a portion of the document.
     */
    public Writer reformat(
            BaseDocument doc,
            int startOffset,
            int endOffset,
            boolean indentOnly)
            throws BadLocationException, IOException {
        return super.reformat(doc, startOffset, endOffset,  indentOnly);
    }
    
    /**
     * Reformats a block of text.
     *
     * @param target 
     * @param typedText 
     * @return 
     */
    public int[] getReformatBlock(JTextComponent target, String typedText) {
        return super.getReformatBlock(target, typedText);
    }
    
}
