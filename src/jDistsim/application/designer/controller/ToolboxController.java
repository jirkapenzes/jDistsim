package jDistsim.application.designer.controller;

import jDistsim.ServiceLocator;
import jDistsim.application.designer.model.ToolboxModel;
import jDistsim.application.designer.model.ToolboxModelItem;
import jDistsim.application.designer.view.ToolboxView;
import jDistsim.core.simulation.modules.EmptyDescription;
import jDistsim.core.simulation.modules.IModuleLibrary;
import jDistsim.core.simulation.modules.ModuleContainer;
import jDistsim.ui.panel.toolbox.ToolboxListener;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.pattern.mvc.AbstractController;
import jDistsim.utils.pattern.mvc.AbstractFrame;

/**
 * Author: Jirka Pénzeš
 * Date: 9.11.12
 * Time: 18:48
 */
public class ToolboxController extends AbstractController<ToolboxModel> implements ToolboxListener {

    private ToolboxView view;

    public ToolboxController(AbstractFrame mainFrame, ToolboxModel model) {
        super(mainFrame, model);
        view = getMainFrame().getView(ToolboxView.class);
        view.getContentPane().setModel(buildToolboxModel());
        view.addToolboxListener(this);
    }

    private ToolboxModel buildToolboxModel() {
        IModuleLibrary moduleLibrary = ServiceLocator.getInstance().get(IModuleLibrary.class);
        ToolboxModel toolboxModel = getMainFrame().getModel(ToolboxModel.class);
        for (ModuleContainer container : moduleLibrary.containersList()) {
            toolboxModel.add(container.getBaseName(), new ToolboxModelItem(container.getView(), container.getDescription(), container.getFactory(), container.getBaseName()));
        }
        return toolboxModel;
    }

    @Override
    public void componentSelected(ToolboxModelItem modelItem) {
        ToolboxView view = getMainFrame().getView(ToolboxView.class);
        view.getContentPane().setDescriptionText(modelItem.getModuleDescription());
    }

    @Override
    public void componentUnselected() {
        ToolboxView view = getMainFrame().getView(ToolboxView.class);
        view.getContentPane().resetButtonsState();
        view.getContentPane().setDescriptionText(new EmptyDescription());
    }

    @Override
    public void componentPressed(ToolboxModelItem toolboxModelItem) {
        Logger.log("Pressed");
    }

    @Override
    public void componentReleased(ToolboxModelItem toolboxModelItem) {
        Logger.log("Released");
    }
}
