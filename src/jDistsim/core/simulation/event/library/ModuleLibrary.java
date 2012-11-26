package jDistsim.core.simulation.event.library;

import jDistsim.core.modules.ModuleConfiguration;
import jDistsim.core.modules.factory.CreateModuleFactory;
import jDistsim.core.simulation.event.description.CreateDescription;
import jDistsim.core.simulation.event.description.DelayDescription;
import jDistsim.core.simulation.event.description.DisposeDescription;
import jDistsim.ui.module.moduleView.CreateModuleView;
import jDistsim.ui.module.moduleView.DelayModuleView;
import jDistsim.ui.module.moduleView.DisposeModuleView;
import jDistsim.utils.ioc.ObjectContainer;

import java.awt.*;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Author: Jirka Pénzeš
 * Date: 10.11.12
 * Time: 16:15
 */
public class ModuleLibrary implements IModuleLibrary {

    private ObjectContainer<String> container;

    public ModuleLibrary() {
        container = new ObjectContainer<>();
        configure();
    }

    private void configure() {
        container.bind("create", new ModuleContainer())
                .toView(new CreateModuleView())
                .toDescription(new CreateDescription())
                .toFactory(new CreateModuleFactory(new ModuleConfiguration("create", new Dimension(80, 50))));
    }

    public Set<Map.Entry<String, ModuleContainer>> entrySet() {
        Set<Map.Entry<String, ModuleContainer>> entries = new HashSet<>();
        for (Map.Entry<String, Object> entry : container.entrySet()) {
            entries.add(new AbstractMap.SimpleEntry<>(entry.getKey(), (ModuleContainer) entry.getValue()));
        }
        return entries;
    }
}
