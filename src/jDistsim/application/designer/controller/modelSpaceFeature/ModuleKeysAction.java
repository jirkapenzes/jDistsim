package jDistsim.application.designer.controller.modelSpaceFeature;

import jDistsim.application.designer.controller.ModelSpaceController;
import jDistsim.application.designer.controller.ModuleConnector;
import jDistsim.core.modules.ModuleConnectedPointUI;
import jDistsim.core.modules.ModuleUI;
import jDistsim.utils.common.ModelSpaceListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Author: Jirka Pénzeš
 * Date: 2.2.13
 * Time: 23:15
 */
public class ModuleKeysAction extends ModelSpaceListener {

    @Override
    public void onAddedModule(final ModuleUI module, final ModelSpaceController modelSpaceController) {
        module.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_DELETE) {
                    for (ModuleConnectedPointUI connectedPointUI : module.getConnectedPoints()) {
                        HashMap<ModuleConnectedPointUI, ModuleConnector> dependencies = (HashMap<ModuleConnectedPointUI, ModuleConnector>) connectedPointUI.getDependencies().clone();
                        for (ModuleConnector moduleConnector : dependencies.values()) {
                            moduleConnector.getModulePointA().disconnect(moduleConnector.getModulePointB());
                            modelSpaceController.getView().getContentPane().remove(moduleConnector.getConnectorLine());
                        }
                    }
                    modelSpaceController.getModel().getModuleList().remove(module.getIdentifier());
                    modelSpaceController.getView().getContentPane().remove(module);
                    modelSpaceController.unselectedActiveModule();
                    modelSpaceController.getView().getContentPane().repaint();
                }
            }
        });
    }

    @Override
    public void onModelSelectedActiveModule(ModuleUI module, ModelSpaceController modelSpaceController) {
        module.requestFocus();
    }

    @Override
    public void onModelUnselectedActiveModule(ModuleUI module, ModelSpaceController modelSpaceController) {
        modelSpaceController.getView().getContentPane().requestFocus();
    }
}
