package jDistsim.core.modules.factory.ui;

import jDistsim.core.modules.IModuleUIFactory;
import jDistsim.core.modules.lib.CreateModule;
import jDistsim.ui.dialog.BaseModuleSettingsDialog;
import jDistsim.ui.dialog.CreateModuleSettingsDialog;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 25.2.13
 * Time: 10:29
 */
public class CreateModuleUIFactory implements IModuleUIFactory<CreateModule> {

    @Override
    public BaseModuleSettingsDialog makeSettingsDialog(JFrame parent, CreateModule module) {
        return new CreateModuleSettingsDialog(parent, module);
    }
}
