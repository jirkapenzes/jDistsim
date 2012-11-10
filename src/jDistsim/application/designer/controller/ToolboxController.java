package jDistsim.application.designer.controller;

import jDistsim.application.designer.model.ToolboxModel;
import jDistsim.application.designer.model.ToolboxModelItem;
import jDistsim.application.designer.view.ToolboxView;
import jDistsim.core.simulation.event.description.CreateEventDescription;
import jDistsim.core.simulation.event.description.DisposeDescription;
import jDistsim.core.simulation.event.description.EmptyDescription;
import jDistsim.ui.component.toolboxView.CreateComponentView;
import jDistsim.ui.component.toolboxView.DisposeComponentView;
import jDistsim.ui.panel.toolbox.ToolboxListener;
import jDistsim.utils.pattern.mvc.AbstractController;
import jDistsim.utils.pattern.mvc.AbstractFrame;

/**
 * Author: Jirka Pénzeš
 * Date: 9.11.12
 * Time: 18:48
 */
public class ToolboxController extends AbstractController<ToolboxModel> implements ToolboxListener {

    public ToolboxController(AbstractFrame mainFrame, ToolboxModel model) {
        super(mainFrame, model);

        ToolboxModel toolboxModel = new ToolboxModel();
        toolboxModel.addToolboxModelItem(new ToolboxModelItem(new CreateComponentView(), new CreateEventDescription(), "create"));
        toolboxModel.addToolboxModelItem(new ToolboxModelItem(new DisposeComponentView(), new DisposeDescription(), "dispose"));
        ToolboxView view = getMainFrame().getView(ToolboxView.class);
        view.getContentPane().setModel(toolboxModel);
        view.addMouseListener(this);
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
