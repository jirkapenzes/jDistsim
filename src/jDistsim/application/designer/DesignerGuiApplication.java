package jDistsim.application.designer;

import jDistsim.ServiceLocator;
import jDistsim.application.designer.common.ComponentFactory;
import jDistsim.core.simulation.modules.IModuleLibrary;
import jDistsim.main.IGuiApplication;
import jDistsim.utils.logging.Logger;

/**
 * Author: Jirka Pénzeš
 * Date: 18.9.12
 * Time: 23:02
 */
public class DesignerGuiApplication implements IGuiApplication {

    @Override
    public void Start() {
        Logger.log("Create service locator");
        ServiceLocator.create();

        Logger.log("Prepare GUI jDistsim designer");
        registerDependencies();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame(new ComponentFactory()).show();
            }
        });
    }

    private void registerDependencies() {
        Logger.log("Register dependencies");
        ServiceLocator.getInstance().bind(IModuleLibrary.class, new ModuleLibrary());
    }
}
