package jDistsim.application.designer.controller;

import jDistsim.application.designer.controller.modelSpaceFeature.ModuleAnimator;
import jDistsim.application.designer.model.ToolbarModel;
import jDistsim.application.designer.view.ToolbarView;
import jDistsim.core.modules.ModuleUI;
import jDistsim.core.simulation.model.ISimulationModelValidator;
import jDistsim.core.simulation.model.SimulationModelBuilder;
import jDistsim.core.simulation.simulator.*;
import jDistsim.core.simulation.validator.SimulationModelValidator;
import jDistsim.ui.panel.listener.ToolbarListener;
import jDistsim.utils.pattern.mvc.AbstractController;
import jDistsim.utils.pattern.mvc.AbstractFrame;

import java.awt.event.MouseEvent;
import java.util.Collection;

/**
 * Author: Jirka Pénzeš
 * Date: 3.12.12
 * Time: 23:36
 */
public class ToolbarController extends AbstractController<ToolbarModel> implements ToolbarListener {

    private DistributedSimulator simulator;

    public ToolbarController(AbstractFrame mainFrame, ToolbarModel model) {
        super(mainFrame, model);
        initializeActions();
    }

    private void initializeActions() {
        ToolbarView view = getMainFrame().getView(ToolbarView.class);
        view.setToolbarListener(this);
    }


    @Override
    public void onSimulationStartButtonClick(MouseEvent mouseEvent, Object sender) {
        InformationController informationController = getMainFrame().getController(InformationController.class);
        ModelSpaceController modelSpaceController = getMainFrame().getController(ModelSpaceController.class);

        Collection<ModuleUI> modules = modelSpaceController.getModel().getModuleList().values();
        ISimulatorEndCondition endCondition = new ISimulatorEndCondition() {
            @Override
            public boolean occurred(SimulatorEnvironment environment) {
                return environment.getLocalTime() > 20;
            }
        };

        ISimulationModel simulationModel = SimulationModelBuilder.buildSimulationModelFromUI(modules);
        ISimulationModelValidator modelValidator = new SimulationModelValidator();

        simulator = new DistributedSimulator(modelValidator, endCondition);
        simulator.setAnimator(new ModuleAnimator(modelSpaceController.getModel().getModuleList(), modelSpaceController.getView().getContentPane()));
        simulator.getOutput().getWriters().add(informationController.makeSimulatorWriter());
        simulator.getOutput().getWriters().add(new SimulatorLoggerHandler());

        SimulatorRunner simulatorRunner = new SimulatorRunner(simulator, simulationModel);
        simulatorRunner.start();
    }

    @Override
    public void onSimulationStopButtonClick(MouseEvent mouseEvent, Object sender) {
        if (simulator != null)
            simulator.stop();

        simulator = null;
    }
}
