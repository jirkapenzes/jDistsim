package jDistsim.core.simulation.simulator;

import jDistsim.ServiceLocator;
import jDistsim.application.designer.common.IDialogBuilder;
import jDistsim.core.simulation.exception.SimulatorCoreException;
import jDistsim.utils.logging.Logger;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 21:20
 */
public class SimulatorRunner {

    private volatile ISimulator baseSimulator;
    private volatile ISimulationModel model;
    private static volatile Thread simulatorThread;

    public SimulatorRunner(ISimulator baseSimulator, ISimulationModel model) {
        Logger.log("Initialize simulator runner");
        this.baseSimulator = baseSimulator;
        this.model = model;
    }

    public synchronized void start() {
        if (simulatorThread != null) {
            Logger.log("Interrupt old simulation thread");
            baseSimulator.stop();
            simulatorThread.interrupt();
        }

        Logger.log("Prepare simulator simulatorThread");
        simulatorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Logger.log("Started main simulator simulatorThread");
                try {
                    baseSimulator.simulate(model);
                } catch (SimulatorCoreException exception) {
                    Logger.log(exception);
                    ServiceLocator.getInstance().get(IDialogBuilder.class)
                            .buildErrorDialog(exception.getMessage() + "\nMore information in the application log.");
                }
            }
        });
        simulatorThread.start();
    }
}
