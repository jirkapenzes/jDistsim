package jDistsim.demo;


/**
 * Author: Jirka Pénzeš
 * Date: 21.12.12
 * Time: 22:19
 */
public class DemoMain {

    public static void main(String[] args) {
        RootModule createModule1 = new CreateModule("create_1", 1);

        Module conditionModule1 = new ConditionModule("condition_1", new ConditionPredicate() {
            @Override
            public boolean match(Entity entity) {
                int entityOrder = (int) entity.getAttribute("order");
                return entityOrder % 2 == 0;
            }
        });

        Module processModule1 = new ProcessModule("process_1", 1);
        Module printModule1 = new PrintModule("print_1");
        Module disposeModule1 = new DisposeModule("dispose_1");

        createModule1.connect(conditionModule1);
        conditionModule1.connect(0, processModule1);
        conditionModule1.connect(1, disposeModule1);

        processModule1.connect(printModule1);
        printModule1.connect(disposeModule1);

        ISimulationModel simulationModel = new SimulationModel();
        simulationModel.addRootModule(createModule1);

        Simulator simulator = new Simulator(simulationModel);
        simulator.simulate();
    }
}
