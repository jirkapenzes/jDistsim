package jDistsim.application.designer.controller.modelSpaceFeature;

import jDistsim.application.designer.controller.ModelSpaceController;
import jDistsim.core.modules.ModuleUI;
import jDistsim.utils.common.ModelSpaceListener;
import jDistsim.utils.logging.Logger;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 27.12.12
 * Time: 23:39
 */
public class ModuleMovingAction extends ModelSpaceListener {

    private Point mousePositionDown;
    private boolean isDragged = false;

    @Override
    public void moduleMousePressed(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
        switch (mouseEvent.getButton()) {
            case MouseEvent.BUTTON1:
                mousePositionDown = new Point(mouseEvent.getX(), mouseEvent.getY());
                break;
        }
    }

    @Override
    public void moduleMotionMouseDragged(MouseEvent mouseEvent, ModelSpaceController controller) {
        isDragged = true;
        ModuleUI moduleUI = getModuleUIFromMouseEvent(mouseEvent);
        controller.unselectedActiveModule();

        Point position = moduleUI.getLocation();
        position.translate(mouseEvent.getX() - mousePositionDown.x, mouseEvent.getY() - mousePositionDown.y);
        moduleUI.setLocation(position);
    }

    @Override
    public void moduleMouseReleased(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
        if (isDragged) {
            ModuleUI moduleUI = getModuleUIFromMouseEvent(mouseEvent);
            modelSpaceController.selectedActiveModule(moduleUI);
            isDragged = false;
            Logger.log("calculate new position");
        }
    }
}
