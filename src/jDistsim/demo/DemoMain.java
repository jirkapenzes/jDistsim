package jDistsim.demo;


/**
 * Author: Jirka Pénzeš
 * Date: 21.12.12
 * Time: 22:19
 */
public class DemoMain {

    public static void main(String[] args) {
        ISimulationModel simulationModel = new SimulationModel();
        simulationModel.addSimulationModule(new CreateSimulationModule());

        DistributedSimulation distributedSimulation = new DistributedSimulation(new RmiCommunication());
        distributedSimulation.simulate(simulationModel, condition);
    }
}
