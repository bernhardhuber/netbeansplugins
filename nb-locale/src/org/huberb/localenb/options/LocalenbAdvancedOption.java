package org.huberb.localenb.options;

import org.netbeans.spi.options.AdvancedOption;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.NbBundle;

public final class LocalenbAdvancedOption extends AdvancedOption {
    
    @Override
    public String getDisplayName() {
        return NbBundle.getMessage(LocalenbAdvancedOption.class, "AdvancedOption_DisplayName");
    }
    
    @Override
    public String getTooltip() {
        return NbBundle.getMessage(LocalenbAdvancedOption.class, "AdvancedOption_Tooltip");
    }
    
    @Override
    public OptionsPanelController create() {
        return new LocalenbOptionsPanelController();
    }
    
}
