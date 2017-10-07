package org.huber.keytool.settings;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.spi.options.OptionsCategory;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;

public final class KeytoolOptionsCategory extends OptionsCategory {
    
    public Icon getIcon() {
        return new ImageIcon(Utilities.loadImage("org/huber/keytool/images/password-32x32.png"));
    }
    
    public String getCategoryName() {
        return NbBundle.getMessage(KeytoolOptionsCategory.class, "OptionsCategory_Name");
    }
    
    public String getTitle() {
        return NbBundle.getMessage(KeytoolOptionsCategory.class, "OptionsCategory_Title");
    }
    
    public OptionsPanelController create() {
        return new KeytoolOptionsPanelController();
    }
    
}
