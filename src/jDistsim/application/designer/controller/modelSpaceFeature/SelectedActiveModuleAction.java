package jDistsim.application.designer.controller.modelSpaceFeature;

import jDistsim.application.designer.controller.ModelSpaceController;
import jDistsim.core.modules.ModuleUI;
import jDistsim.utils.common.ModelSpaceListener;

import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 28.12.12
 * Time: 0:42
 */
public class SelectedActiveModuleAction extends ModelSpaceListener {

    @Override
    public void modelSpaceMouseClicked(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
        modelSpaceController.unselectedActiveModule();
    }

    @Override
    public void moduleClicked(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
        ModuleUI moduleUI = (ModuleUI) mouseEvent.getSource();
        if (moduleUI == modelSpaceController.getModel().getCurrentActiveModule() && moduleUI.getActive()) {
            modelSpaceController.unselectedActiveModule();
            return;
        }

        modelSpaceController.unselectedActiveModule();
        modelSpaceController.selectedActiveModule(moduleUI);
        modelSpaceController.getModel().setCurrentActiveModule(modelSpaceController.getModuleList().get(moduleUI.getIdentifier()));
    }
}
