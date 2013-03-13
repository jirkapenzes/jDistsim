package jDistsim.core.simulation.modules.lib.condition;

import jDistsim.core.simulation.modules.IModuleUIFactory;
import jDistsim.ui.dialog.BaseModuleSettingsDialog;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 14.3.13
 * Time: 0:00
 */
public class ConditionUIFactory implements IModuleUIFactory<Condition> {

    @Override
    public BaseModuleSettingsDialog makeSettingsDialog(JFrame parent, Condition module) {
        return null;
    }
}
