/*
 * WordCountTableModel.java
 *
 * Created on 22. Jänner 2006, 10:42
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbwordcount.model;

import javax.swing.table.AbstractTableModel;
import org.openide.util.NbBundle;

/**
 *
 * @author HuberB1
 */
public abstract class AbstractWordCountTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 20060128002300L;

    protected Number[] counters;
    protected String[] labels;
        
    public abstract void setCounters(Number[] newCounters );

    /**
     * Notify the table that table rows have changed.
     * <p>
     * Do some <code>fireTable*</code> calls.
     *
     * @param oldCountersLength the old number of counters
     * @param newCountersLength the new number of counters
     */
    protected void notifyTabledRowsChanged(final int oldCountersLength, final int newCountersLength) {
        int rowCount = this.getRowCount();
        if (rowCount > 0) {
            rowCount -= 1;
        } else {
            rowCount = 0;
        }
        
        if (oldCountersLength > newCountersLength) {
            this.fireTableRowsDeleted( newCountersLength -1, oldCountersLength -1 );
        } else if (oldCountersLength < newCountersLength) {
            this.fireTableRowsInserted( oldCountersLength -1, newCountersLength -1 );
        }
        
        this.fireTableRowsUpdated(0, rowCount);
    }
    
    public Number[] getCounters() {
        return this.counters;
    }
    
    public String[] getLabels() {
        return this.labels;
    }
    public void setLabels( String[] newLabels ) {
        this.labels = newLabels;
    }
    
    //-------------------------------------------------------------
    // AbstractTableModel implementation
    //-------------------------------------------------------------
    public int getRowCount() {
        return counters.length;
    }
    
    public int getColumnCount() {
        return 2;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        
        if (columnIndex == 0) {
            if (rowIndex < labels.length) {
                value = labels[rowIndex];
            }
        } else if (columnIndex == 1) {
            if (rowIndex < counters.length) {
                value = counters[rowIndex];
            }
        }
        return value;
    }
    
    public String getColumnName(int columnIndex) {
        String retValue = null;
        
        if (columnIndex == 0) {
            retValue = NbBundle.getMessage(WordCountTableModel.class, "TBL_HDR_Name");
        } else if (columnIndex == 1) {
            retValue = NbBundle.getMessage(WordCountTableModel.class, "TBL_HDR_Count");
        }
        return retValue;
    }
    
    public Class<?> getColumnClass(int columnIndex) {
        Class retValue = null;
        
        if (columnIndex == 0) {
            return String.class;
        } else if (columnIndex == 1) {
            return Number.class;
        }
        return retValue;
    }    
}
