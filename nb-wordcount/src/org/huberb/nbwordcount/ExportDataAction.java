package org.huberb.nbwordcount;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.text.NumberFormat;
import java.util.Locale;
import org.huberb.nbwordcount.model.WordCountTableModel;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

/**
 * Export the content of the top component to the clipboard.
 * <p>
 * Use following format:
 * <code>Name\tValue\n</code>.
 *
 */
public final class ExportDataAction extends CallableSystemAction {
    static final String ICON_PATH = "org/huberb/nbwordcount/images/editcopy.png";
    private final static String DELIM_COL = "\t";
    private final static String DELIM_ROW = "\n";
    
    @Override
    public void performAction() {
        final WordCountTopComponent win = WordCountTopComponent.findInstance();
        if (win == null) {
            return ;
        }
        
        // the wordcount table model
        final WordCountTableModel wctm = win.getWordCountTableModel();
        if (wctm == null) {
            return ;
        }
        // format the wordcount table model
        final StringBuffer sb = new StringBuffer();
        
        buildHeader1( wctm, sb );
        buildContentInfo( win, sb );
        buildContentWc( wctm, sb );
        
        // copy sb to the clipboard
        final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        final StringSelection stringSelection = new StringSelection(sb.toString());
        final ClipboardOwner owner = stringSelection;
        clipboard.setContents(stringSelection, owner );
    }
    
    /**
     * Build the header.
     * <p>
     * The header consists of the column header names.
     *
     * @param wctm the word count table model
     * @param sb the StringBuffer holding the clipboard text
     */
    protected void buildHeader1(WordCountTableModel wctm, StringBuffer sb ) {
        final int columnCount = wctm.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            sb.append( wctm.getColumnName( i ) );
            if (i < columnCount -1) {
                sb.append(DELIM_COL);
            }
        }
        sb.append(DELIM_ROW);
    }
    
    /**
     * Add the various count values to the StringBuffer as CSV.
     *
     * @param wctm the word count table model
     * @param sb the StringBuffer holding the clipboard text
     */
    protected void buildContentWc(WordCountTableModel wctm, StringBuffer sb ) {
        final int columnCount = wctm.getColumnCount();
        final int rowCount = wctm.getRowCount();
        for (int i = 0; i < rowCount; i++ ) {
            for (int j = 0; j < columnCount; j++) {
                final Object value = wctm.getValueAt( i, j );
                final String valueAsString = convertValueToString(value);
                // add the converted table model value
                sb.append( valueAsString );
                if (j < columnCount -1) {
                    sb.append(DELIM_COL);
                }
            }
            sb.append( DELIM_ROW );
        }
    }
    
    /**
     * Convert a table model value to String.
     * <p>
     * This method uses the default locale for converting numbers
     * to String
     *
     * @param value the table model value
     * @return the String representation of value
     */
    protected String convertValueToString( Object value ) {
        final String valueAsString;
        
        if (value instanceof Number) {
            final Number valueAsNumber = (Number)value;
            final Locale locale = Locale.getDefault();
            final NumberFormat nf = NumberFormat.getInstance( locale );
            // format value using the default locale
            valueAsString = nf.format( valueAsNumber );
        } else {
            valueAsString = String.valueOf(value);
        }
        return valueAsString;
    }
    
    /**
     * Write the count of files, and the file names
     *
     * @param wctm the word count table model
     * @param sb the StringBuffer holding the clipboard text
     */
    protected void buildContentInfo( WordCountTopComponent win, StringBuffer sb ) {
        
        // # \t {N}\n
        sb.append( NbBundle.getMessage(ExportDataAction.class, "CLIPBOARD_COL0_COUNT_OF_FILES" ) );
        sb.append( DELIM_COL );
        sb.append( win.getCountOfProcessedNames() );
        sb.append( DELIM_ROW );
        
        // File \t file1, file2, ...\n
        sb.append( NbBundle.getMessage(ExportDataAction.class, "CLIPBOARD_COL0_FILENAME" ) );
        sb.append( DELIM_COL );
        sb.append( win.getProcessedNames() );
        sb.append( DELIM_ROW );
    }
    @Override
    public String getName() {
        return NbBundle.getMessage(ExportDataAction.class, "CTL_ExportDataAction");
    }
    
    @Override
    protected void initialize() {
        super.initialize();
        // see org.openide.util.actions.SystemAction.iconResource() javadoc for more details
        // putValue("noIconInMenu", Boolean.TRUE);
    }
    
    @Override
    protected String iconResource() {
        return ICON_PATH;
    }
    
    @Override
    public HelpCtx getHelpCtx() {
        return ConstantsHelper.getHelpCtx();
    }
    
    @Override
    protected boolean asynchronous() {
        return false;
    }
    
}
