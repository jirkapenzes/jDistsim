package jDistsim.application.designer.controller;

import jDistsim.ServiceLocator;
import jDistsim.application.designer.model.ToolboxModel;
import jDistsim.application.designer.model.ToolboxModelItem;
import jDistsim.application.designer.view.ToolboxView;
import jDistsim.core.simulation.event.description.EmptyDescription;
import jDistsim.core.simulation.event.library.EventContainer;
import jDistsim.core.simulation.event.library.IEventLibrary;
import jDistsim.ui.panel.toolbox.ToolboxListener;
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
        view.addMouseListener(this);
    }

    private ToolboxModel buildToolboxModel() {
        IEventLibrary eventLibrary = ServiceLocator.getInstance().get(IEventLibrary.class);

        ToolboxModel toolboxModel = new ToolboxModel();
        for (Map.Entry<String, EventContainer> entry : eventLibrary.entrySet()) {
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
}
