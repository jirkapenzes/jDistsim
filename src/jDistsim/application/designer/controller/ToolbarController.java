package jDistsim.application.designer.controller;

import jDistsim.ServiceLocator;
import jDistsim.application.designer.common.Application;
import jDistsim.application.designer.controller.modelSpaceFeature.ModuleAnimator;
import jDistsim.application.designer.controller.tabLogic.OutputTabLogic;
import jDistsim.application.designer.model.ToolbarModel;
import jDistsim.application.designer.view.ToolbarView;
import jDistsim.core.common.SaveBox;
import jDistsim.core.common.SaveContainer;
import jDistsim.core.simulation.distributed.DistributedSimulator;
import jDistsim.core.simulation.model.ISimulationModelValidator;
import jDistsim.core.simulation.model.SimulationModelBuilder;
import jDistsim.core.simulation.modules.IModuleLibrary;
import jDistsim.core.simulation.modules.IModuleView;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ui.ModuleUI;
import jDistsim.core.simulation.simulator.ISimulationModel;
import jDistsim.core.simulation.simulator.SimulatorLoggerHandler;
import jDistsim.core.simulation.simulator.SimulatorRunner;
import jDistsim.core.simulation.validator.SimulationModelValidator;
import jDistsim.ui.panel.listener.ToolbarListener;
import jDistsim.utils.pattern.mvc.AbstractController;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.xml.wox.serial.Persistor;

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
        OutputTabLogic outputTabLogic = informationController.getOutputTabLogic();

        Collection<ModuleUI> modules = modelSpaceController.getModel().getModuleList().values();

        ISimulationModel simulationModel = SimulationModelBuilder.buildSimulationModelFromUI(modules);
        ISimulationModelValidator modelValidator = new SimulationModelValidator();

        simulator = new DistributedSimulator(modelValidator, Application.global().getNetworkSettings());
        simulator.setAnimator(new ModuleAnimator(modelSpaceController.getModel().getModuleList(), modelSpaceController.getView().getContentPane()));
        simulator.getOutput().getWriters().add(informationController.makeSimulatorWriter());
        simulator.getOutput().getWriters().add(new SimulatorLoggerHandler());
        simulator.getEnvironment().addObserver(outputTabLogic);

        SimulatorRunner simulatorRunner = new SimulatorRunner(simulator, simulationModel);
        simulatorRunner.start();
    }

    @Override
    public void onSimulationStopButtonClick(MouseEvent mouseEvent, Object sender) {
        if (simulator != null)
            simulator.stop();

        simulator = null;
    }

    @Override
    public void onModelSaveButtonClick(MouseEvent mouseEvent, Object sender) {
        ModelSpaceController modelSpaceController = getMainFrame().getController(ModelSpaceController.class);
        modelSpaceController.unselectedActiveModule();
        try {
            SaveBox saveBox = new SaveBox();
            for (ModuleUI moduleUI : modelSpaceController.getModel().getModuleList().values()) {
                Module module = moduleUI.getModule();
                SaveContainer saveContainer = new SaveContainer(module, moduleUI.getLocation());

                for (Object dependency : module.getAllInputDependencies())
                    saveContainer.in.add((Module) dependency);

                for (Object dependency : module.getAllOutputDependencies())
                    saveContainer.out.add((Module) dependency);

                saveBox.store(saveContainer);
            }
            Persistor.save(saveBox, "C:/Users/Jirka/Desktop/demo.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onModelOpenButtonClick(MouseEvent mouseEvent, Object sender) {
        ModelSpaceController modelSpaceController = getMainFrame().getController(ModelSpaceController.class);
        modelSpaceController.unselectedActiveModule();

        modelSpaceController.getModel().getModuleList().clear();
        modelSpaceController.getModel().getModuleList().notifyObservers();
        modelSpaceController.getView().getContentPane().removeAll();

        SaveBox saveBox = (SaveBox) Persistor.load("C:/Users/Jirka/Desktop/demo.xml");
        for (SaveContainer saveContainer : saveBox.getData()) {
            IModuleView moduleView = ServiceLocator.getInstance().get(IModuleLibrary.class).get(saveContainer.module.getClass()).getFactory().createView();
            ModuleUI moduleUI = new ModuleUI(saveContainer.module, moduleView);
            moduleUI.setLocation(saveContainer.location);
            modelSpaceController.getModel().getModuleList().put(saveContainer.module.getIdentifier(), moduleUI);
        }
        try {
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        modelSpaceController.getModel().getModuleList().notifyObservers();
        modelSpaceController.getView().getContentPane().repaint();
    }
}
