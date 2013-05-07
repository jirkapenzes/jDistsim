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
    // požadavek o autorizace
    public boolean authorize(String requesterModelName) throws RemoteException;
    // požadavek o získání výhledu (nulové zprávy)
    public void getLookahead(double requesterTime, String requester) throws RemoteException;
    // požadavek na zpracování entity
    public void process(double time, Entity entity, String requester) throws RemoteException;
    // zaslání nulové zprávy
    public void processNullModule(double time, String requester) throws RemoteException;
    // LP neodpoví, dokud není připraven simulovat
    public void waitForReady() throws RemoteException;
}
