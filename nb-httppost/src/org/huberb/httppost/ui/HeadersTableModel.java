package org.huberb.httppost.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.table.AbstractTableModel;
import org.huberb.httppost.model.HttpPostForm;
import org.openide.util.NbBundle;

/**
 * A table model for HeaderBean objects.
 */
public class HeadersTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 20060925203500L;
    
    private final static Object[] COLUMNS = {
        NbBundle.getMessage( HeadersTableModel.class, "TBL_HDR0_Name" ), String.class,
        NbBundle.getMessage( HeadersTableModel.class, "TBL_HDR1_Value" ), String.class,
    };    
    
    private List<HeaderBean> headers;
    
    public HeadersTableModel() {
        this(HttpPostForm.getEmptyHeaders() );
    }
    public HeadersTableModel(Map<String, List<String>> headers) {
        updateHeaders( headers );
    }
    public void clean() {
        this.headers.clear();
        this.fireTableDataChangedAndSort();
    }
    
    public void updateHeaders(Map<String, List<String>> headers) {
        if (this.headers == null) {
            this.headers = new ArrayList<HeaderBean>();
        }
        this.headers.clear();
        
        for (Iterator<Entry<String, List<String>>> i = headers.entrySet().iterator(); i.hasNext(); ) {
            final Entry<String, List<String>> header = i.next();
            
            String name = header.getKey();
            List<String> values = header.getValue();
            String[] valuesAsStrings = values.toArray(new String[values.size()]);
            //
            HeaderBean headerBean = new HeaderBean( name, valuesAsStrings );
            this.headers.add( headerBean );
        }
        this.fireTableDataChangedAndSort();
    }
    protected void fireTableDataChangedAndSort() {
        if (this.headers != null && this.headers.size() > 0) {
            Collections.sort( this.headers );
        }
        this.fireTableDataChanged();
    }
    
    public void addHeaderBean(HeaderBean hb) {
        this.headers.add( hb );
        this.fireTableDataChanged();
    }
    public void removeHeaderBean(HeaderBean hb) {
        this.headers.remove( hb );
        this.fireTableDataChanged();
    }
    public HeaderBean getHeaderBeanAt(int selectionIndex) {
        HeaderBean hb = null;
        if (selectionIndex >= 0 && selectionIndex < this.getRowCount()) {
            hb = this.headers.get(selectionIndex);
        }
        return hb;
    }
    
    public int getRowCount() {
        return this.headers.size();
    }
    public int getColumnCount() {
        return 2;
    }    
    public String getColumnName(int column) {
        return (String)COLUMNS[column/2];
    }
    public Class getColumnClass(int column) {
        return (Class)COLUMNS[column/2 +1];
    }
    public Object getValueAt(int rowIndex, int columnIndex) {
        HeaderBean headerBean = this.headers.get( rowIndex );
        Object value;
        switch (columnIndex) {
            case 0:
                value = headerBean.getHeaderName();
                break;
            case 1:
                value = headerBean.getHeaderValuesAsCSV();
                break;
            default:
                value = null;
        }
        return value;
    }
}