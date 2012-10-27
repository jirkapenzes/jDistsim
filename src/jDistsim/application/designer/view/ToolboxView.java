package jDistsim.application.designer.view;

import jDistsim.core.model.ToolboxModel;
import jDistsim.core.module.EventToolbarModule;
import jDistsim.core.simulation.event.description.CreateEventDescription;
import jDistsim.core.simulation.event.description.DisposeDescription;
import jDistsim.core.simulation.event.ui.preview.CreateUIEventPreview;
import jDistsim.core.simulation.event.ui.preview.DisposeUIEventPreview;
import jDistsim.ui.panel.toolbox.ToolboxPanel;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractView;

/**
 * Author: Jirka Pénzeš
 * Date: 27.10.12
 * Time: 13:09
 */
public class ToolboxView extends AbstractView<ToolboxPanel> {

    public ToolboxView(AbstractFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    protected ToolboxPanel layout() {
        ToolboxModel toolboxModel = new ToolboxModel();
        toolboxModel
                .addEventToolbarModule(new EventToolbarModule(new CreateUIEventPreview(new CreateEventDescription())))
                .addEventToolbarModule(new EventToolbarModule(new DisposeUIEventPreview(new DisposeDescription())));

        return new ToolboxPanel(toolboxModel);
    }
}
