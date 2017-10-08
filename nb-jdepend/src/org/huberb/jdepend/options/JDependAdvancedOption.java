package org.huberb.jdepend.options;

import org.netbeans.spi.options.AdvancedOption;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.NbBundle;

public final class JDependAdvancedOption extends AdvancedOption {

    @Override
    public String getDisplayName() {
        return NbBundle.getMessage(JDependAdvancedOption.class, "AdvancedOption_DisplayName");
    }

    @Override
    public String getTooltip() {
        return NbBundle.getMessage(JDependAdvancedOption.class, "AdvancedOption_Tooltip");
    }

    @Override
    public OptionsPanelController create() {
        return new JDependOptionsPanelController();
    }

}
