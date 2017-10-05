/*
 * DefaultDoubleRenderer.java
 *
 * Created on 25. Jänner 2006, 21:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbwordcount;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * A Number pattern renderer.
 * <p>
 * This renderer allows to change the pattern of the formatter.
 *
 * @author HuberB1
 */
public class DefaultNumberRenderer extends  DefaultTableCellRenderer {
    private final static String DEFAULT_PATTERN = "#,##0.##";
    private NumberFormat formatter = null;
    private String pattern;
    
    /** Creates a new instance of DefaultDoubleRenderer */
    public DefaultNumberRenderer() {
        super();
        
        this.pattern = DEFAULT_PATTERN;
        setHorizontalAlignment(JLabel.RIGHT);
    }
    
    public void setValue(Object value) {
        if (formatter==null) {
            formatter = new DecimalFormat( DEFAULT_PATTERN );
        }

        String text = "";
        if (value != null) {
            if (value instanceof Number) {
                text = formatter.format(value);
            } else {
                text = String.valueOf(value);
            }
        }
        setText(text);
    }
    
    public void setPattern( String newPattern ) {
        this.pattern = newPattern;
        this.formatter = new DecimalFormat( this.pattern );
    }
    
    public String getPattern() {
        return this.pattern;
    }
}
