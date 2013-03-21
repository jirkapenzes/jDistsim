package jDistsim.core.simulation.distributed.communication;

import jDistsim.core.simulation.simulator.entity.Entity;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Author: Jirka Pénzeš
 * Date: 8.3.13
 * Time: 15:21
 */
public interface IRemote extends Remote {

    public boolean authorize(String modelName) throws RemoteException;

    public double getLookahead(double requesterTime, String requester) throws RemoteException;

    public void process(double time, Entity entity, String requester) throws RemoteException;

    public void waitForReady() throws RemoteException;
}
