package jDistsim.application.designer;

import jDistsim.application.designer.common.UIConfiguration;
import jDistsim.core.simulation.modules.IModuleLibrary;
import jDistsim.core.simulation.modules.ModuleContainer;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.core.simulation.modules.lib.create.*;
import jDistsim.core.simulation.modules.lib.delay.*;
import jDistsim.core.simulation.modules.lib.dispose.Dispose;
import jDistsim.core.simulation.modules.lib.dispose.DisposeDescription;
import jDistsim.core.simulation.modules.lib.dispose.DisposeFactory;
import jDistsim.core.simulation.modules.lib.dispose.DisposeView;
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
        container.bind(Create.class, new ModuleContainer())
                .toView(new CreateView())
                .toDescription(new CreateDescription())
                .toFactory(new CreateFactory())
                .toUIFactory(new CreateUIFactory())
                .withConfiguration(new ModuleConfiguration("create", UIConfiguration.getInstance().getColorSchemeForBasicModule()))
                .build();

        container.bind(Dispose.class, new ModuleContainer())
                .toView(new DisposeView())
                .toDescription(new DisposeDescription())
                .toFactory(new DisposeFactory())
                .toUIFactory(new CreateUIFactory())
                .withConfiguration(new ModuleConfiguration("dispose", UIConfiguration.getInstance().getColorSchemeForBasicModule()))
                .build();

        container.bind(Delay.class, new ModuleContainer())
                .toView(new DelayView())
                .toDescription(new DelayDescription())
                .toFactory(new DelayFactory())
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
