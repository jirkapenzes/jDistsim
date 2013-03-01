package jDistsim.core.simulation.modules.lib.delay;

import jDistsim.core.simulation.modules.IModuleDescription;
import jDistsim.utils.resource.EventTextResource;

/**
 * Author: Jirka Pénzeš
 * Date: 10.11.12
 * Time: 20:55
 */
public class DelayDescription implements IModuleDescription {
    @Override
    public String getTitle() {
        return EventTextResource.DELAY_EVENT_DESCRIPTION_TITLE;
    }

    @Override
    public String getDescription() {
        return EventTextResource.DELAY_EVENT_DESCRIPTION_TEXT;
    }
}
