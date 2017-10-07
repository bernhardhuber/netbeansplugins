package org.huberb.cpd.options;

import org.netbeans.spi.options.AdvancedOption;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.NbBundle;

public final class CpdAdvancedOption extends AdvancedOption {
    
    public String getDisplayName() {
        return NbBundle.getMessage(CpdAdvancedOption.class, "AdvancedOption_DisplayName");
    }
    
    public String getTooltip() {
        return NbBundle.getMessage(CpdAdvancedOption.class, "AdvancedOption_Tooltip");
    }
    
    public OptionsPanelController create() {
        return new CpdOptionsPanelController();
    }
    
}
