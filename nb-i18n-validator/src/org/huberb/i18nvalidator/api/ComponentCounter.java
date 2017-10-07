package org.huberb.i18nvalidator.api;

public class ComponentCounter<T extends AbstractComponent> extends AbstractComponent {
    private T fi;
    private Integer count;
    
    public ComponentCounter(T fi) {
        this.fi = fi;
        this.count = 0;
    }
    public void increment() {
        count += 1;
    }
    public Integer getCount() {
        return this.count;
    }
    public T getComponent() {
        return this.fi;
    }
    
    @Override
    public int hashCode() {
        int retValue = this.fi.hashCode();
        return retValue;
    }
    @Override
    public boolean equals(Object obj) {
        boolean equals = this.fi.equals(obj);
        return equals;
    }
    
}