package jDistsim.core.simulation.event.description;

import jDistsim.core.simulation.event.library.create.ICreateDescription;
import jDistsim.utils.resource.EventTextResource;

/**
 * Author: Jirka Pénzeš
 * Date: 6.10.12
 * Time: 20:29
 */
public class CreateDescription implements ICreateDescription {

    @Override
    public String getTitle() {
        return EventTextResource.CREATE_EVENT_DESCRIPTION_TITLE;
    }

    @Override
    public String getDescription() {
        return EventTextResource.CREATE_EVENT_DESCRIPTION_TEXT;
    }
}