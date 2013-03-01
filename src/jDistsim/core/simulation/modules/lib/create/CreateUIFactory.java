package jDistsim.core.simulation.modules.lib.create;

import jDistsim.core.simulation.modules.IModuleUIFactory;
import jDistsim.ui.dialog.BaseModuleSettingsDialog;
import jDistsim.ui.dialog.CreateModuleSettingsDialog;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 25.2.13
 * Time: 10:29
 */
public class CreateUIFactory implements IModuleUIFactory<Create> {

    @Override
    public BaseModuleSettingsDialog makeSettingsDialog(JFrame parent, Create module) {
        return new CreateModuleSettingsDialog(parent, module);
    }
}
