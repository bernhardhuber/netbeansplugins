/*
 * RandomPasswordSuggestioner.java
 *
 * Created on 18. November 2005, 17:53
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.randompassword.helper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

/**
 * This class encapulates the password generation.
 *
 * @author HuberB1
 */
public class RandomPasswordSuggestioner implements Serializable {

    private static final long serialVersionUID = 20061017084100L;

    private final String algorithm = "SHA1PRNG";
    private transient Random randomGenerator;

    /**
     * Creates a new instance of RandomPasswordSuggestioner
     */
    public RandomPasswordSuggestioner() {
        createRandomGenerator();
    }

    private void createRandomGenerator() {
        try {
            this.randomGenerator = SecureRandom.getInstance(algorithm);
        } catch (GeneralSecurityException gse) {
            this.randomGenerator = new Random();
        }
        randomGenerator.setSeed(System.currentTimeMillis());
    }

    /**
     * Build a String of all allowed characters
     *
     * @param allowedCharacterClasses the allowed character classes
     * @return String of all allowed characters
     */
    private String buildAllowedCharacters(List<CharacterClass> allowedCharacterClasses) {
        StringBuilder sb = new StringBuilder();
        for (CharacterClass cc : allowedCharacterClasses) {
            sb.append(cc.getCharacters());
        }
        return sb.toString();
    }

    /**
     * Create a suggestion for a password
     *
     * @param passwordLength the exact length of the password suggestion
     * @param allowedCharacters the allowed characters of the password
     * suggestion
     * @return String the suggested password
     */
    public String suggestPassword(int passwordLength, List<CharacterClass> allowedCharacterClasses) {
        String allowedCharacters = buildAllowedCharacters(allowedCharacterClasses);
        return suggestPassword(passwordLength, allowedCharacters);
    }

    /**
     * Create a suggestion for a password
     *
     * @param passwordLength the exact length of the password suggestion
     * @param allowedCharacters the allowed characters of the password
     * suggestion
     * @return String the suggested password
     */
    public String suggestPassword(int passwordLength, String allowedCharacters) {
        final int randomRange = allowedCharacters.length();
        final StringBuilder suggestedPassword = new StringBuilder(passwordLength);
        for (int i = 0; i < passwordLength; i++) {
            final int randomIndex = this.randomGenerator.nextInt(randomRange);
            final char c = allowedCharacters.charAt(randomIndex);
            suggestedPassword.append(c);
        }
        return suggestedPassword.toString();
    }

    private void readObject(ObjectInputStream ios) throws IOException, ClassNotFoundException {
        ios.defaultReadObject();
        createRandomGenerator();
    }
}
