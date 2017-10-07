/*
 * MessageFormatValidator.java
 *
 * Created on 19. Mai 2007, 12:12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.huberb.i18nvalidator.misc.IOHelper;
import org.netbeans.modules.properties.BundleStructure;
import org.netbeans.modules.properties.PropertiesDataObject;
import org.netbeans.modules.properties.PropertiesFileEntry;
import org.openide.cookies.LineCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.text.Line;
import org.openide.windows.OutputEvent;
import org.openide.windows.OutputListener;
import org.openide.windows.OutputWriter;

/**
 *
 * @author HuberB1
 */
public class MessageFormatValidator {
    
    /**
     * Creates a new instance of MessageFormatValidator
     */
    public MessageFormatValidator() {
    }
    
    public void validate(File file) {
        Locale locale = Locale.getDefault();
        
        Properties props = loadProperties(file);
        if (props != null) {
            MessageFormatConstraint mfc = new MessageFormatConstraint();
            mfc.setLocale( locale );
            
            MessageSingleQuoteConstraint msqc = new MessageSingleQuoteConstraint();
            msqc.setLocale( locale );
            
            // validate each message value
            int countOfValidationExceptions = 0;
            for (Iterator i = props.keySet().iterator(); i.hasNext(); ) {
                String key = (String)i.next();
                String value = props.getProperty(key);
                if (value != null) {
                    try {
                        mfc.constraint( value );
                        String result = mfc.getResult();
                        boolean verbose = false;
                        if (verbose) {
                            ioWrapper.outputMessage( file, key, result );
                        }
                        msqc.constraint( value );
                        
                    } catch (ValidatorException ex) {
                        ioWrapper.outputMessage( file, key, ex );
                        countOfValidationExceptions += 1;
                    }
                }
            }
            if (countOfValidationExceptions == 0) {
                ioWrapper.outputMessage( "No key validation failed!" );
            }
        }
    }
    
    protected Properties loadProperties(File file) {
        
        Properties props = new Properties();
        try {
            // load the properties
            FileInputStream fis = new FileInputStream(file);
            try {
                props.load(fis);
            } catch (IllegalArgumentException iaex) {
                iaex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    fis.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return props;
    }
    
    private IOWrapper ioWrapper = new IOWrapper();
    static class IOWrapper {
        private IOHelper ioHelper = new IOHelper("I18N Validate", false );
        private MyOutputListener myol = new MyOutputListener();
        
        protected void outputMessage( String m ) {
            final OutputWriter ow = ioHelper.getOut();
            
            ow.println(m);
        }
        protected void outputMessage( File file, String key, String m ) {
            String filename = file.getAbsolutePath();
            final OutputWriter ow = ioHelper.getOut();
            
            ow.println(filename + ":" + key + ": " + m);
        }
        protected void outputMessage( File file, String key, Exception ex ) {
            String filename = file.getAbsolutePath();
            
            final OutputWriter ow = ioHelper.getOut();
            
            StringBuilder sb = new StringBuilder();
            sb.append( filename + ":" + key + ": "  + ex.getMessage() );
            
            for (Throwable theException = ex.getCause(); theException != null; theException = theException.getCause() ) {
                sb.append( " " );
                sb.append( theException.getMessage() );
            }
            try {
                ow.println(sb.toString(), myol, true );
            } catch (IOException ioex) {
                ioex.printStackTrace();
            }
        }
    }
    
    static class MyOutputListener implements OutputListener {
        public void outputLineSelected(OutputEvent outputEvent) {
            //System.out.println("outputLineSelected " + toStringOutputEvent(outputEvent));
        }
        
        public void outputLineAction(OutputEvent outputEvent) {
            //System.out.println("outputLineAction " + toStringOutputEvent(outputEvent));
            String outputLine = outputEvent.getLine();
            OutputMatcher om = new OutputMatcher();
            Object[] result = om.extract(outputLine);
            if (result != null && result[0] != null) {
                Line line = (Line) result [0];
                //
                line.show(line.SHOW_TOFRONT);
                
                // add an annotation
                //CpdAnnotation cpdAnnotation = new CpdAnnotation(lineString);
                //cpdAnnotation.attach( line );
            }
        }
        
        public void outputLineCleared(OutputEvent outputEvent) {
            //System.out.println("outputLineCleared " + toStringOutputEvent(outputEvent));
        }
        
        private String toStringOutputEvent( OutputEvent outputEvent ) {
            return outputEvent.getLine();
        }
        
        static class OutputMatcher {
            private Pattern pattern = Pattern.compile("^(.+):(.+):(.+)");
            
            private Pattern[] patterns = new Pattern[] {pattern};
            
            public Object[] extract( String message ) {
                Matcher matcher = null;
                boolean matches = false;
                for (int i = 0; !matches && i < patterns.length; i++ ) {
                    Pattern p = patterns[i];
                    matcher = p.matcher( message );
                    matches = matcher.matches();
                }
                
                // extract filename, and linenumber form the match
                if (matches && matcher != null && matcher.groupCount() >= 2) {
                    String fileName = matcher.group(1);
                    String propertiesKey = matcher.group(2);
                    
                    FileObject fo = FileUtil.toFileObject(new File(fileName));
                    if (fo == null) {
                        return null;
                    }
                    
                    try {
                        DataObject dob = getExactDataObject(fo);
                        LineCookie lineCookie = (LineCookie) dob.getCookie(LineCookie.class);
                        if (lineCookie == null) {
                            return null;
                        }
                        int lineNumber = 0;
                        Line line = lineCookie.getLineSet().getOriginal(lineNumber);
                        
                        return new Object[] {line, message};
                    } catch (DataObjectNotFoundException ex) {
                        return null;
                    }
                }
                return null;
            }
            
            DataObject getExactDataObject(FileObject fo) throws DataObjectNotFoundException {
                DataObject dob = null;
                dob = DataObject.find(fo);
                if (dob instanceof PropertiesDataObject) {
                    PropertiesDataObject pdo = (PropertiesDataObject)dob;
                    BundleStructure bs = pdo.getBundleStructure();
                    int iMax = bs.getEntryCount();
                    for (int i = 0; i < iMax; i++ ) {
                        PropertiesFileEntry pfe = bs.getNthEntry(i);
                        //FileObject fo2 = pfe.getFile();
                        dob = pfe.getDataObject();
                    }
                }
                return dob;
            }
        }
    }
}