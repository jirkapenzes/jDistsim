package jDistsim.core.simulation.distributed.communication;

import jDistsim.application.designer.common.Application;
import jDistsim.core.simulation.distributed.DistributedSimulator;
import jDistsim.core.simulation.simulator.entity.Entity;
import jDistsim.utils.logging.Logger;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Author: Jirka Pénzeš
 * Date: 8.3.13
 * Time: 16:01
 */
public class RmiRemote extends UnicastRemoteObject implements IRemote {

    private DistributedSimulator simulator;

    protected RmiRemote() throws RemoteException {
        super();
    }

    public RmiRemote(DistributedSimulator simulator) throws RemoteException {
        this();
        this.simulator = simulator;
    }

    @Override
    public boolean authorize(String modelName) throws RemoteException {
        boolean exist = Application.global().getDistributedModels().containsKey(modelName);
        Logger.log("[RMI] Authorize request -> " + modelName + " -> " + exist);
        return exist;
    }

    @Override
    public double getLookahead() throws RemoteException {
        double lookahead = simulator.getLookahead();
        Logger.log("[RMI] Lookahead request -> " + lookahead);
        return lookahead;
    }

    @Override
    public void process(double time, Entity entity, String requester) throws RemoteException {
        Logger.log("[RMI] process -> " + time + "; " + entity.getIdentifier() + "; " + requester);
        simulator.addDistributeModule(time, entity, requester);
    }

    @Override
    public void waitForReady() throws RemoteException {
        Logger.log("[RMI] wait for ready");
        while (!simulator.isReady()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Logger.log(e);
            }
        }
    }
}
