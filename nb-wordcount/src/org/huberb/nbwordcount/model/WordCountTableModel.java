/*
 * WordCountTableModel.java
 *
 * Created on 22. Jänner 2006, 10:42
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbwordcount.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openide.util.NbBundle;

/**
 *
 * @author HuberB1
 */
public class WordCountTableModel extends AbstractWordCountTableModel {
    private static final long serialVersionUID = 20060128002300L;
    
    /** Creates a new instance of WordCountTableModel */
    public WordCountTableModel() {
        final Long COUNTER_DEFAULT = Long.valueOf(0L);
        final Double COUNTER_DEFAULT2 = Double.valueOf(0.0);
        counters = new Number[] {
            COUNTER_DEFAULT,
            COUNTER_DEFAULT,
            COUNTER_DEFAULT,
            
            COUNTER_DEFAULT2,
            COUNTER_DEFAULT2,
            
            COUNTER_DEFAULT,
            COUNTER_DEFAULT,
        };
        
        labels = new String[] {
            NbBundle.getMessage(WordCountTableModel.class, "TBL_COL0_Characters"),
            NbBundle.getMessage(WordCountTableModel.class, "TBL_COL0_Words"),
            NbBundle.getMessage(WordCountTableModel.class, "TBL_COL0_Lines"),
            
            NbBundle.getMessage(WordCountTableModel.class, "TBL_COL0_CharactersPerLine"),
            NbBundle.getMessage(WordCountTableModel.class, "TBL_COL0_WordsPerLine"),
            
            NbBundle.getMessage(WordCountTableModel.class, "TBL_COL0_BlankLines"),
            NbBundle.getMessage(WordCountTableModel.class, "TBL_COL0_CodeLines"),
        };
    }
    
    public void setCounters(Number[] newCounters ) {
        List newCountersAsList = Arrays.asList(newCounters);
        final List<Number> countersAsList = new ArrayList<Number>();
        
        // retrieve all counters from SimpleLineCounter
        countersAsList.addAll( newCountersAsList.subList( 0, 3 ) );
        
        final double countOfCharacters = newCounters[0].doubleValue();
        final double countOfWords = newCounters[1].doubleValue();
        final long countOfLines = newCounters[2].longValue();
        
        if (countOfLines > 0) {
            double charactersByLine = countOfCharacters / countOfLines;
            double wordsByLine = countOfWords / countOfLines;
            
            countersAsList.add( Double.valueOf(charactersByLine) );
            countersAsList.add( Double.valueOf(wordsByLine) );
        }
        
        // retrieve SimpleLineCounter #blank lines, and #code lines
        countersAsList.addAll( newCountersAsList.subList( 4, 6 ) );
        
        final int oldCountersLength = this.counters.length;
        this.counters = countersAsList.toArray( new Number[countersAsList.size()]);
        final int newCountersLength = this.counters.length;
        
        notifyTabledRowsChanged(oldCountersLength, newCountersLength);
    }
}
