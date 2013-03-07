package jDistsim.ui.dialog;

import jDistsim.core.simulation.modules.lib.sender.Sender;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 7.3.13
 * Time: 12:09
 */
public class SenderModuleSettingsDialog extends BaseModuleSettingsDialog<Sender> {

    public SenderModuleSettingsDialog(JFrame parent, Sender module) {
        super(parent, module);
    }

    @Override
    protected void initializeUI() {

    }

    @Override
    protected void buildWindowBody() {
    }

    @Override
    protected boolean doLogic() {
        return true;
    }
}
