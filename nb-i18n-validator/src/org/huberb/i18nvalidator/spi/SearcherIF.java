/*
 * SearcherIF.java
 *
 * Created on 23. Mai 2007, 19:56
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.spi;

import org.huberb.i18nvalidator.api.FindInfoComposite;
import org.huberb.i18nvalidator.api.SearchInfoComposite;

/**
 *
 * @author HuberB1
 */
public interface SearcherIF {
    SearchInfoComposite search(FindInfoComposite fic);
}
