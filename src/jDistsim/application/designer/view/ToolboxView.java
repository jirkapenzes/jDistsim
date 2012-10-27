package jDistsim.application.designer.view;

import jDistsim.core.model.EventToolbarModel;
import jDistsim.core.module.EventToolbarModule;
import jDistsim.core.simulation.event.description.CreateEventDescription;
import jDistsim.core.simulation.event.description.DisposeDescription;
import jDistsim.core.simulation.event.ui.preview.CreateUIEventPreview;
import jDistsim.core.simulation.event.ui.preview.DisposeUIEventPreview;
import jDistsim.ui.panel.EventToolbar;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractView;

/**
 * Author: Jirka Pénzeš
 * Date: 27.10.12
 * Time: 13:09
 */
public class ToolboxView extends AbstractView<EventToolbar> {

    public ToolboxView(AbstractFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    protected EventToolbar layout() {
        EventToolbarModel eventToolbarModel = new EventToolbarModel();
        eventToolbarModel
                .addEventToolbarModule(new EventToolbarModule(new CreateUIEventPreview(new CreateEventDescription())))
                .addEventToolbarModule(new EventToolbarModule(new DisposeUIEventPreview(new DisposeDescription())));

        return new EventToolbar(eventToolbarModel);
    }
}
