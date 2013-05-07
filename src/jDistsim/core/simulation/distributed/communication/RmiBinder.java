package jDistsim.core.simulation.distributed.communication;

import jDistsim.core.simulation.exception.RmiRemoteObjectNotFoundException;
import jDistsim.utils.common.ThreadWaiter;
import jDistsim.utils.logging.Logger;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

/**
 * Author: Jirka Pénzeš
 * Date: 8.3.13
 * Time: 19:09
 */
public class RmiBinder {

    // default value
    private int steps = 1000;
    // default value
    private int sleepTime = 1000;

    public RmiBinder() {
    }

    public RmiBinder(int steps) {
        setSteps(steps);
    }

    public RmiBinder(int steps, int sleepTime) {
        initialize(steps, sleepTime);
    }

    private void initialize(int steps, int sleepTime) {
        setSleepTime(sleepTime);
        setSteps(steps);
    }

    private void setSteps(int steps) {
        this.steps = steps;
    }

    private void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public <T> T lookupRmiObject(String name, Registry registry) throws RmiRemoteObjectNotFoundException {
        for (int i = 0; i < steps; i++) {
            try {
                return lookupRemoteObject(name, registry);
            } catch (Exception exception) {
                Logger.log("Remote object " + name + " not found [attempt " + i + "]");
            }
            ThreadWaiter.waitCurrentThreadFor(sleepTime);
        }
        throw new RmiRemoteObjectNotFoundException();
    }

    public <T> T lookupRmiObject(String name, Registry registry, int steps, int sleepTime) throws RmiRemoteObjectNotFoundException {
        initialize(steps, sleepTime);
        return lookupRmiObject(name, registry);
    }

    private <T> T lookupRemoteObject(String name, Registry registry) throws RemoteException, NotBoundException {
        Object object = registry.lookup(name);
        Logger.log("Remote object " + name + " has been found");
        return (T) object;
    }

    private void sleep(int sleep) {
        if (sleep == 0) {
            return;
        }
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException exception) {
        }
    }
}
