/*
 * IOHelper.java
 *
 * Created on 23. Mai 2007, 08:48
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.misc;

import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

/**
 *
 * @author HuberB1
 */
public class IOHelper {
    private String name = "---";
    private boolean newIO = false;
    
    /** Creates a new instance of IOHelper */
    public IOHelper() {
        this("---");
    }
    /** Creates a new instance of IOHelper */
    public IOHelper(String name) {
        this(name,false);
    }
    /** Creates a new instance of IOHelper */
    public IOHelper(String name, boolean newIO) {       
        this.name = name;
        this.newIO = newIO;
    }
    
    private InputOutput createIO() {
        IOProvider iop = IOProvider.getDefault();
        InputOutput io = iop.getIO( name, newIO );
        return io;
    }
        
    public OutputWriter getOut() {
        InputOutput io = createIO();
        return io.getOut();
    }
    
}
