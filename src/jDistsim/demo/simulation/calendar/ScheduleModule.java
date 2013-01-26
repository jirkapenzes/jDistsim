package jDistsim.demo.simulation.calendar;

/**
 * Author: Jirka Pénzeš
 * Date: 26.12.12
 * Time: 23:26
 */
public class ScheduleModule implements Comparable<ScheduleModule> {

    private final double time;
    private final EventContainer eventContainer;

    public ScheduleModule(double time, EventContainer eventContainer) {
        this.time = time;
        this.eventContainer = eventContainer;
    }

    public EventContainer getEventContainer() {
        return eventContainer;
    }

    public double getTime() {
        return time;
    }

    @Override
    public int compareTo(ScheduleModule scheduleModule) {
        return new Double(getTime()).compareTo(scheduleModule.getTime());
    }

    @Override
    public String toString() {
        return getEventContainer().getModule().getIdentifier();
    }
}
