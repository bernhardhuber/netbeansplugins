/*
 * PropertiesKeyUsageHelper.java
 *
 * Created on 21. Mai 2007, 07:38
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.matcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 *
 * @author HuberB1
 */
public class PropertiesKeyUsageHelper {
    private Properties props;
    private Map<String,String> keysToFiles;
    
    /**
     * Creates a new instance of PropertiesKeyUsageHelper
     */
    public PropertiesKeyUsageHelper() {
        init();
    }
    
    protected void init() {
        this.props = new Properties();
        this.keysToFiles = new TreeMap<String,String>();
    }
    
    public void setProps(Properties props) {
        this.props = props;
    }
    
    public Map<String, String> getKeysToFiles() {
        return keysToFiles;
    }
    
    public void scanFile(File f) {
        try {
            FileInputStream fis = new FileInputStream(f);
            
            try {
                ByteBuffer bb = fis.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, f.length());
                
                byte[] b = new byte[(int) f.length()];
                bb.get(b);
                String search = new String(b);
                
                for (Enumeration en = props.keys(); en.hasMoreElements(); ) {
                    final String key = (String)en.nextElement();
                    final String s = "\"" + key + "\"";
                    if (search.indexOf(s) != -1) {
                        StringBuilder sb = new StringBuilder();
                        
                        String xxx = (String) keysToFiles.get(key);
                        if (xxx != null) {
                            sb.append( xxx );
                        }
                        
                        if (sb.length() == 0) {
                            sb.append( f.getName() );
                        } else {
                            sb.append( "," );
                            sb.append( f.getName() );
                        }
                        keysToFiles.put(key, sb.toString() );
                    } else if (s.indexOf("OpenIDE") != -1 || s.indexOf("/") != -1) {
                        keysToFiles.put(key, "RETAINED");
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                try {
                    fis.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
