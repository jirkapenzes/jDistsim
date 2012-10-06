package jDistsim.core.simulation.event.description;

import jDistsim.core.simulation.event.library.dispose.IDisposeDescription;
import jDistsim.utils.resource.EventTextResource;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 17:50
 */
public class DisposeDescription implements IDisposeDescription {

    @Override
    public String getTitle() {
        return EventTextResource.DISPOSE_EVENT_DESCRIPTION_TITLE;
    }

    @Override
    public String getDescription() {
        return EventTextResource.DISPOSE_EVENT_DESCRIPTION_TEXT;
    }
}
