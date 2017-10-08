/*
 * TapPanel.java
 *
 * Created on 24. September 2006, 08:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.httppost.ui;
//org.huberb.httppost.ui.TapPanel
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.UIManager;

/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2004 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

/**
 * Panel that can collapse to a small size and be reexpanded.
 *
 * @author Tim Boudreau
 */
public final class TapPanel extends javax.swing.JPanel {
    public static final int UP = 0;
    public static final int DOWN = 2;
    
    public static final String PROP_ORIENTATION = "orientation"; //NOI18N
    private int orientation = UP;
    private boolean armed = false;
    private boolean expanded = true;
    private int minimumHeight = 8;
    
    /**
     * Creates a new instance of TapPanel
     */
    public TapPanel() {
        setLayout( new TrivialLayout() );
    }
    
    private static WeakReference<Adap> adapRef = null;
    
    static class Adap extends MouseAdapter implements MouseMotionListener {
        MouseListener other = null;
        
        public void mouseEntered(MouseEvent e) {
            ( (TapPanel) e.getSource() ).setArmed( true );
        }
        
        public void mouseExited(MouseEvent e) {
            ( (TapPanel) e.getSource() ).setArmed( false );
        }
        
        public void mouseMoved(MouseEvent e) {
            ( (TapPanel) e.getSource() ).setArmed( ( (TapPanel) e.getSource() ).isArmPoint( e.getPoint() ) );
        }
        
        public void mousePressed(MouseEvent e) {
            if ( ( (TapPanel) e.getSource() ).isArmPoint( e.getPoint() ) ) {
                ( (TapPanel) e.getSource() ).setExpanded( !( (TapPanel) e.getSource() ).isExpanded() );
                e.consume();
            } else if ( other != null ) {
                other.mousePressed( e );
            }
        }
        
        public void mouseDragged(MouseEvent e) {
            //do nothing
        }
    }
    
    private static Adap getAdapter() {
        Adap result = null;
        if ( adapRef != null ) {
            result = (Adap) adapRef.get();
        }
        if ( result == null ) {
            result = new Adap();
            adapRef = new WeakReference( result );
        }
        return result;
    }
    
    /**
     * Allows mouse clicks *not* in the expansion bar to cause the navigator component to become activated, but the user
     * can click to expand/collapse without activating the component.
     */
    void setSecondaryMouseHandler(MouseListener lis) {
        getAdapter().other = lis;
    }
    
    public void addNotify() {
        addMouseMotionListener( getAdapter() );
        addMouseListener( getAdapter() );
        super.addNotify();
    }
    
    public void removeNotify() {
        super.removeNotify();
        removeMouseMotionListener( getAdapter() );
        removeMouseListener( getAdapter() );
    }
    
    public int getOrientation() {
        return orientation;
    }
    
    public void setOrientation(int i) {
        if ( i != orientation ) {
            int oldOr = i;
            orientation = i;
            firePropertyChange( PROP_ORIENTATION, oldOr, i );
        }
    }
    
    private void setArmed(boolean val) {
        if ( val != armed ) {
            armed = val;
            repaint();
        }
    }
    
    public boolean isExpanded() {
        return expanded;
    }
    
    public Dimension getPreferredSize() {
        return getLayout().preferredLayoutSize( this );
    }
    
    public Dimension getMinimumSize() {
        Dimension d = getPreferredSize();
        d.width = 20;
        return d;
    }
    
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
    
    public void setExpanded(boolean b) {
        if ( expanded != b ) {
            Dimension d = getPreferredSize();
            expanded = b;
            Dimension d1 = getPreferredSize();
            if ( isDisplayable() ) {
                revalidate();
            }
        }
    }
    
    private boolean isArmPoint(Point p) {
        if ( !expanded ) {
            return p.y > 0 && p.y < getHeight();
        } else {
            if ( orientation == UP ) {
                return p.y > getHeight() - minimumHeight;
            } else {
                return p.y < minimumHeight;
            }
        }
    }
    
    public void updateBorder() {
        if ( orientation == UP ) {
            super.setBorder( BorderFactory.createEmptyBorder( 0, 0, minimumHeight, 0 ) );
        } else {
            super.setBorder( BorderFactory.createEmptyBorder( minimumHeight, 0, 0, 0 ) );
        }
    }
    
    public int getMinimumHeight() {
        return minimumHeight;
    }
    
    public void setBorder() {
        //do nothing
    }
    
    public void paintBorder(Graphics g) {
        Color c = armed ? UIManager.getColor( "List.selectionBackground" ) : getBackground(); //NOI18N
        int x = 0;
        int y = orientation == UP ? 1 + ( getHeight() - minimumHeight ) : 0;
        int w = getWidth();
        int h = minimumHeight - 1;
        g.setColor( c );
        g.fillRect( x, y, w, h );
        
        int pos = orientation == UP ? getHeight() - 1 : 0;
        int dir = orientation == UP ? -1 : 1;
        g.setColor( armed ? c.darker() : UIManager.getColor( "controlShadow" ) ); //NOI18N
        g.drawLine( 0, pos, w, pos );
        pos += dir;
        
        if ( ( orientation == UP ) == expanded ) {
            up.paintIcon( this, g, ( getWidth() / 2 ) - ( up.getIconWidth() / 2 ),
                    getHeight() - ( minimumHeight + ( expanded ? 0 : -1 ) ) );
        } else {
            down.paintIcon( this, g, ( getWidth() / 2 ) - ( up.getIconWidth() / 2 ), expanded ? 2 : 1 );
        }
    }
    
    public void paintChildren(Graphics g) {
        if ( !expanded ) {
            return;
        }
        super.paintChildren(g);
    }
    
    private Icon up = new UpIcon();
    private Icon down = new DownIcon();
    
    private static int ICON_SIZE = 8;
    
    private class UpIcon implements Icon, Serializable {
        protected final static long serialVersionUID = 20060925152500L;
        public int getIconHeight() {
            return ICON_SIZE - 2;
        }
        
        public int getIconWidth() {
            return ICON_SIZE + 2;
        }
        
        public void paintIcon(java.awt.Component c, Graphics g, int x, int y) {
            
            g.setColor( armed ?
                UIManager.getColor( "List.selectionForeground" ) : //NOI18N
                UIManager.getColor( "controlShadow" ) ); //NOI18N
//            int[] xPoints = new int[] {x+getIconWidth()/2, x+getIconWidth(), x};
//            int[] yPoints = new int[] {y, y+getIconHeight()-1, y+getIconHeight()-1};
            int[] xPoints = new int[]{x, x + 8, x + 4};
            int[] yPoints = new int[]{y + 5, y + 5, y};
            
            g.fillPolygon( xPoints, yPoints, 3 );
        }
    }
    
    private class DownIcon implements Icon, Serializable {
        protected final static long serialVersionUID = 20060925152500L;
        
        public int getIconHeight() {
            return ICON_SIZE - 3;
        }
        
        public int getIconWidth() {
            return ICON_SIZE + 2;
        }
        
        public void paintIcon(java.awt.Component c, Graphics g, int x, int y) {
            x++;
            g.setColor( armed ?
                UIManager.getColor( "List.selectionForeground" ) : //NOI18N
                UIManager.getColor( "controlShadow" ) ); //NOI18N
            
//            int[] xPoints = new int[] {(x+getIconWidth()/2), x+getIconWidth()-1, x};
//            int[] yPoints = new int[] {y+getIconHeight()-2, y, y};
            
            int[] xPoints = new int[]{x, x + 8, x + 4};
            int[] yPoints = new int[]{y, y, y + 4};
            
            g.fillPolygon( xPoints, yPoints, 3 );
        }
        
    }
    
}
