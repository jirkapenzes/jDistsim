package jDistsim.core.simulation.modules.lib.assign;

import jDistsim.core.simulation.modules.IModuleUIFactory;
import jDistsim.ui.dialog.AssignModuleSettingsDialog;
import jDistsim.ui.dialog.BaseModuleSettingsDialog;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 3.3.13
 * Time: 13:40
 */
public class AssignUIFactory implements IModuleUIFactory<Assign> {

    @Override
    public BaseModuleSettingsDialog makeSettingsDialog(JFrame parent, Assign module) {
        return new AssignModuleSettingsDialog(parent, module);
    }
}
