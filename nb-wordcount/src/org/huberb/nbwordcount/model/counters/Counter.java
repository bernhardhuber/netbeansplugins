package org.huberb.nbwordcount.model.counters;

/**
 * A simple Counter class.
 * <p>
 * This class stores a Counter value. It allows to increment, reset, and
 * retrieve the value of the counter.
 *
 */
public class Counter extends Number {
    private static final long serialVersionUID = 20060123112200L;
    
    private long counterValue;
    
    /** Creates a new instance of Counter */
    public Counter() {
        this(0L);
    }
    /** Creates a new instance of Counter */
    public Counter(long initialValue) {
        this.reset(initialValue);
    }
    
    /**
     * Reset the counter value to zero.
     */
    public final void reset(long resetValue) {
        this.counterValue = resetValue;
    }
    
    /**
     * Increment the counter value.
     *
     * @param delta the incremental value
     */
    public void increment(long delta) {
        this.counterValue += delta;
    }
    /**
     * Retrieve the counter value
     *
     * @return long the counter value
     */
    public long getCounterValue() {
        return this.counterValue;
    }
    
    // implement abstract Number methods start
    public int intValue() {
        return (int) this.counterValue;
    }
    
    public long longValue() {
        return this.counterValue;
    }
    
    public float floatValue() {
        return (float) this.counterValue;
    }
    
    public double doubleValue() {
        return (double) this.counterValue;
    }
    // implement abstract Number methods end
    
    public int hashCode() {
        int hashCode = (int)this.getCounterValue();
        return hashCode;
    }
    
    public boolean equals( Object obj ) {
        boolean equals = false;
        if (obj == this) {
            equals = true;
        } else {
            if (obj != null && obj instanceof Counter) {
                Counter otherCounter = (Counter)obj;
                equals = (otherCounter.getCounterValue() == this.getCounterValue());
            }
        }
        return equals;
    }
    
    public String toString() {
        return String.valueOf(this.counterValue);
    }
    
}