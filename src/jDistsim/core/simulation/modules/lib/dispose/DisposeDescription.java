package jDistsim.core.simulation.modules.lib.dispose;

import jDistsim.core.simulation.modules.IModuleDescription;
import jDistsim.utils.resource.EventTextResource;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 17:50
 */
public class DisposeDescription implements IModuleDescription {

    @Override
    public String getTitle() {
        return EventTextResource.DISPOSE_EVENT_DESCRIPTION_TITLE;
    }

    @Override
    public String getDescription() {
        return EventTextResource.DISPOSE_EVENT_DESCRIPTION_TEXT;
    }
}
