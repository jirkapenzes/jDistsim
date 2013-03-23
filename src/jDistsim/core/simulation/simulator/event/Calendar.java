package jDistsim.core.simulation.simulator.event;

import jDistsim.utils.collection.SynchronizedPriorityQueue;

/**
 * Author: Jirka Pénzeš
 * Date: 18.2.13
 * Time: 22:12
 */
public class Calendar extends SynchronizedPriorityQueue<ScheduleEvent> {

    @Override
    public String toString() {
        String result = "";
        for (ScheduleEvent event : this) {
            String tmp = event.getEventContainer().getModule().getIdentifier().toUpperCase().substring(0, 1) + ": " + event.getTime();
            result += tmp + " ";
        }
        return result;
    }
}
