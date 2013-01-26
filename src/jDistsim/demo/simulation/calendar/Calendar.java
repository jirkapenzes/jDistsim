package jDistsim.demo.simulation.calendar;

import jDistsim.demo.Entity;
import jDistsim.demo.Module;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Author: Jirka Pénzeš
 * Date: 26.12.12
 * Time: 20:34
 */
public class Calendar {

    private final PriorityBlockingQueue<ScheduleModule> queue;

    public Calendar() {
        queue = new PriorityBlockingQueue<>();
    }

    public Calendar(int capacity) {
        queue = new PriorityBlockingQueue<>(capacity);
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public void plan(double localTime, Module module, Entity entity) {
        queue.put(new ScheduleModule(localTime, new EventContainer(module, entity)));
    }

    public ScheduleModule poll() {
        if (queue.isEmpty())
            return null;
        return queue.poll();
    }

    public synchronized ScheduleModule peek() {
        if (queue.isEmpty())
            return null;
        return queue.peek();
    }

    public synchronized int size() {
        return queue.size();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}