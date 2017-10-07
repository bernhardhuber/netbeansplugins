/*
 * ChangeObserverOfWizardPanel.java
 *
 * Created on 17. Februar 2006, 21:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.ui.wizard;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import org.huber.keytool.ui.bind.CaretChangeListenerBridge;
import org.huber.keytool.ui.bind.DocumentChangeListenerBridge;
import org.huber.keytool.ui.bind.PropertyChangeListenerBridge;
import org.openide.ErrorManager;
import org.openide.util.WeakListeners;

/**
 *
 * @author HuberB1
 */
public class ChangeObserverOfWizardPanel {
    private AbstractWizardDescriptorPanel awdp;
    
    private ChangeListener changeListener;
    private CaretListener caretListener;
    private ActionListener actionListener;
    private DocumentListener documentListener;
    private PropertyChangeListener propertyChangeListener;
    
    /**
     * Creates a new instance of ChangeObserverOfWizardPanel
     */
    public ChangeObserverOfWizardPanel(AbstractWizardDescriptorPanel awdp) {
        this.awdp = awdp;
    }
    
    /**
     * Bind a CaretListener.
     * <p>
     * This method propagates caret events to wizard panel change events.
     *
     * @param source an object implementing addCaretListener.
     */
    public boolean bindCaretListener( Object source ) {
        boolean bound = false;
        try {
            final CaretListener caretListener = getCaretListener();
            final CaretListener weakCaretListener = (CaretListener) WeakListeners.create( CaretListener.class, caretListener, source );
            
            final Method m = source.getClass().getMethod( "addCaretListener", CaretListener.class );
            m.invoke( source, weakCaretListener );
            bound = true;
        } catch (Exception ex) {
            ErrorManager.getDefault().notify( ex );
        }
        return bound;
    }
    
    /**
     * Bind a ChangeListener.
     * <p>
     * This method propagates change events to wizard panel change events.
     *
     * @param source an object implementing addChangeListener.
     */
    public boolean bindChangeListener( Object source ) {
        boolean bound = false;
        try {
            final ChangeListener changeListener = getChangeListener();
            final ChangeListener weakChangeListener = WeakListeners.change( changeListener, source );
            
            final Method m = source.getClass().getMethod("addChangeListener", ChangeListener.class);
            m.invoke( source, weakChangeListener );
            bound = true;
        } catch (Exception ex) {
            ErrorManager.getDefault().notify( ex );
        }
        return bound;
    }
    
    /**
     * Bind a PropertyChangeListener.
     * <p>
     * This method propagates property change events to wizard panel change events.
     *
     * @param source an object implementing addPropertyChangeListener.
     */
    public boolean bindPropertyChangeListener( Object source ) {
        boolean bound = false;
        try {
            final PropertyChangeListener pcl = getPropertyChangeListener();
            final PropertyChangeListener weakPcl = (PropertyChangeListener)WeakListeners.create( PropertyChangeListener.class, pcl, source );
            
            final Method m = source.getClass().getMethod( "addPropertyChangeListener", PropertyChangeListener.class );
            m.invoke( source, weakPcl );
            bound = true;
        } catch (Exception ex) {
            ErrorManager.getDefault().notify( ex );
        }
        return bound;
    }
    
    /**
     * Bind a PropertyChangeListener.
     * <p>
     * This method propagates property change events to wizard panel change events.
     *
     * @param source an object implementing addPropertyChangeListener.
     * @param propertyNames property names registering for.
     */
    public boolean bindPropertyChangeListener(Object source, String[] propertyNames) {
        boolean bound = false;
        try {
            final PropertyChangeListener pcl = getPropertyChangeListener();
            final PropertyChangeListener weakPcl = (PropertyChangeListener)WeakListeners.create( PropertyChangeListener.class, pcl, source );
            
            final Method m = source.getClass().getMethod( "addPropertyChangeListener", String.class, PropertyChangeListener.class );
            for (int i = 0; i < propertyNames.length; i++ ) {
                final String propertyName = propertyNames[i];
                m.invoke( source, propertyName, weakPcl );
            }
            bound = true;
        } catch (Exception ex) {
            ErrorManager.getDefault().notify( ex );
        }
        return bound;
    }
    
    /**
     * Bind a DocumentListener.
     * <p>
     * This method propagates document events to wizard panel change events.
     *
     * @param source an object implementing addDocumentListener.
     */
    public boolean bindDocumentListener( Object source ) {
        boolean bound = false;
        try {
            final DocumentListener documentListener = getDocumentListener();
            final DocumentListener weakDocumentListener = (DocumentListener) WeakListeners.create( DocumentListener.class, documentListener, source );
            
            final Method m = source.getClass().getMethod( "addDocumentListener", DocumentListener.class );
            m.invoke( source, weakDocumentListener );
            bound = true;
        } catch (Exception ex) {
            ErrorManager.getDefault().notify( ex );
        }
        return bound;
    }
    
    //---
    public ChangeListener getChangeListener() {
        if (this.changeListener == null) {
            this.changeListener = new WizardPanelChangeListener(this.awdp);
        }
        return changeListener;
    }
    
    public CaretListener getCaretListener() {
        if (this.caretListener == null) {
            this.caretListener = new CaretChangeListenerBridge(this.getChangeListener());
        }
        return this.caretListener;
    }
        
    private PropertyChangeListener getPropertyChangeListener() {
        if (this.propertyChangeListener == null) {
            this.propertyChangeListener = new PropertyChangeListenerBridge( this.getChangeListener() );
        }
        return this.propertyChangeListener;
    }
    
    private DocumentListener getDocumentListener() {
        if (this.documentListener == null) {
            this.documentListener = new DocumentChangeListenerBridge( this.getChangeListener() );
        }
        return this.documentListener;
    }
}
