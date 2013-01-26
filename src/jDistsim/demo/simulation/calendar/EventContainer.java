package jDistsim.demo.simulation.calendar;

import jDistsim.demo.Entity;
import jDistsim.demo.Module;

/**
 * Author: Jirka Pénzeš
 * Date: 27.12.12
 * Time: 13:26
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
