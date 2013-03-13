package jDistsim.core.simulation.modules.lib.outputer;

import jDistsim.core.simulation.modules.IModuleUIFactory;
import jDistsim.ui.dialog.BaseModuleSettingsDialog;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 13.3.13
 * Time: 23:29
 */
public class OutputerUIFactory implements IModuleUIFactory<Outputer> {
    @Override
    public BaseModuleSettingsDialog makeSettingsDialog(JFrame parent, Outputer module) {
        return null;
    }
}
