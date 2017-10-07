/*
 * PropertySupportReflectionFactory.java
 *
 * Created on 29. September 2006, 14:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.httppost.node;

import org.openide.nodes.Node;
import org.openide.nodes.PropertySupport;

/**
 *
 * @author HuberB1
 */
public class PropertySupportReflectionFactory {
    
    /** Creates a new instance of PropertySupportReflectionFactory */
    private PropertySupportReflectionFactory() {
    }
    
    public static Node.Property createNodeProperty( Object obj, Class type, String name ) throws NoSuchMethodException {
        return createNodeProperty( obj, type, name, true, true );
    }
    
    public static Node.Property createNodeProperty( Object obj, Class type, String name,
            final boolean canRead, final boolean canWrite ) throws NoSuchMethodException {
        
        PropertySupport.Reflection propertySupport = new PropertySupport.Reflection( obj, type, name ) {
            public boolean canRead() {
                return canRead;
            }
            public boolean canWrite() {
                return canWrite;
            }
        };
        propertySupport.setName( name );
        return propertySupport;
    }

    static Node.Property createNodeProperty(Object obj, Class type, String name, 
            String getMethodName, String setMethodName, 
            final boolean canRead, final boolean canWrite) throws NoSuchMethodException {
        
        PropertySupport.Reflection propertySupport = new PropertySupport.Reflection( obj, type, getMethodName, setMethodName ) {
            public boolean canRead() {
                return canRead;
            }
            public boolean canWrite() {
                return canWrite;
            }
        };
        propertySupport.setName( name );
        return propertySupport;
    }
    
}
