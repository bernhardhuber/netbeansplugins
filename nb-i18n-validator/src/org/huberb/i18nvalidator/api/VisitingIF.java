/*
 * VisitingIF.java
 *
 * Created on 23. Mai 2007, 21:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.api;

import org.huberb.i18nvalidator.spi.AbstractVisitor;

/**
 *
 * @author HuberB1
 */
public interface VisitingIF {
    void accept( AbstractVisitor ac );
}
