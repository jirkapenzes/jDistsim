package jDistsim.application.designer.model;

import jDistsim.core.simulation.event.description.IEventDescription;
import jDistsim.ui.component.ComponentView;

/**
 * Author: Jirka Pénzeš
 * Date: 9.11.12
 * Time: 19:26
 */
public class ToolboxModelItem {

    private String identifier;
    private ComponentView componentView;
    private IEventDescription eventDescription;

    public ToolboxModelItem(ComponentView componentView, IEventDescription eventDescription, String identifier) {
        this.componentView = componentView;
        this.eventDescription = eventDescription;
        this.identifier = identifier;
    }

    public ComponentView getComponentView() {
        return componentView;
    }

    public IEventDescription getEventDescription() {
        return eventDescription;
    }

    public String getIdentifier() {
        return identifier;
    }
}