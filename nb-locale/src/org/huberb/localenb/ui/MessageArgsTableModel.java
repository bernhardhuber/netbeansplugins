/*
 * MessageArgsTableModel.java
 *
 * Created on 18. Mai 2007, 15:10
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.localenb.ui;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.huberb.localenb.options.LocaleOption;

/**
 * A simple table model holding message arguments.
 *
 * @author HuberB1
 */
public class MessageArgsTableModel extends AbstractTableModel {

    protected final static long serialVersionUID = 20070518L;

    private final List<String> argList;

    /**
     * Creates a new instance of MessageArgsTableModel
     */
    public MessageArgsTableModel() {
        int PRE_POPULATED_SIZE = 10;
        argList = new ArrayList<>();

        for (int i = 0; i < PRE_POPULATED_SIZE; i++) {
            argList.add("");
        }
    }

    @Override
    public int getRowCount() {
        return argList.size();
    }
    private Object[] COLUMN = new Object[]{
        "#", Integer.class,
        "Argument", String.class,};

    @Override
    public int getColumnCount() {
        return COLUMN.length / 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        if (columnIndex == 0) {
            value = rowIndex;
        } else if (columnIndex == 1) {
            value = argList.get(rowIndex);
        }
        return value;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex == 1);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (isCellEditable(rowIndex, columnIndex)) {
            this.argList.set(rowIndex, String.valueOf(aValue));
        }
    }

    @Override
    public String getColumnName(int column) {
        return (String) COLUMN[2 * column + 0];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return (Class) COLUMN[2 * columnIndex + 1];
    }

    public String getArgument(int index) {
        String value = null;
        if (index < this.argList.size()) {
            value = this.argList.get(index);
        }
        return value;
    }

    public Object getArgumentAs(int index, Format f) throws ParseException {
        Object value = null;

        String argAsString = getArgument(index);
        if (argAsString != null) {
            if (f instanceof DateFormat) {
                value = parseDate(argAsString);
            } else if (f instanceof NumberFormat) {
                value = parseNumber(argAsString);
            } else {
                value = argAsString;
            }
        }
        return value;
    }

    public List<String> getArgs() {
        return new ArrayList<String>(this.argList);
    }

    public void setArgs(List<String> args) {
        this.argList.clear();
        this.argList.addAll(args);
    }

    protected Date parseDate(String valueAsString) throws ParseException {
        LocaleOption localeOption = LocaleOption.getDefault();
        String datePattern = localeOption.getMessageArgDatePattern();

        final SimpleDateFormat parsingDateFormat = new SimpleDateFormat(datePattern);
        Date value = parsingDateFormat.parse(valueAsString);
        return value;
    }

    protected Number parseNumber(String valueAsString) throws ParseException {
        LocaleOption localeOption = LocaleOption.getDefault();
        String numberPattern = localeOption.getMessageArgNumberPattern();

        DecimalFormat df = new DecimalFormat(numberPattern);
        Number value = df.parse(valueAsString);
        return value;
    }
}
