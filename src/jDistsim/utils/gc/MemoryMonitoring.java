package jDistsim.utils.gc;

import jDistsim.utils.action.ActionObject;
import jDistsim.utils.logging.Logger;

/**
 * Author: Jirka Pénzeš
 * Date: 28.10.12
 * Time: 1:12
 */
public class MemoryMonitoring extends ActionObject implements Runnable {

    private static final long MEGABYTE = 1024L * 1024L;
    private int sleepTime;
    private long totalMemory;
    private long freeMemory;

    private boolean running;
    private final Object lock = new Object();

    public MemoryMonitoring() {
        this(1000);
    }

    public MemoryMonitoring(int sleepTime) {
        this.sleepTime = sleepTime;
        calculateMemory();
    }

    @Override
    public void run() {
        synchronized (lock) {
            running = true;
        }
        while (running) {
            try {
                calculateMemory();
                doActionPerformed();
                Thread.sleep(sleepTime);
            } catch (InterruptedException exception) {
                Logger.log(exception);
            }
        }
    }

    public void stopMonitoring() {
        synchronized (lock) {
            running = false;
        }
    }

    public long getFreeMemory() {
        return bytesToMegabytes(freeMemory);
    }

    public long getTotalMemory() {
        return bytesToMegabytes(totalMemory);
    }

    public long getUsedMemory() {
        return bytesToMegabytes(totalMemory - freeMemory);
    }

    private void calculateMemory() {
        totalMemory = Runtime.getRuntime().totalMemory();
        freeMemory = Runtime.getRuntime().freeMemory();
    }

    private static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }
}
