/*
 * AbstractComponent.java
 *
 * Created on 23. Mai 2007, 20:08
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.api;

import java.io.Serializable;
import org.huberb.i18nvalidator.spi.AbstractVisitor;

/**
 *
 * @author HuberB1
 */
public abstract class AbstractComponent implements VisitingIF, Serializable {
    protected static final long serialVersionUID = 20070523L;
    
    /**
     * Creates a new instance of AbstractComponent
     */
    public AbstractComponent() {
    }
    
    public void accept( AbstractVisitor ac ) {
        ac.visit( this );
    }
}
