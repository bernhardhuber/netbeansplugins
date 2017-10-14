/*
 * CpdInternalRenderer.java
 *
 * Created on 21. April 2007, 20:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.cpd.cpdlib;

import java.util.Iterator;
import net.sourceforge.pmd.cpd.Match;
import net.sourceforge.pmd.cpd.Renderer;
import net.sourceforge.pmd.cpd.TokenEntry;

/**
 * Need to do some rendering at all???
 * @author HuberB1
 */
public class CpdInternalRenderer implements Renderer {
    
    /** Creates a new instance of CpdInternalRenderer */
    public CpdInternalRenderer() {
    }
    
    @Override
    public String render(Iterator matches) {
        while (matches.hasNext()) {
            Match match = (Match) matches.next();
            //match.getLineCount()
            //match.getTokenCount());
            
            for (Iterator iterator = match.iterator(); iterator.hasNext();) {
                TokenEntry mark = (TokenEntry) iterator.next();
                //buffer.append(mark.getBeginLine());

                //buffer.append(mark.getTokenSrcID());
            }
            String codeFragment = match.getSourceCodeSlice();
        }
        return "";
    }
    
}
