package jDistsim.core.simulation.simulator.event;

import jDistsim.core.simulation.distributed.DistributedModule;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.lib.NullModule;

/**
 * Author: Jirka Pénzeš
 * Date: 18.2.13
 * Time: 22:04
 */
public class ScheduleEvent implements Comparable<ScheduleEvent> {

    private enum Priority {
        Low, Normal, High
    }

    private final double time;
    private final EventContainer eventContainer;
    private Priority priority;

    public ScheduleEvent(double time, EventContainer eventContainer) {
        this.time = time;
        this.eventContainer = eventContainer;
        this.priority = makePriority();
    }

    private Priority makePriority() {
        Module module = eventContainer.getModule();
        if (module instanceof NullModule) return Priority.Low;
        if (module instanceof DistributedModule) return Priority.Normal;
        return Priority.High;
    }

    public double getTime() {
        return time;
    }

    public EventContainer getEventContainer() {
        return eventContainer;
    }

    @Override
    public int compareTo(ScheduleEvent scheduleModule) {
        Double timeA = new Double(getTime());
        Double timeB = new Double(scheduleModule.getTime());
        if (timeA.compareTo(timeB) == 0) {
            return -1 * priority.compareTo(scheduleModule.priority);
        }
        return new Double(getTime()).compareTo(scheduleModule.getTime());
    }

    @Override
    public String toString() {
        return getEventContainer().getModule().getIdentifier();
    }
}
