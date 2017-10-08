/*
 * CharacterClasses.java
 *
 * Created on 18. November 2005, 19:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.randompassword.helper;

/**
 *
 * @author HuberB1
 */
public enum CharacterClass {
    LOWER_CASE("abcdefghijklmnopqrstuvwxyz"),
    UPPER_CASE("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    DIGITS("0123456789"),
    SPECIAL("!$%&/()=?,.-_:;<>#+*");
    
    private final String characters;
    
    CharacterClass( String newCharacters ) {
        this.characters = newCharacters;
    }
    
    public String getCharacters() {
        return this.characters;
    }

};
