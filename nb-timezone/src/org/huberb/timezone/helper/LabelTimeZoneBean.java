package org.huberb.timezone.helper;

import java.util.TimeZone;

/**
 * A label-value using <code>TimeZone</code> as its value attribute type
 */
public class LabelTimeZoneBean extends LabelValueBean<TimeZone> {

    LabelTimeZoneBean(String l, TimeZone tz) {
        super(l, tz);
    }

    @Override
    public int hashCode() {
        TimeZone tz = this.getValue();
        return tz.getID().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj instanceof LabelTimeZoneBean) {
            LabelTimeZoneBean ltzb = (LabelTimeZoneBean) obj;
            return this.getValue().getID().equals(ltzb.getValue().getID());
        }
        return false;
    }
}
