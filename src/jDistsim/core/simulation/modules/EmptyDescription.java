package jDistsim.core.simulation.modules;

import jDistsim.core.simulation.modules.IModuleDescription;
import jDistsim.utils.resource.EventTextResource;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 18:13
 */
public class EmptyDescription implements IModuleDescription {

    @Override
    public String getTitle() {
        return EventTextResource.EMPTY_EVENT_DESCRIPTION_TITLE;
    }

    @Override
    public String getDescription() {
        return EventTextResource.EMPTY_EVENT_DESCRIPTION_TEXT;
    }
}
