/*
 * JavaStatementLineCounter.java
 *
 * Created on 10. August 2006, 14:28
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbwordcount.model.counters;

import java.util.regex.Pattern;
import org.huberb.nbwordcount.model.counters.LineCounterIF.CounterInfo;

/**
 * A simple java file counter, counting the total lines, blank lines, and
 * code lines.
 *
 * @author HuberB1
 */
public class JavaStatementLineCounter implements LineCounterIF {
    private Counter lines;
    private Counter nonWhiteSpaceLines;
    private Counter code;
    
    private Pattern RE_WHITESPACE = Pattern.compile("\\s*[*]?\\s*");
    private Pattern RE_COMMENT1 = Pattern.compile( "\\s*[*].*" );
    private Pattern RE_COMMENT2 = Pattern.compile( "\\s*//.*" );
    private Pattern RE_COMMENT3 = Pattern.compile( "\\s*/[*].*" );
    
    private Pattern RE_SIMPLE_STATMENT = Pattern.compile( "\\s*[:?=(){};]\\s*" );
    
    /** Creates a new instance of JavaStatementLineCounter */
    public JavaStatementLineCounter() {
        final long INITIAL_VALUE = 0L;
        
        this.lines = new Counter(INITIAL_VALUE);
        this.nonWhiteSpaceLines = new Counter(INITIAL_VALUE);
        this.code = new Counter(INITIAL_VALUE);
    }
    
    /**
     *
     * @return Long the count of lines
     */
    public Long getCountOfLines() {
        return Long.valueOf(this.lines.longValue() );
    }
    
    /**
     *
     * @return Long the count of whitespace lines
     */
    public Long getCountOfWhitspaceLines() {
        return Long.valueOf( this.lines.longValue() - this.nonWhiteSpaceLines.longValue() );
    }
    
    /**
     *
     * @return Long the count of code lines
     */
    public Long getCountOfCodeLines() {
        return Long.valueOf( this.code.longValue() );
    }
    
    /**
     * Count lines, blank lines, and code lines of this line
     *
     * @param line the line
     */
    public void count(String s) {
        lines.increment(1L);
        if (!RE_WHITESPACE.matcher(s).matches()) {
            nonWhiteSpaceLines.increment(1L);
        }
        
        // look for comments
        final boolean comment =
                RE_COMMENT1.matcher(s).matches() // continuation of block comment
                || RE_COMMENT2.matcher(s).matches() // full line comment
                || RE_COMMENT3.matcher(s).matches();   // beginning of block comment
        
        if (!comment) {
            // sort out simple statements
            final boolean isACodeLine = !RE_SIMPLE_STATMENT.matcher(s).matches();
            if (isACodeLine) {
                code.increment(1L);
            }
        }
    }
    
    /**
     *
     * @return CounterInfo the information gathered
     */
    public CounterInfo getCounterInfo() {
        final JavaStatementLineCounter slc = this;
        final CounterInfo ci = new CounterInfo() {
            public Number[] getCounterValues() {
                return new Number[] {
                    slc.getCountOfLines(),
                    slc.getCountOfWhitspaceLines(),
                    slc.getCountOfCodeLines()
                };
                
            }
        };
        return ci;
    }
}
