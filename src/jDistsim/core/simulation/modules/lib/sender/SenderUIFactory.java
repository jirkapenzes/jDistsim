package jDistsim.core.simulation.modules.lib.sender;

import jDistsim.core.simulation.modules.IModuleUIFactory;
import jDistsim.ui.dialog.BaseModuleSettingsDialog;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 1.3.13
 * Time: 20:49
 */
public class SenderUIFactory implements IModuleUIFactory<Sender> {
    @Override
    public BaseModuleSettingsDialog makeSettingsDialog(JFrame parent, Sender module) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
