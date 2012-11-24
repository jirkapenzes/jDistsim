package jDistsim.core.simulation.event.description;

import jDistsim.utils.resource.EventTextResource;

/**
 * Author: Jirka Pénzeš
 * Date: 6.10.12
 * Time: 20:29
 */
public class CreateDescription implements IModuleDescription {

    @Override
    public String getTitle() {
        return EventTextResource.CREATE_EVENT_DESCRIPTION_TITLE;
    }

    @Override
    public String getDescription() {
        return EventTextResource.CREATE_EVENT_DESCRIPTION_TEXT;
    }
}
