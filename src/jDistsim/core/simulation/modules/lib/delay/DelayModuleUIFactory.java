package jDistsim.core.simulation.modules.lib.delay;

import jDistsim.core.simulation.modules.IModuleUIFactory;
import jDistsim.core.simulation.modules.lib.delay.DelayModule;
import jDistsim.ui.dialog.BaseModuleSettingsDialog;
import jDistsim.ui.dialog.DelayModuleSettingsDialog;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 27.2.13
 * Time: 1:49
 */
public class DelayModuleUIFactory implements IModuleUIFactory<DelayModule> {

    @Override
    public BaseModuleSettingsDialog makeSettingsDialog(JFrame parent, DelayModule module) {
        return new DelayModuleSettingsDialog(parent, module);
    }
}
