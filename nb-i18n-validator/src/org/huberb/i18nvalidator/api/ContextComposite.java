/*
 * ContextComposite.java
 *
 * Created on 23. Mai 2007, 20:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.api;

import java.io.Serializable;

/**
 *
 * @author HuberB1
 */
public class ContextComposite extends AbstractComposite implements Serializable {
    protected static final long serialVersionUID = 20070523L;

    private AbstractComponent context;
            
    /**
     * Creates a new instance of ContextComposite
     */
    public ContextComposite() {
        this(null);
    }
    /**
     * Creates a new instance of ContextComposite
     */
    public ContextComposite(AbstractComponent context) {
        this.context = context;
    }

    public AbstractComponent getContext() {
        return context;
    }


}
