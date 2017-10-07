/*
 * ActionStateListener.java
 *
 * Created on 19. Februar 2006, 15:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.huber.keytool.model.KeyStoreBean;
import org.openide.ErrorManager;

/**
 * A Listener of the application state.
 * <p>
 * Propagate the application state to its delegate objects.
 *
 * @author HuberB1
 */
public class ActionStateListener implements PropertyChangeListener {
    private List<ActionStateListenerElement> sourceObjects;
    
    /**
     * Creates a new instance of ActionStateListener
     */
    public ActionStateListener() {
        this.sourceObjects = new ArrayList<ActionStateListenerElement>();
    }
    
    /**
     * Register actions which shall change its state,
     * depending on a keystore bean is defined.
     *
     * @param actions the actions which change its states
     */
    public void setEnableObjectsIfLoaded( Object[] actions ) {
        for (int i = 0; i < actions.length; i++ ) {
            final Object obj = actions[i];
            try {
                final Method m = obj.getClass().getMethod("setEnabled", boolean.class);
                
                final ActionStateListenerElement asle = new ActionStateListenerElement( obj, m );
                sourceObjects.add( asle );
                
            } catch (SecurityException ex) {
                ErrorManager.getDefault().notify(ex);
            } catch (NoSuchMethodException ex) {
                ErrorManager.getDefault().notify(ex);
            }
        }
    }
    
    /**
     * Invoke this method a keystore bean changes.
     *
     * @param ect the property change event that a keystore bean object has changed
     */
    public void propertyChange(PropertyChangeEvent evt) {
        final String propertyName = evt.getPropertyName();
        
        if (KeyStoreTopComponent.KEY_STORE_BEAN_PROP_NAME.equals( propertyName )) {
            final KeyStoreBean newValue = (KeyStoreBean)evt.getNewValue();
            
            final boolean isEnabled = this.isEnabled( newValue );
            handleKeyStoreLoaded( isEnabled );
        }
    }
    
    protected boolean isEnabled( KeyStoreBean ksb ) {
        boolean isEnabled = ksb.isKeyStoreLoaded();
        return isEnabled;
    }

    /**
     * Toggle the state of the registered actions.
     *
     * @param newEnabled propagate this value to the enabled status of the 
     * registered actions
     */
    protected void handleKeyStoreLoaded(boolean newEnabled) {
        for( ActionStateListenerElement asle : this.sourceObjects) {
            final Object source = asle.getSource();
            final Method m = asle.getMethod();
            try {
                m.invoke( source, newEnabled );
            } catch (IllegalArgumentException ex) {
                ErrorManager.getDefault().notify(ex);
            } catch (IllegalAccessException ex) {
                ErrorManager.getDefault().notify(ex);
            } catch (InvocationTargetException ex) {
                ErrorManager.getDefault().notify(ex);
            }
        }
    }

    /**
     * Encapsulate an object, and its method.
     * Use an object of this class to invoke later on
     * <code>object.method()</code>
     */
    static class ActionStateListenerElement {
        private Object o;
        private Method m;
        public ActionStateListenerElement() {
            
        }
        public ActionStateListenerElement(Object o, Method m) {
            this.o = o;
            this.m = m;
        }
        public Object getSource() {
            return this.o;
        }
        public void setSource(Object o) {
            this.o = o;
        }
        public Method getMethod() {
            return this.m;
        }
        public void setMethod( Method m ) {
            this.m = m;
        }
    }
}
