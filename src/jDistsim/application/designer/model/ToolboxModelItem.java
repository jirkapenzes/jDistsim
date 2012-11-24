package jDistsim.application.designer.model;

import jDistsim.core.simulation.event.description.IEventDescription;
import jDistsim.ui.component.IModuleView;

/**
 * Author: Jirka Pénzeš
 * Date: 9.11.12
 * Time: 19:26
 */
public class ToolboxModelItem {

    private String identifier;
    private IModuleView IModuleView;
    private IEventDescription eventDescription;

    public ToolboxModelItem(IModuleView IModuleView, IEventDescription eventDescription, String identifier) {
        this.IModuleView = IModuleView;
        this.eventDescription = eventDescription;
        this.identifier = identifier;
    }

    public IModuleView getComponentView() {
        return IModuleView;
    }

    public IEventDescription getEventDescription() {
        return eventDescription;
    }

    public String getIdentifier() {
        return identifier;
    }
}