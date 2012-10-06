package jDistsim.core.module;

import jDistsim.core.simulation.event.ui.UIEventPreview;

/**
 * Author: Jirka Pénzeš
 * Date: 6.10.12
 * Time: 21:15
 */
public class EventToolbarModule implements IEventToolbarModule {

    private UIEventPreview eventPreview;

    public EventToolbarModule(UIEventPreview eventPreview) {
        this.eventPreview = eventPreview;
    }

    @Override
    public UIEventPreview getEventPreview() {
        return eventPreview;
    }
}
