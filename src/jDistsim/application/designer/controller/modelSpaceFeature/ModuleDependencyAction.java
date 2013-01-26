package jDistsim.application.designer.controller.modelSpaceFeature;

import jDistsim.utils.common.ModelSpaceListener;

/**
 * Author: Jirka Pénzeš
 * Date: 28.12.12
 * Time: 0:03
 */
public class ModuleDependencyAction extends ModelSpaceListener {
//
//    private List<ModuleConnectedPointUI_DELETE> currentShowPoints;
//
//    public ModuleDependencyAction() {
//        currentShowPoints = new ArrayList<>();
//    }
//
//    @Override
//    public void moduleMouseReleased(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
//        ModuleUI moduleUI = (ModuleUI) mouseEvent.getSource();
//        for (ModuleConnectedPointUI_DELETE connectedPointUI : currentShowPoints) {
//            connectedPointUI.setLocation(ModelSpaceHelper.calculatePointPosition(connectedPointUI, moduleUI));
//            connectedPointUI.setVisible(true);
//        }
//        modelSpaceController.getView().getContentPane().repaint();
//    }
//
//    @Override
//    public void moduleMouseEntered(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
//        if (modelSpaceController.getModel().isRelations()) {
//            ModuleUI moduleUI = (ModuleUI) mouseEvent.getSource();
//            ModelSpaceView view = modelSpaceController.getView();
//
//            for (ModuleConnectedPointUI_DELETE connectedPointUI : moduleUI.getInputPointsUI()) {
//                connectedPointUI.setLocation(ModelSpaceHelper.calculatePointPosition(connectedPointUI, moduleUI));
//                view.getContentPane().add(connectedPointUI, 0);
//            }
//            currentShowPoints.addAll(moduleUI.getInputPointsUI());
//            for (ModuleConnectedPointUI_DELETE connectedPointUI : moduleUI.getOutputPointsUI()) {
//                connectedPointUI.setLocation(ModelSpaceHelper.calculatePointPosition(connectedPointUI, moduleUI));
//                view.getContentPane().add(connectedPointUI, 0);
//            }
//            currentShowPoints.addAll(moduleUI.getOutputPointsUI());
//            view.getContentPane().repaint();
//        }
//    }
//
//    @Override
//    public void moduleMouseExited(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
//        for (ModuleConnectedPointUI_DELETE connectedPointUI : currentShowPoints) {
//            modelSpaceController.getView().getContentPane().remove(connectedPointUI);
//        }
//        modelSpaceController.getView().getContentPane().repaint();
//    }
//
//    @Override
//    public void moduleMotionMouseDragged(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
//        for (ModuleConnectedPointUI_DELETE connectedPointUI : currentShowPoints)
//            connectedPointUI.setVisible(false);
//    }
}
