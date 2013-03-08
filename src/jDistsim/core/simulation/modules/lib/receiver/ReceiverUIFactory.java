package jDistsim.core.simulation.modules.lib.receiver;

import jDistsim.core.simulation.modules.IModuleUIFactory;
import jDistsim.ui.dialog.BaseModuleSettingsDialog;
import jDistsim.ui.dialog.ReceiverModuleSettingsDialog;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 5.3.13
 * Time: 17:11
 */
public class ReceiverUIFactory implements IModuleUIFactory<Receiver> {

    @Override
    public BaseModuleSettingsDialog makeSettingsDialog(JFrame parent, Receiver module) {
        return new ReceiverModuleSettingsDialog(parent, module);
    }
}
