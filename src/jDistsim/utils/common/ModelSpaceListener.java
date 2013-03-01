package jDistsim.utils.common;

import jDistsim.application.designer.controller.ModelSpaceController;
import jDistsim.core.simulation.modules.ui.ModuleUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 27.12.12
 * Time: 23:30
 */
public abstract class ModelSpaceListener {

    public void modelSpaceMouseClicked(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
    }

    public void modelSpaceMousePressed(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
    }

    public void modelSpaceMouseReleased(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
    }

    public void modelSpaceMouseEntered(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
    }

    public void modelSpaceMouseExited(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
    }

    public void modelSpaceMouseWheelMoved(MouseWheelEvent mouseWheelEvent, ModelSpaceController modelSpaceController) {
    }

    public void modelSpaceMouseDragged(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
    }

    public void modelSpaceMouseMoved(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
    }

    public void modelSpaceMotionMouseDragged(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
    }

    public void modelSpaceMotionMouseMoved(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
    }

    public void moduleClicked(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
    }

    public void moduleMousePressed(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
    }

    public void moduleMouseReleased(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
    }

    public void moduleMouseEntered(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
    }

    public void moduleMouseExited(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
    }

    public void moduleMouseWheelMoved(MouseWheelEvent mouseWheelEvent, ModelSpaceController modelSpaceController) {
    }

    public void moduleMouseDragged(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
    }

    public void moduleMouseMoved(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
    }

    public void moduleMotionMouseDragged(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
    }

    public void moduleMotionMouseMoved(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
    }

    public ModuleUI getModuleUIFromMouseEvent(MouseEvent mouseEvent) {
        return (ModuleUI) mouseEvent.getSource();
    }

    public void onModelSelectedActiveModule(ModuleUI module, ModelSpaceController modelSpaceController) {
    }

    public void onModelUnselectedActiveModule(ModuleUI module, ModelSpaceController modelSpaceController) {
    }

    public void onAddedModule(ModuleUI module, ModelSpaceController modelSpaceController) {
    }
}
