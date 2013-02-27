package jDistsim.core.simulation.event.library;

import jDistsim.core.modules.ModuleConfiguration;
import jDistsim.core.modules.factory.CreateModuleFactory;
import jDistsim.core.modules.factory.DelayModuleFactory;
import jDistsim.core.modules.factory.DisposeModuleFactory;
import jDistsim.core.modules.factory.ui.CreateModuleUIFactory;
import jDistsim.core.modules.lib.CreateModule;
import jDistsim.core.modules.lib.DelayModule;
import jDistsim.core.modules.lib.DisposeModule;
import jDistsim.core.simulation.event.description.CreateDescription;
import jDistsim.core.simulation.event.description.DelayDescription;
import jDistsim.core.simulation.event.description.DisposeDescription;
import jDistsim.ui.module.ColorScheme;
import jDistsim.ui.module.moduleView.CreateModuleView;
import jDistsim.ui.module.moduleView.DelayModuleView;
import jDistsim.ui.module.moduleView.DisposeModuleView;
import jDistsim.utils.ioc.ObjectContainer;

import java.awt.*;
import java.util.*;
import java.util.List;


/**
 * Author: Jirka Pénzeš
 * Date: 10.11.12
 * Time: 16:15
 */
public class ModuleLibrary implements IModuleLibrary {

    private ObjectContainer<Class> container;
    private ColorScheme defaultColorScheme = new ColorScheme(new Color(67, 201, 224), new Color(70, 127, 137));

    public ModuleLibrary() {
        container = new ObjectContainer<>();
        configure();
    }

    private void configure() {
        container.bind(CreateModule.class, new ModuleContainer())
                .toView(new CreateModuleView())
                .toDescription(new CreateDescription())
                .toFactory(new CreateModuleFactory())
                .toUIFactory(new CreateModuleUIFactory())
                .withConfiguration(new ModuleConfiguration("create", new Dimension(80, 50), defaultColorScheme))
                .build();

        container.bind(DisposeModule.class, new ModuleContainer())
                .toView(new DisposeModuleView())
                .toDescription(new DisposeDescription())
                .toFactory(new DisposeModuleFactory())
                .toUIFactory(new CreateModuleUIFactory())
                .withConfiguration(new ModuleConfiguration("dispose", new Dimension(80, 50), defaultColorScheme))
                .build();

        container.bind(DelayModule.class, new ModuleContainer())
                .toView(new DelayModuleView())
                .toDescription(new DelayDescription())
                .toFactory(new DelayModuleFactory())
                .toUIFactory(new CreateModuleUIFactory())
                .withConfiguration(new ModuleConfiguration("delay", new Dimension(80, 50), defaultColorScheme))
                .build();
    }

    @Override
    public ModuleContainer get(Class moduleClass) {
        return container.get(moduleClass);
    }

    @Override
    public List<ModuleContainer> containersList() {
        List<ModuleContainer> containers = new ArrayList<>();
        for (Map.Entry<Class, Object> entry : container.entrySet()) {
            containers.add((ModuleContainer) entry.getValue());
        }
        return containers;
    }
}
