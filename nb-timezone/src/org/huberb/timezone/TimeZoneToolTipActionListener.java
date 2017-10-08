/*
 * TimeZoneToolTipActionListener.java
 *
 * Created on 03. März 2006, 11:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.timezone;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimeZone;

import javax.swing.JComboBox;

import org.huberb.timezone.helper.LabelTimeZoneBean;
import org.openide.util.NbBundle;

/**
 * A tool tip listener displaying details about a time zone.
 *
 * @author HuberB1
 */
public class TimeZoneToolTipActionListener implements ActionListener {

    /**
     * Creates a new instance of TimeZoneToolTipActionListener
     */
    public TimeZoneToolTipActionListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object eSource = e.getSource();
        if (eSource != null && eSource instanceof JComboBox) {
            final JComboBox cb = (JComboBox) eSource;
            final Object cbSelectedItem = cb.getSelectedItem();
            if (cbSelectedItem != null && cbSelectedItem instanceof LabelTimeZoneBean) {
                final LabelTimeZoneBean selectedLabelTimeZoneBean = (LabelTimeZoneBean) cbSelectedItem;

                // update the tool tip
                final String newToolTipMessage = formatTimeZone(selectedLabelTimeZoneBean.getValue());
                cb.setToolTipText(newToolTipMessage);
            }
        }
    }

    /**
     * Format the time zone for displaying it in a tool tip
     *
     * @param tz the time zone displayed in the tool tip
     * @return String the tool tip
     */
    private String formatTimeZone(TimeZone tz) {
        final int MILLIS_IN_SECOD = 1000;
        final int SECONDS_IN_HOUR = 60 * 60;

        final int rawOffset = tz.getRawOffset();
        final int rawOffsetInSeconds = rawOffset / MILLIS_IN_SECOD;
        final int rawOffsetH = rawOffsetInSeconds / SECONDS_IN_HOUR;
        final int rawOffsetM = rawOffsetInSeconds % SECONDS_IN_HOUR;

        final int dstSavings = tz.getDSTSavings();
        final int dstSavingsInSeconds = dstSavings / MILLIS_IN_SECOD;
        final int dstSavingsH = dstSavingsInSeconds / SECONDS_IN_HOUR;
        final int dstSvingsM = dstSavingsInSeconds % SECONDS_IN_HOUR;

        final Object[] args = {
            tz.getID(),
            tz.getDisplayName(), rawOffsetH, rawOffsetM, dstSavingsH, dstSvingsM
        };

        final String tooltipText = NbBundle.getMessage(TimeZoneToolTipActionListener.class,
                "CTL_TimeZoneToolTip",
                args);
        return tooltipText;
    }
}
