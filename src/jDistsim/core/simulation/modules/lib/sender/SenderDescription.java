package jDistsim.core.simulation.modules.lib.sender;

import jDistsim.core.simulation.modules.IModuleDescription;
import jDistsim.utils.resource.EventTextResource;

/**
 * Author: Jirka Pénzeš
 * Date: 1.3.13
 * Time: 20:27
 */
public class SenderDescription implements IModuleDescription {

    @Override
    public String getTitle() {
        return EventTextResource.SENDER_EVENT_DESCRIPTION_TITLE;
    }

    @Override
    public String getDescription() {
        return EventTextResource.SENDER_EVENT_DESCRIPTION_TEXT;
    }
}
