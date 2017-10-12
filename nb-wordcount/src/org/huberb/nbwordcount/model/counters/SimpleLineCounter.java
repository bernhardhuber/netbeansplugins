/*
 * SimpleLineCounter.java
 *
 * Created on 10. August 2006, 14:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbwordcount.model.counters;

import org.huberb.nbwordcount.model.counters.LineCounterIF.CounterInfo;

/**
 * A simple character, word, line counter.
 *
 * @author HuberB1
 */
public class SimpleLineCounter implements LineCounterIF {
    private final Counter countOfCharacters;
    private final Counter countOfWords;
    private final Counter countOfLines;
    
    /** Creates a new instance of SimpleLineCounter */
    public SimpleLineCounter() {
        final long INITIAL_VALUE = 0L;
        
        this.countOfCharacters = new Counter(INITIAL_VALUE);
        this.countOfWords = new Counter(INITIAL_VALUE);
        this.countOfLines = new Counter(INITIAL_VALUE);
    }
    
    public Number getCountOfCharacter() {
        return this.countOfCharacters.longValue();
    }
    public Number getCountOfWords() {
        return this.countOfWords.longValue();
    }
    public Number getCountOfLines() {
        return this.countOfLines.longValue();
    }
    
    /**
     * Count characters, words, and lines of this line
     *
     * @param line the line
     */
    @Override
    public void count(String line) {
        this.countOfLines.increment(1L);
        this.countOfCharacters.increment( line.length() );
        this.countOfWords.increment( this.countOfWords( line ));
    }
    
    /**
     * Count words of a line
     *
     * @param line the line
     * @return count of words contained in the specified line
     */
    private long countOfWords(String line) {
        long countOfWords = 0L;
        boolean inWord = true;
        
        for (int i = 0; i < line.length(); i++ ) {
            final char c = line.charAt( i );
            if (Character.isWhitespace( c )) {
                if (inWord) {
                    inWord = false;
                    countOfWords += 1;
                }
            } else {
                // !Character.isWhitespace(c)
                if (!inWord) {
                    inWord = true;
                }
            }
        }
        if (inWord) {
            countOfWords += 1;
        }
        return countOfWords;
    }
    
    @Override
    public CounterInfo getCounterInfo() {
        final SimpleLineCounter slc = this;        
        final CounterInfo ci = new CounterInfo() {
            @Override
            public Number[] getCounterValues() {
                return new Number[] {
                    slc.getCountOfCharacter(),
                    slc.getCountOfWords(),
                    slc.getCountOfLines()
                };
                
            }
        };
        return ci;
    }
}
