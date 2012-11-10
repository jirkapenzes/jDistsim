package jDistsim.core.simulation.event.description;

import jDistsim.utils.resource.EventTextResource;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 17:50
 */
public class DisposeDescription implements IEventDescription {

    @Override
    public String getTitle() {
        return EventTextResource.DISPOSE_EVENT_DESCRIPTION_TITLE;
    }

    @Override
    public String getDescription() {
        return EventTextResource.DISPOSE_EVENT_DESCRIPTION_TEXT;
    }
}
