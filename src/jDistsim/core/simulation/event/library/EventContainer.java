package jDistsim.core.simulation.event.library;

import jDistsim.core.simulation.event.description.IEventDescription;
import jDistsim.ui.component.IModuleView;
import jDistsim.ui.component.ModuleView;
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

    public EventContainer toView(ModuleView view) {
        objectContainer.bind(IModuleView.class, view);
        return this;
    }

    public EventContainer toDescription(IEventDescription createDescription) {
        objectContainer.bind(IEventDescription.class, createDescription);
        return this;
    }

    public IModuleView getComponentView() {
        return (IModuleView) objectContainer.get(IModuleView.class);
    }

    public IEventDescription getDescription() {
        return (IEventDescription) objectContainer.get(IEventDescription.class);
    }
}
