package jDistsim.application.designer;

import jDistsim.application.designer.common.UIConfiguration;
import jDistsim.core.simulation.modules.IModuleLibrary;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.core.simulation.modules.ModuleContainer;
import jDistsim.core.simulation.modules.lib.create.*;
import jDistsim.core.simulation.modules.lib.delay.*;
import jDistsim.core.simulation.modules.lib.dispose.Dispose;
import jDistsim.core.simulation.modules.lib.dispose.DisposeDescription;
import jDistsim.core.simulation.modules.lib.dispose.DisposeFactory;
import jDistsim.core.simulation.modules.lib.dispose.DisposeView;
import jDistsim.core.simulation.modules.lib.sender.*;
import jDistsim.utils.ioc.ObjectContainer;

import java.util.*;


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
                .toIndex(1)
                .toView(new CreateView())
                .toDescription(new CreateDescription())
                .toFactory(new CreateFactory())
                .toUIFactory(new CreateUIFactory())
                .withConfiguration(new ModuleConfiguration("create", UIConfiguration.getInstance().getColorSchemeForBasicModule()))
                .build();

        container.bind(Dispose.class, new ModuleContainer())
                .toIndex(2)
                .toView(new DisposeView())
                .toDescription(new DisposeDescription())
                .toFactory(new DisposeFactory())
                .toUIFactory(new CreateUIFactory())
                .withConfiguration(new ModuleConfiguration("dispose", UIConfiguration.getInstance().getColorSchemeForBasicModule()))
                .build();

        container.bind(Delay.class, new ModuleContainer())
                .toIndex(3)
                .toView(new DelayView())
                .toDescription(new DelayDescription())
                .toFactory(new DelayFactory())
                .toUIFactory(new DelayUIFactory())
                .withConfiguration(new ModuleConfiguration("delay", UIConfiguration.getInstance().getColorSchemeForBasicModule()))
                .build();

        container.bind(Sender.class, new ModuleContainer())
                .toIndex(4)
                .toView(new SenderView())
                .toDescription(new SenderDescription())
                .toFactory(new SenderFactory())
                .toUIFactory(new SenderUIFactory())
                .withConfiguration(new ModuleConfiguration("sender", UIConfiguration.getInstance().getColorSchemeForDistributedModule()))
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
