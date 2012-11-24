package jDistsim.core.simulation.event.library;

import jDistsim.core.simulation.event.description.CreateDescription;
import jDistsim.core.simulation.event.description.DelayDescription;
import jDistsim.core.simulation.event.description.DisposeDescription;
import jDistsim.ui.module.moduleView.CreateModuleView;
import jDistsim.ui.module.moduleView.DelayModuleView;
import jDistsim.ui.module.moduleView.DisposeModuleView;
import jDistsim.utils.ioc.ObjectContainer;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Author: Jirka Pénzeš
 * Date: 10.11.12
 * Time: 16:15
 */
public class EventLibrary implements IEventLibrary {

    private ObjectContainer<String> container;

    public EventLibrary() {
        container = new ObjectContainer<>();
        configure();
    }

    private void configure() {
        container.bind("create", new EventContainer())
                .toView(new CreateModuleView())
                .toDescription(new CreateDescription());
        container.bind("dispose", new EventContainer())
                .toView(new DisposeModuleView())
                .toDescription(new DisposeDescription());
        container.bind("delay", new EventContainer())
                .toView(new DelayModuleView())
                .toDescription(new DelayDescription());
    }

    public Set<Map.Entry<String, EventContainer>> entrySet() {
        Set<Map.Entry<String, EventContainer>> entries = new HashSet<>();
        for (Map.Entry<String, Object> entry : container.entrySet()) {
            entries.add(new AbstractMap.SimpleEntry<>(entry.getKey(), (EventContainer) entry.getValue()));
        }
        return entries;
    }
}
