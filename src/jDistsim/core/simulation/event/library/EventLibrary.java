package jDistsim.core.simulation.event.library;

import jDistsim.core.simulation.event.description.CreateDescription;
import jDistsim.core.simulation.event.description.DisposeDescription;
import jDistsim.ui.component.toolboxView.CreateComponentView;
import jDistsim.ui.component.toolboxView.DisposeComponentView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author: Jirka Pénzeš
 * Date: 10.11.12
 * Time: 16:15
 */
public class EventLibrary implements IEventLibrary {

    private HashMap<String, EventContainer> containerHashMap;

    public EventLibrary() {
        containerHashMap = new HashMap<>();
        configure();
    }

    private void configure() {
        containerHashMap.put("create", new EventContainer())
                .toView(new CreateComponentView())
                .toDescription(new CreateDescription());
        containerHashMap.put("dispose", new EventContainer())
                .toView(new DisposeComponentView())
                .toDescription(new DisposeDescription());
    }

    public Set<Map.Entry<String, EventContainer>> entrySet() {
        return containerHashMap.entrySet();
    }
}
