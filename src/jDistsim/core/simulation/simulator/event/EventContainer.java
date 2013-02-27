package jDistsim.core.simulation.simulator.event;

import jDistsim.core.modules.Module;
import jDistsim.core.simulation.simulator.entity.Entity;

/**
 * Author: Jirka Pénzeš
 * Date: 18.2.13
 * Time: 22:04
 */
public class EventContainer {

    private Module module;
    private Entity entity;

    public EventContainer(Module module, Entity entity) {
        this.module = module;
        this.entity = entity;
    }

    public Module getModule() {
        return module;
    }

    public Entity getEntity() {
        return entity;
    }
}
