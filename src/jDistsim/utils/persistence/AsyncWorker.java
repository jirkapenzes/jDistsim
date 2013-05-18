package jDistsim.utils.persistence;

import jDistsim.utils.common.ThreadWaiter;
import jDistsim.utils.logging.Logger;

/**
 * Author: Jirka Pénzeš
 * Date: 8.4.13
 * Time: 10:28
 */
public abstract class AsyncWorker {

    private Thread thread;
    private boolean run;

    public AsyncWorker() {
        run = false;
    }

    public AsyncWorker runAsync() {
        run = true;
        if (thread != null) {
            Logger.log("Interrupt old async thread");
            thread.interrupt();
        }
        Logger.log("Prepare async thread");
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Logger.log("Run async thread -> doWork()");
                doWork();
                Logger.log("Run async thread -> workerCompleted()");
                workerCompleted();
                run = false;
            }
        });
        thread.start();
        return this;
    }

    public abstract void doWork();

    public abstract void workerCompleted();

    public abstract void rollback();

    public void stop() {
        Logger.log("Explicit stop async thread");
        thread.interrupt();
        run = false;
        Logger.log("Asyncworker rollback");
        rollback();
    }

    public boolean isRun() {
        return run;
    }

    public AsyncWorker waitForComplete() {
        while (run) {
            ThreadWaiter.waitCurrentThreadFor(10);
        }
        Logger.log("AsyncWorker done");
        return this;
    }
}
