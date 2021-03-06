/*
 * TimeZoneHelper.java
 *
 * Created on 03. März 2006, 08:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.timezone.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;

import org.openide.util.NbBundle;

/**
 * Encapsulate timezone related methods in this class.
 *
 * @author HuberB1
 */
public class TimeZoneHelper {

    private static final Logger LOGGER = Logger.getLogger(TimeZoneHelper.class.getName());

    private String sourcePattern;
    private String targetPattern;

    /**
     * Creates a new instance of TimeZoneHelper
     */
    public TimeZoneHelper() {
    }

    public void setSourcePattern(String newPattern) {
        this.sourcePattern = newPattern;
    }

    public String getSourcePattern() {
        return this.sourcePattern;
    }

    public void setTargetPattern(String newPattern) {
        this.targetPattern = newPattern;
    }

    public String getTargetPattern() {
        return this.targetPattern;
    }

    public List<LabelTimeZoneBean> getTimeZoneIds() {
        final List<LabelTimeZoneBean> labelTimeZoneBeanList = new ArrayList<>();
        final String[] timeZoneIds = TimeZone.getAvailableIDs();
        for (String timeZoneId : timeZoneIds) {
            final TimeZone tz = TimeZone.getTimeZone(timeZoneId);
            final LabelTimeZoneBean lbv = new LabelTimeZoneBean(tz.getID(), tz);
            labelTimeZoneBeanList.add(lbv);
        }
        Collections.sort(labelTimeZoneBeanList, new LabelTimeZoneBeanCompartor());
        return labelTimeZoneBeanList;
    }

    public LabelTimeZoneBean getDefaultTimeZoneIds() {
        final TimeZone tz = TimeZone.getDefault();
        final LabelTimeZoneBean lbv = new LabelTimeZoneBean(tz.getID(), tz);
        return lbv;
    }

    public DefaultComboBoxModel getTimeZoneComboBoxModel() {
        List<LabelTimeZoneBean> l = getTimeZoneIds();
        LabelTimeZoneBean[] lAsArray = l.toArray(new LabelTimeZoneBean[0]);
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel(lAsArray);
        dcbm.setSelectedItem(getDefaultTimeZoneIds());
        return dcbm;
    }

    public Date convertToDate(String dateAsString) {
        final SimpleDateFormat sdf = new SimpleDateFormat(sourcePattern);

        Date date = null;

        try {
            date = sdf.parse(dateAsString);
        } catch (ParseException ex) {
            LOGGER.log(Level.INFO, "cannot convert to date " + dateAsString, ex);
        }

        return date;
    }

    public TimeZone convertToTimeZone(String tzAsString) {
        TimeZone tz = TimeZone.getTimeZone(tzAsString);
        return tz;
    }

    public String formatDate(Date date, TimeZone targetTimeZone) {
        final SimpleDateFormat sdf = new SimpleDateFormat(targetPattern);
        sdf.setTimeZone(targetTimeZone);

        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public String formatResult(String source, TimeZone targetTz, String result) {
        final Object[] args = {
            source,
            targetTz.getID(),
            result
        };
//        final String pattern =
//                "source: {0}, timezone ID {1}\n" +
//                "result: {2}" +
//                "\n";
//        final String formatted = MessageFormat.format( pattern, args );
        final String formatted = NbBundle.getMessage(TimeZoneHelper.class, "timeZoneHelper.formatResult", args);
        return formatted;
    }

    public String timeZoneOffsets(Date date, TimeZone targetTimeZone) {
        final long dateAsLong = date.getTime();
        int offsets[] = new int[2];
        int rawOffset = getOffsets(dateAsLong, targetTimeZone, offsets);

        final Boolean inDaylightTime = targetTimeZone.inDaylightTime(date);

        final Object[] args = {
            inDaylightTime, rawOffset, offsets[0], offsets[1]};
//        final String pattern =
//                "daylight saving time {0}, " +
//                "total rawOffset {1,number,integer}, " +
//                "GMT offset {2,number,integer}, " +
//                "DST offset {3,number,integer}" +
//                "\n";
//        final String formatted = MessageFormat.format( pattern, args );
        final String formatted = NbBundle.getMessage(TimeZoneHelper.class, "timeZoneHelper.timeZoneOffsets", args);
        return formatted;
    }

    private int getOffsets(long date, TimeZone tz, int[] offsets) {
        int rawoffset = tz.getRawOffset();
        int dstoffset = 0;
        if (tz.inDaylightTime(new Date(date))) {
            dstoffset = tz.getDSTSavings();
        }
        if (offsets != null) {
            offsets[0] = rawoffset;
            offsets[1] = dstoffset;
        }
        return rawoffset + dstoffset;
    }

    private static class LabelTimeZoneBeanCompartor implements Comparator<LabelTimeZoneBean> {

        @Override
        public int compare(LabelTimeZoneBean o1, LabelTimeZoneBean o2) {
            return o1.getLabel().compareTo(o2.getLabel());
        }
    }

}
