package org.huberb.i18nvalidator.spi;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.WeakHashMap;
import org.huberb.i18nvalidator.api.*;

/**
 *
 * @author HuberB1
 */
public abstract class AbstractVisitor {
    
    protected PropertyChangeSupport pcs;
    private WeakHashMap<Class,Method> methodMap;
    
    public static final String VISTING_PROP = "visiting";
    public AbstractVisitor() {
        this.methodMap = new WeakHashMap<Class,Method>();
        this.pcs = new PropertyChangeSupport(this);
    }
    //-------------------------------------------------------------------------
    public void visit(AbstractComponent ac) {
        //System.out.println("???visit???" + ac );
        
        final Class acClass = ac.getClass();
        Method m = methodMap.get( acClass );
        if (m == null) {
            try {
                m = this.getClass().getMethod("_visit", acClass );
                methodMap.put( acClass, m );
            } catch (SecurityException ex) {
                ex.printStackTrace();
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            }
        }
        
        if (m != null) {
            try {
                pcs.firePropertyChange( VISTING_PROP, null, ac );
                m.invoke( this, ac );
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    //-------------------------------------------------------------------------
    public void _visit(ContextComposite cc) {
        AbstractComponent ac = cc.getContext();
        if (ac != null) {
            ac.accept( this );
        }
        _visit( (AbstractComposite)cc );
    }
    
    //----
    public void _visit(AbstractComposite abstractComposite) {
        for (Iterator<AbstractComponent> i = abstractComposite.getItems().iterator(); i.hasNext(); ) {
            AbstractComponent ac = i.next();
            ac.accept( this );
        }
    }
    //----
    public void _visit(FindInfoComposite fic) {
        _visit( (AbstractComposite)fic );
    }
    public void _visit(SearchInfoComposite fic) {
        _visit( (AbstractComposite)fic );
    }
    
    //-------------------------------------------------------------------------
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        this.pcs.addPropertyChangeListener(pcl);
    }
    public void addAllPropertyChangeListeners(PropertyChangeListener[] pcls) {
        for (int i = 0; i < pcls.length; i++ ) {
            this.pcs.addPropertyChangeListener( pcls[i] );
        }
    }
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        this.pcs.removePropertyChangeListener(pcl);
    }
    public PropertyChangeListener[] getPropertyChangeListeners() {
        return this.pcs.getPropertyChangeListeners();
    }
}