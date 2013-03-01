package jDistsim.application.designer;

import jDistsim.application.designer.common.UIConfiguration;
import jDistsim.core.simulation.modules.IModuleLibrary;
import jDistsim.core.simulation.modules.ModuleContainer;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.core.simulation.modules.lib.create.*;
import jDistsim.core.simulation.modules.lib.delay.*;
import jDistsim.core.simulation.modules.lib.dispose.DisposeDescription;
import jDistsim.core.simulation.modules.lib.dispose.DisposeModule;
import jDistsim.core.simulation.modules.lib.dispose.DisposeModuleFactory;
import jDistsim.core.simulation.modules.lib.dispose.DisposeModuleView;
import jDistsim.utils.ioc.ObjectContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Author: Jirka Pénzeš
 * Date: 10.11.12
 * Time: 16:15
 */
public class ModuleLibrary implements IModuleLibrary {

    private ObjectContainer<Class> container;

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
                .withConfiguration(new ModuleConfiguration("create", UIConfiguration.getInstance().getColorSchemeForBasicModule()))
                .build();

        container.bind(DisposeModule.class, new ModuleContainer())
                .toView(new DisposeModuleView())
                .toDescription(new DisposeDescription())
                .toFactory(new DisposeModuleFactory())
                .toUIFactory(new CreateModuleUIFactory())
                .withConfiguration(new ModuleConfiguration("dispose", UIConfiguration.getInstance().getColorSchemeForBasicModule()))
                .build();

        container.bind(DelayModule.class, new ModuleContainer())
                .toView(new DelayModuleView())
                .toDescription(new DelayDescription())
                .toFactory(new DelayModuleFactory())
                .toUIFactory(new DelayModuleUIFactory())
                .withConfiguration(new ModuleConfiguration("delay", UIConfiguration.getInstance().getColorSchemeForBasicModule()))
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
