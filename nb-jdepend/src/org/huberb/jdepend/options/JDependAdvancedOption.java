package org.huberb.jdepend.options;

import org.netbeans.spi.options.AdvancedOption;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.NbBundle;

public final class JDependAdvancedOption extends AdvancedOption {
    
    public String getDisplayName() {
        return NbBundle.getMessage(JDependAdvancedOption.class, "AdvancedOption_DisplayName");
    }
    
    public String getTooltip() {
        return NbBundle.getMessage(JDependAdvancedOption.class, "AdvancedOption_Tooltip");
    }
    
    public OptionsPanelController create() {
        return new JDependOptionsPanelController();
    }
    
}
