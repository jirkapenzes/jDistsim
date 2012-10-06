package jDistsim.core.simulation.event.description;

import jDistsim.core.simulation.event.ui.IEventDescription;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 17:50
 */
public class DelayDescription implements IEventDescription {

    @Override
    public String getTitle() {
        return "DelayEvent";
    }

    @Override
    public String getDescription() {
        return "<html>Hold the entity in the DELAY block for a time sampled from a Uniform distribution between 3 and 5 time units.</html>";
    }
}
