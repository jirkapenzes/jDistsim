package jDistsim.core.simulation.distributed.communication;

import jDistsim.application.designer.common.LocalNetworkSettings;
import jDistsim.core.simulation.distributed.DistributedModelDefinition;
import jDistsim.core.simulation.distributed.DistributedSimulator;
import jDistsim.core.simulation.exception.DistributedException;
import jDistsim.core.simulation.exception.RmiRemoteObjectNotFoundException;
import jDistsim.utils.logging.Logger;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Author: Jirka Pénzeš
 * Date: 8.3.13
 * Time: 12:14
 */
public class Communication {

    private LocalNetworkSettings networkSettings;
    private IRemote remote;
    private static boolean CreateRegistry = false;

    public Communication(LocalNetworkSettings networkSettings) {
        this.networkSettings = networkSettings;
    }

    public void start(DistributedSimulator simulator) {
        try {
            validateNetworkSettings(networkSettings);
            Registry registry;
            if (CreateRegistry) {
                registry = LocateRegistry.getRegistry(networkSettings.getPort());
            } else {
                registry = LocateRegistry.createRegistry(networkSettings.getPort());
                CreateRegistry = true;
            }
            remote = new RmiRemote(simulator);
            registry.rebind(networkSettings.getModelName(), remote);
        } catch (RemoteException exception) {
            throw new DistributedException();
        }
    }

    private void validateNetworkSettings(LocalNetworkSettings networkSettings) {
        int port = networkSettings.getPort();
        if (port <= 0 || port > 65535) {
            throw new DistributedException("Invalid local port");
        }
        String name = networkSettings.getModelName();
        if (name.equals("null") || name.equals("")) {
            throw new DistributedException("Invalid local model name");

        }
    }

    public IRemote bind(DistributedModelDefinition modelDefinition) {
        try {
            Registry remoteRegistry = LocateRegistry.getRegistry(modelDefinition.getAddress(), modelDefinition.getPort());
            RmiBinder binder = new RmiBinder();
            return (IRemote) binder.lookupRmiObject(modelDefinition.getRmiModelName(), remoteRegistry);
        } catch (RemoteException exception) {
            throw new RmiRemoteObjectNotFoundException();
        }
    }

    public LocalNetworkSettings getNetworkSettings() {
        return networkSettings;
    }

    public void stop() {
        try {
            Logger.log("RMI -> model unbind");
            Registry registry = LocateRegistry.getRegistry(networkSettings.getPort());
            registry.unbind(networkSettings.getModelName());
        } catch (NotBoundException | RemoteException exception) {

        }
    }

    public void waitForReady() {
        try {
            remote.waitForReady();
        } catch (RemoteException e) {
            throw new RmiRemoteObjectNotFoundException();
        }
    }


}
