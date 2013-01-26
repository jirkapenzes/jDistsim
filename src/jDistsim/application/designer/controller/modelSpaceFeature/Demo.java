package jDistsim.application.designer.controller.modelSpaceFeature;

import jDistsim.application.designer.controller.ModelSpaceController;
import jDistsim.core.modules.ModuleConnectedPointUI;
import jDistsim.core.modules.ModuleUI;
import jDistsim.utils.common.ModelSpaceListener;
import jDistsim.utils.ui.SwingUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 29.12.12
 * Time: 20:14
 */
public class Demo extends ModelSpaceListener {

    private final int pointSize = 12;
    private class ConnectPointHelper extends JComponent {

        private ModuleConnectedPointUI moduleConnectedPointUI;

        private ConnectPointHelper(ModuleConnectedPointUI moduleConnectedPointUI, int size, Point location) {
            this.moduleConnectedPointUI = moduleConnectedPointUI;
            setLocation(location);
            setSize(size, size);
        }

        public ModuleConnectedPointUI getModuleConnectedPointUI() {
            return moduleConnectedPointUI;
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(getSize().width, getSize().height);
        }


        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            graphics2D.setColor(new Color(188, 246, 47));
            graphics2D.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            graphics2D.setColor(new Color(62, 109, 0));
            graphics2D.fillRect(2, 2, getWidth() - 5, getHeight() - 5);
        }
    }

    private List<ConnectPointHelper> connectPointHelperList;
    private ModuleUI currentActiveModule;

    public Demo() {
        connectPointHelperList = new ArrayList<>();
        currentActiveModule = null;
    }

    @Override
    public void onModelSelectedActiveModule(ModuleUI module, ModelSpaceController modelSpaceController) {
        removeAlCurrentHelperPoints(modelSpaceController);
        showHelperPoints(module, modelSpaceController);
        showPossibleDependecies(modelSpaceController);
    }

    @Override
    public void onModelUnselectedActiveModule(ModuleUI module, ModelSpaceController modelSpaceController) {
        removeAlCurrentHelperPoints(modelSpaceController);
    }

    @Override
    public void modelSpaceMotionMouseMoved(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
        if (!showHelperPoints(mouseEvent))
            removeAlCurrentHelperPoints(modelSpaceController);
    }

    private boolean showHelperPoints(MouseEvent mouseEvent) {
        if (currentActiveModule == null || currentActiveModule.contains(mouseEvent.getPoint()))
            return true;

        if (currentActiveModule.isActive())
            return true;

        for (ConnectPointHelper pointHelper : connectPointHelperList) {
            if (pointHelper.getBounds().contains(mouseEvent.getPoint())) {
                return true;
            }
        }
        return false;
    }

    // Pokud z modulem tahnu - pak body odstranim
    @Override
    public void moduleMotionMouseDragged(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
        removeAlCurrentHelperPoints(modelSpaceController);
    }

    // Pokud najedu mysi na modul -> zobrazi se pripojny body (pouze pokud neni nejaky modul aktivni)
    @Override
    public void moduleMouseEntered(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
        // pokud je modul aktivni -> body jsou videt trvale a neni potreba je zobrazovat znovu
        if (currentActiveModule != null && currentActiveModule.isActive())
            return;

        ModuleUI moduleUI = getModuleUIFromMouseEvent(mouseEvent);
        removeAlCurrentHelperPoints(modelSpaceController);
        showHelperPoints(moduleUI, modelSpaceController);
        currentActiveModule = moduleUI;
    }

    // Pokud jsem tahl mysi a pak pustil tlacitko - body se musi opet objevit
    @Override
    public void moduleMouseReleased(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
        if (currentActiveModule != null && !currentActiveModule.isActive())
            return;

        showHelperPoints((ModuleUI) mouseEvent.getSource(), modelSpaceController);
        showPossibleDependecies(modelSpaceController);
    }

    private void showHelperPoints(ModuleUI moduleUI, ModelSpaceController modelSpaceController) {
        for (ModuleConnectedPointUI connectedPointUI : moduleUI.getConnectedPoints()) {
            addConnectedPointHelper(moduleUI, modelSpaceController, connectedPointUI);
        }
    }

    private void showPossibleDependecies(ModelSpaceController modelSpaceController) {
        for (ModuleUI moduleUI : modelSpaceController.getModuleList().values()) {
            if (moduleUI.getIdentifier().equals(currentActiveModule.getIdentifier()))
                continue;

            for (ModuleConnectedPointUI connectedPointUI : moduleUI.getConnectedPoints()) {
                if (connectedPointUI.getType() == ModuleConnectedPointUI.Type.INPUT) {
                    addConnectedPointHelper(moduleUI, modelSpaceController, connectedPointUI);
                }
            }
        }
    }

    private void removeAlCurrentHelperPoints(ModelSpaceController controller) {
        for (ConnectPointHelper pointHelper : connectPointHelperList) {
            controller.getView().getContentPane().remove(pointHelper);
        }
    }

    private void addConnectedPointHelper(ModuleUI moduleUI, ModelSpaceController modelSpaceController, ModuleConnectedPointUI connectedPointUI) {
        ConnectPointHelper connectPointHelper = new ConnectPointHelper(connectedPointUI, pointSize, ModelSpaceHelper.calculatePointPosition(pointSize, connectedPointUI, moduleUI));
        int moduleIndex = SwingUtil.getComponentIndex(moduleUI);
        modelSpaceController.getView().getContentPane().add(connectPointHelper, moduleIndex);
        connectPointHelperList.add(connectPointHelper);
    }
}
