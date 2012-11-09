package jDistsim.application.designer.controller;

import jDistsim.application.designer.model.ToolboxModel;
import jDistsim.application.designer.model.ToolboxModelItem;
import jDistsim.application.designer.view.ToolboxView;
import jDistsim.core.simulation.event.description.CreateEventDescription;
import jDistsim.ui.component.toolboxView.CreateComponentView;
import jDistsim.utils.pattern.mvc.AbstractController;
import jDistsim.utils.pattern.mvc.AbstractFrame;

/**
 * Author: Jirka Pénzeš
 * Date: 9.11.12
 * Time: 18:48
 */
public class ToolboxController extends AbstractController<ToolboxModel> {

    public ToolboxController(AbstractFrame mainFrame, ToolboxModel model) {
        super(mainFrame, model);

        ToolboxModel toolboxModel = new ToolboxModel();
        toolboxModel.addToolboxModelItem(new ToolboxModelItem(new CreateComponentView(), new CreateEventDescription()));
        toolboxModel.addToolboxModelItem(new ToolboxModelItem(new CreateComponentView(), new CreateEventDescription()));
        ToolboxView view = getMainFrame().getView(ToolboxView.class);
        view.getContentPane().setModel(toolboxModel);
    }
}
