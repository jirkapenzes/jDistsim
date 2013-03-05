package jDistsim.ui.dialog;

import jDistsim.core.simulation.modules.Module;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 24.2.13
 * Time: 16:43
 */
public abstract class BaseModuleSettingsDialog<TModule extends Module> extends BaseDialog {

    protected TModule module;

    public BaseModuleSettingsDialog(JFrame parent, TModule module) {
        super(parent, module.getIdentifier());
        setTitle("Module settings dialog");
        this.module = module;
    }

    public BaseModuleSettingsDialog(JFrame parent, IDialogComponentFactory componentFactory, TModule module) {
        super(parent, componentFactory, module.getIdentifier());
        setTitle("Module settings dialog");
        this.module = module;
    }

    @Override
    protected boolean okButtonLogic() {
        boolean logicResult = doLogic();
        if (logicResult) {
            module.rebuild();
            module.notifyObservers("properties");
        }
        return logicResult;
    }

    protected abstract boolean doLogic();
}
