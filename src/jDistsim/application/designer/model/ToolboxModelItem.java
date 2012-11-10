package jDistsim.application.designer.model;

import jDistsim.core.simulation.event.description.IEventDescription;
import jDistsim.ui.component.IComponentView;

/**
 * Author: Jirka Pénzeš
 * Date: 9.11.12
 * Time: 19:26
 */
public class ToolboxModelItem {

    private String identifier;
    private IComponentView IComponentView;
    private IEventDescription eventDescription;

    public ToolboxModelItem(IComponentView IComponentView, IEventDescription eventDescription, String identifier) {
        this.IComponentView = IComponentView;
        this.eventDescription = eventDescription;
        this.identifier = identifier;
    }

    public IComponentView getComponentView() {
        return IComponentView;
    }

    public IEventDescription getEventDescription() {
        return eventDescription;
    }

    public String getIdentifier() {
        return identifier;
    }
}