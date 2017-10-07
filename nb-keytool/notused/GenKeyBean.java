/*
 * GenKeyBean.java
 *
 * Created on 14. Februar 2006, 18:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package notused;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author HuberB1
 * @deprecated
 */
public class GenKeyBean implements Serializable {
    private static final long serialVersionUID = 200602141837L;
    
    /** Creates a new instance of GenKeyBean */
    public GenKeyBean() {
    }
    
    public void updateAttributes( final Object source ) {
        final Object target = this;
        String []attributes = {
            "alias",
            "keySize",
            "validity"
        };
        final Class sourceClass = source.getClass();
        final Class targetClass = target.getClass();
        for (int i = 0; i < attributes.length; i++ ) {
            final char firstLetter = attributes[i].charAt(0);
            final String nameSuffix = attributes[i].substring( 1 );
            final String getMethodName = "get" + Character.toUpperCase( firstLetter) + nameSuffix;
            final String setMethodName = "set" + Character.toUpperCase( firstLetter) + nameSuffix;
            try {
                Method methodSource = sourceClass.getMethod(getMethodName, null);
                final Object retVal = methodSource.invoke( source, null );
                
                Method methodTarget = targetClass.getMethod( setMethodName, retVal.getClass() );
                methodTarget.invoke( target, retVal );
                
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (SecurityException ex) {
                ex.printStackTrace();
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void updateBeanAttributes( Object source ) {
        try {
            BeanInfo sourceBeanInfo = Introspector.getBeanInfo( source.getClass() );
            BeanInfo targetBeanInfo = Introspector.getBeanInfo(this.getClass() );
            
            PropertyDescriptor[] sourcePds = sourceBeanInfo.getPropertyDescriptors();
            for (int i = 0; i < sourcePds.length; i++ ) {
                final String propertyName = sourcePds[i].getName();
                final Method readMethod = sourcePds[i].getReadMethod();
                
                readMethod.invoke( source, null );
            }
        } catch (Exception e) {

        }
    }
    
    /**
     * Holds value of property alias.
     */
    private String alias;
    
    /**
     * Getter for property alias.
     * @return Value of property alias.
     */
    public String getAlias() {
        return this.alias;
    }
    
    /**
     * Setter for property alias.
     * @param alias New value of property alias.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    /**
     * Holds value of property keySize.
     */
    private int keySize;
    
    /**
     * Getter for property keySize.
     * @return Value of property keySize.
     */
    public int getKeySize() {
        return this.keySize;
    }
    
    /**
     * Setter for property keySize.
     * @param keySize New value of property keySize.
     */
    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }
    
    /**
     * Holds value of property validity.
     */
    private long validity;
    
    /**
     * Getter for property validity.
     * @return Value of property validity.
     */
    public long getValidity() {
        return this.validity;
    }
    
    /**
     * Setter for property validity.
     * @param validity New value of property validity.
     */
    public void setValidity(long validity) {
        this.validity = validity;
    }
}
