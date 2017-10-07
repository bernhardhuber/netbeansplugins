/*
 * SimpleRenderVisitor.java
 *
 * Created on 02. Juni 2007, 12:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.spi.implementation;

import org.huberb.i18nvalidator.api.AbstractComponent;
import org.huberb.i18nvalidator.api.AbstractComposite;
import org.huberb.i18nvalidator.api.ContextComposite;
import org.huberb.i18nvalidator.api.FindItem;
import org.huberb.i18nvalidator.api.ResourceLocation;
import org.huberb.i18nvalidator.api.SearchItem;
import org.huberb.i18nvalidator.spi.*;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

/**
 *
 * @author HuberB1
 */
public class SimpleRenderVisitor extends AbstractVisitor {
    private OutputWriter ow;
    
    /** Creates a new instance of SimpleRenderVisitor */
    public SimpleRenderVisitor() {
        IOProvider iop = IOProvider.getDefault();
        InputOutput io = iop.getIO( "SimpleRenderVisitor", false );
        this.ow = io.getOut();
        
    }
    
    //-------------------------------------------------------------------------
    public void visit(AbstractComponent ac) {
        log( ac, "-" );
        tab += 1;
        super.visit(ac);
        tab -= 1;
    }
    
    //-------------------------------------------------------------------------
    public void _visit(ContextComposite cc) {
        tab += 1;
        super._visit( cc );
        tab -= 1;
    }
    
    public void _visit( ResourceLocation rl ) {
//        if (rl.getLineNumber() > 0) {
        log( rl, rl.getResourceUrl().toString() + ", " + rl.getLineNumber() + ":" + rl.getColumn() );
//        }
    }
    
    public void _visit(AbstractComposite abstractComposite) {
        tab += 1;
        super._visit( abstractComposite );
        tab -= 1;
    }
    
    public void _visit( SearchItem fi ) {
        log( fi, fi.getTitle() + ", " + fi.getSnippet() );
    }
    public void _visit( FindItem fi ) {
        log( fi, fi.getPattern() );
    }
    
    private int tab = 0;
    
    private void log( Object obj, String msg ) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tab; i++ ) {
            sb.append( " " );
        }
        this.ow.println( sb.toString() + String.valueOf(obj) + ": " + msg );
        
    }
}
