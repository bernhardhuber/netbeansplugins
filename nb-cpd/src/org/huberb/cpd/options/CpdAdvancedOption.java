package org.huberb.cpd.options;

import org.netbeans.spi.options.AdvancedOption;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.NbBundle;

public final class CpdAdvancedOption extends AdvancedOption {
    
    @Override
    public String getDisplayName() {
        return NbBundle.getMessage(CpdAdvancedOption.class, "AdvancedOption_DisplayName");
    }
    
    @Override
    public String getTooltip() {
        return NbBundle.getMessage(CpdAdvancedOption.class, "AdvancedOption_Tooltip");
    }
    
    @Override
    public OptionsPanelController create() {
        return new CpdOptionsPanelController();
    }
    
}
