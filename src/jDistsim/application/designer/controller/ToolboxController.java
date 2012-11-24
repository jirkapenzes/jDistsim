package jDistsim.application.designer.controller;

import jDistsim.ServiceLocator;
import jDistsim.application.designer.model.ToolboxModel;
import jDistsim.application.designer.model.ToolboxModelItem;
import jDistsim.application.designer.view.ToolboxView;
import jDistsim.core.simulation.event.description.EmptyDescription;
import jDistsim.core.simulation.event.library.EventContainer;
import jDistsim.core.simulation.event.library.IModuleLibrary;
import jDistsim.ui.panel.toolbox.ToolboxListener;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.pattern.mvc.AbstractController;
import jDistsim.utils.pattern.mvc.AbstractFrame;

import java.util.Map;

/**
 * Author: Jirka Pénzeš
 * Date: 9.11.12
 * Time: 18:48
 */
public class ToolboxController extends AbstractController<ToolboxModel> implements ToolboxListener {

    public ToolboxController(AbstractFrame mainFrame, ToolboxModel model) {
        super(mainFrame, model);

        buildToolboxModel();

        ToolboxView view = getMainFrame().getView(ToolboxView.class);
        view.getContentPane().setModel(buildToolboxModel());
        view.addToolboxListener(this);
    }

    private ToolboxModel buildToolboxModel() {
        IModuleLibrary moduleLibrary = ServiceLocator.getInstance().get(IModuleLibrary.class);

        ToolboxModel toolboxModel = new ToolboxModel();
        for (Map.Entry<String, EventContainer> entry : moduleLibrary.entrySet()) {
            EventContainer eventContainer = entry.getValue();
            ToolboxModelItem item = new ToolboxModelItem(eventContainer.getComponentView(), eventContainer.getDescription(), entry.getKey());
            toolboxModel.addToolboxModelItem(item);
        }
        return toolboxModel;
    }

    @Override
    public void componentSelected(ToolboxModelItem modelItem) {
        ToolboxView view = getMainFrame().getView(ToolboxView.class);
        view.getContentPane().setDescriptionText(modelItem.getEventDescription());
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
