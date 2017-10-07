package org.huber.keytool.ui.filefilter;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * An abstract FileFilter
 */
public abstract class AbstractFilter extends FileFilter {
    private final String extension;
    private final String description;
    
    protected AbstractFilter( String newExtension, String newDescription ) {
        this.extension = newExtension;
        this.description = newDescription;
    }
    
    public boolean accept(File f) {
        boolean accept = false;
        
        accept = accept || f.isDirectory();
        if (!accept) {
            boolean acceptFile = true;
            acceptFile = acceptFile && f.isFile();
            acceptFile = acceptFile && f.canRead();
            acceptFile = acceptFile && f.getName().toLowerCase().endsWith( this.extension );
            
            accept = accept || acceptFile;
        }
        
        return accept;
    }
    
    public String getDescription() {
        return this.description;
    }
    
}