package jDistsim.application.designer;

import jDistsim.application.designer.common.UIConfiguration;
import jDistsim.core.simulation.modules.IModuleLibrary;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.core.simulation.modules.ModuleContainer;
import jDistsim.core.simulation.modules.lib.assign.Assign;
import jDistsim.core.simulation.modules.lib.assign.AssignFactory;
import jDistsim.core.simulation.modules.lib.assign.AssignUIFactory;
import jDistsim.core.simulation.modules.lib.assign.AssignView;
import jDistsim.core.simulation.modules.lib.create.Create;
import jDistsim.core.simulation.modules.lib.create.CreateFactory;
import jDistsim.core.simulation.modules.lib.create.CreateUIFactory;
import jDistsim.core.simulation.modules.lib.create.CreateView;
import jDistsim.core.simulation.modules.lib.delay.Delay;
import jDistsim.core.simulation.modules.lib.delay.DelayFactory;
import jDistsim.core.simulation.modules.lib.delay.DelayUIFactory;
import jDistsim.core.simulation.modules.lib.delay.DelayView;
import jDistsim.core.simulation.modules.lib.dispose.Dispose;
import jDistsim.core.simulation.modules.lib.dispose.DisposeFactory;
import jDistsim.core.simulation.modules.lib.dispose.DisposeView;
import jDistsim.core.simulation.modules.lib.outputer.Outputer;
import jDistsim.core.simulation.modules.lib.outputer.OutputerFactory;
import jDistsim.core.simulation.modules.lib.outputer.OutputerUIFactory;
import jDistsim.core.simulation.modules.lib.outputer.OutputerView;
import jDistsim.core.simulation.modules.lib.receiver.Receiver;
import jDistsim.core.simulation.modules.lib.receiver.ReceiverFactory;
import jDistsim.core.simulation.modules.lib.receiver.ReceiverUIFactory;
import jDistsim.core.simulation.modules.lib.receiver.ReceiverView;
import jDistsim.core.simulation.modules.lib.sender.Sender;
import jDistsim.core.simulation.modules.lib.sender.SenderFactory;
import jDistsim.core.simulation.modules.lib.sender.SenderUIFactory;
import jDistsim.core.simulation.modules.lib.sender.SenderView;
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
                .toIndex(1)
                .toView(new CreateView())
                .toFactory(new CreateFactory())
                .toUIFactory(new CreateUIFactory())
                .withConfiguration(new ModuleConfiguration("create", UIConfiguration.getInstance().getColorSchemeForBasicModule()))
                .build();
        container.bind(Dispose.class, new ModuleContainer())
                .toIndex(2)
                .toView(new DisposeView())
                .toFactory(new DisposeFactory())
                .toUIFactory(new CreateUIFactory())
                .withConfiguration(new ModuleConfiguration("dispose", UIConfiguration.getInstance().getColorSchemeForBasicModule()))
                .build();
        container.bind(Delay.class, new ModuleContainer())
                .toIndex(3)
                .toView(new DelayView())
                .toFactory(new DelayFactory())
                .toUIFactory(new DelayUIFactory())
                .withConfiguration(new ModuleConfiguration("delay", UIConfiguration.getInstance().getColorSchemeForBasicModule()))
                .build();
        container.bind(Assign.class, new ModuleContainer())
                .toIndex(4)
                .toView(new AssignView())
                .toFactory(new AssignFactory())
                .toUIFactory(new AssignUIFactory())
                .withConfiguration(new ModuleConfiguration("assign", UIConfiguration.getInstance().getColorSchemeForBasicModule()))
                .build();
        container.bind(Outputer.class, new ModuleContainer())
                .toIndex(5)
                .toView(new OutputerView())
                .toFactory(new OutputerFactory())
                .toUIFactory(new OutputerUIFactory())
                .withConfiguration(new ModuleConfiguration("outputer", UIConfiguration.getInstance().getColorSchemeForBasicModule()))
                .build();
        container.bind(Sender.class, new ModuleContainer())
                .toIndex(6)
                .toView(new SenderView())
                .toFactory(new SenderFactory())
                .toUIFactory(new SenderUIFactory())
                .withConfiguration(new ModuleConfiguration("sender", UIConfiguration.getInstance().getColorSchemeForDistributedModule()))
                .build();
        container.bind(Receiver.class, new ModuleContainer())
                .toIndex(7)
                .toView(new ReceiverView())
                .toFactory(new ReceiverFactory())
                .toUIFactory(new ReceiverUIFactory())
                .withConfiguration(new ModuleConfiguration("receiver", UIConfiguration.getInstance().getColorSchemeForDistributedModule()))
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
