/*
 * RegexpPanelFavoriteService.java
 *
 * Created on 22. Oktober 2005, 10:01
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.regexpnb.ui;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HuberB1
 */
public class RegexpPanelFavoriteService {

    private final List<RegexpPanelBean> favorites;

    /**
     * Creates a new instance of RegexpPanelFavoriteService
     */
    public RegexpPanelFavoriteService() {
        favorites = new ArrayList<>();
    }

    public void add(RegexpPanelBean rpb) {
        favorites.add(rpb);
    }

    public void remove(RegexpPanelBean rpb) {
        favorites.remove(rpb);
    }
}
