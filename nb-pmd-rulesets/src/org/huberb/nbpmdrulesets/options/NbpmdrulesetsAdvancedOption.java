package org.huberb.nbpmdrulesets.options;

import org.netbeans.spi.options.AdvancedOption;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.NbBundle;

public final class NbpmdrulesetsAdvancedOption extends AdvancedOption {
    
    public String getDisplayName() {
        return NbBundle.getMessage(NbpmdrulesetsAdvancedOption.class, "AdvancedOption_DisplayName");
    }
    
    public String getTooltip() {
        return NbBundle.getMessage(NbpmdrulesetsAdvancedOption.class, "AdvancedOption_Tooltip");
    }
    
    public OptionsPanelController create() {
        return new NbpmdrulesetsOptionsPanelController();
    }
    
}
