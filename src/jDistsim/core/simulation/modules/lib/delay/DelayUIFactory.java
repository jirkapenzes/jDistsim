package jDistsim.core.simulation.modules.lib.delay;

import jDistsim.core.simulation.modules.IModuleUIFactory;
import jDistsim.ui.dialog.BaseModuleSettingsDialog;
import jDistsim.ui.dialog.DelayModuleSettingsDialog;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 27.2.13
 * Time: 1:49
 */
public class DelayUIFactory implements IModuleUIFactory<Delay> {

    @Override
    public BaseModuleSettingsDialog makeSettingsDialog(JFrame parent, Delay module) {
        return new DelayModuleSettingsDialog(parent, module);
    }
}
