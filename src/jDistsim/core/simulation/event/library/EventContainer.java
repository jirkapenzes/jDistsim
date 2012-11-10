package jDistsim.core.simulation.event.library;

import jDistsim.core.simulation.event.description.IEventDescription;
import jDistsim.ui.component.ComponentView;
import jDistsim.ui.component.IComponentView;
import jDistsim.utils.ioc.IObjectContainer;
import jDistsim.utils.ioc.ObjectContainer;

/**
 * Author: Jirka Pénzeš
 * Date: 10.11.12
 * Time: 17:48
 */
public class EventContainer {

    private IObjectContainer objectContainer;

    public EventContainer() {
        objectContainer = new ObjectContainer();
    }

    public EventContainer toView(ComponentView view) {
        objectContainer.bind(IComponentView.class, view);
        return this;
    }

    public EventContainer toDescription(IEventDescription createDescription) {
        objectContainer.bind(IEventDescription.class, createDescription);
        return this;
    }

    public IComponentView getComponentView() {
        return (IComponentView) objectContainer.get(IComponentView.class);
    }

    public IEventDescription getDescription() {
        return (IEventDescription) objectContainer.get(IEventDescription.class);
    }
}
