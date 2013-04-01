package jDistsim.application.designer.controller;

import jDistsim.ServiceLocator;
import jDistsim.application.designer.common.Application;
import jDistsim.application.designer.controller.modelSpaceFeature.ModuleAnimator;
import jDistsim.application.designer.controller.tabLogic.OutputTabLogic;
import jDistsim.application.designer.model.ToolbarModel;
import jDistsim.application.designer.view.ToolbarView;
import jDistsim.core.common.SaveBox;
import jDistsim.core.common.SaveConnect;
import jDistsim.core.common.SaveContainer;
import jDistsim.core.simulation.distributed.DistributedModelDefinition;
import jDistsim.core.simulation.distributed.DistributedModuleSettings;
import jDistsim.core.simulation.distributed.DistributedSimulator;
import jDistsim.core.simulation.distributed.SenderSettings;
import jDistsim.core.simulation.model.ISimulationModelValidator;
import jDistsim.core.simulation.model.SimulationModelBuilder;
import jDistsim.core.simulation.modules.*;
import jDistsim.core.simulation.modules.ui.ModuleConnectedPointUI;
import jDistsim.core.simulation.modules.ui.ModuleUI;
import jDistsim.core.simulation.simulator.ISimulationModel;
import jDistsim.core.simulation.simulator.SimulatorLoggerHandler;
import jDistsim.core.simulation.simulator.SimulatorRunner;
import jDistsim.core.simulation.validator.SimulationModelValidator;
import jDistsim.ui.panel.listener.ToolbarListener;
import jDistsim.utils.collection.ReadOnlyList;
import jDistsim.utils.common.WaitDialog;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.pattern.mvc.AbstractController;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.persistence.Persistor;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.MouseEvent;
import java.io.File;
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
        String modelFullPath = Application.global().getModelFullPath();
        if (modelFullPath.equals("")) {
            onModelSaveAsButtonClick(mouseEvent, sender);
        } else {
            String modelName = Application.global().getModelName();
            save(modelSpaceController, modelFullPath, modelName);
        }
    }

    @Override
    public void onModelOpenButtonClick(MouseEvent mouseEvent, Object sender) {
        final ModelSpaceController modelSpaceController = getMainFrame().getController(ModelSpaceController.class);

        JFileChooser fd = new JFileChooser();
        fd.setMultiSelectionEnabled(false);
        fd.setCurrentDirectory(new File("."));
        fd.setDialogTitle("Select model for configure");
        fd.setFileHidingEnabled(true);
        fd.setApproveButtonText("Open model");
        FileFilter fileFilter = new FileNameExtensionFilter("jDistsim model (*.jdsim)", "jdsim");
        fd.addChoosableFileFilter(fileFilter);
        fd.setAcceptAllFileFilterUsed(false);
        fd.setFileFilter(fileFilter);

        int returnVal = fd.showOpenDialog(modelSpaceController.getMainFrame().getFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fd.getSelectedFile();
            final String filePath = file.getAbsolutePath();

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        WaitDialog waitDialog = new WaitDialog(modelSpaceController.getMainFrame().getFrame());
                        waitDialog.show();
                        Logger.log();
                        Logger.log(filePath);
                        SaveBox saveBox = (SaveBox) Persistor.load(filePath);
                        Logger.log();
                        // load remote models
                        for (DistributedModelDefinition modelDefinition : saveBox.getRemotes()) {
                            DistributedModelDefinition loadDefinition = new DistributedModelDefinition(modelDefinition.getModelName(), modelDefinition.getRmiModelName(),
                                    modelDefinition.getAddress(), modelDefinition.getPort(), modelDefinition.isLookahead(), modelDefinition.isReceive());

                            Application.global().getDistributedModels().put(loadDefinition.getRmiModelName(), loadDefinition);
                        }
                        Application.global().getDistributedModels().notifyObservers();
                        Logger.log();

                        // load local network settings
                        Application.global().getNetworkSettings().setModelName(saveBox.getNetworkSettings().getModelName());
                        Application.global().getNetworkSettings().setPort(saveBox.getNetworkSettings().getPort());
                        Application.global().getNetworkSettings().notifyObservers();
                        Logger.log();

                        // load model
                        modelSpaceController.unselectedActiveModule();
                        modelSpaceController.getModel().getModuleList().clear();
                        modelSpaceController.getModel().getModuleList().notifyObservers();
                        modelSpaceController.getView().getContentPane().removeAll();
                        Logger.log();

                        for (SaveContainer container : saveBox.getModelData()) {
                            Class moduleClass = Class.forName(container.getModule());
                            IModuleFactory moduleFactory = ServiceLocator.getInstance().get(IModuleLibrary.class).get(moduleClass).getFactory();
                            ModuleSettings settings = container.getSettings();
                            if (settings instanceof DistributedModuleSettings && settings instanceof SenderSettings) {
                                DistributedModuleSettings distributedModuleSettings = (DistributedModuleSettings) settings;
                                String modelName = distributedModuleSettings.getDistributedModelDefinition().getRmiModelName();
                                if (!modelName.equals("null")) {
                                    distributedModuleSettings.setDistributedModelDefinition(Application.global().getDistributedModels().get(modelName));
                                }
                            }
                            Module module = moduleFactory.create(settings);
                            Logger.log();

                            ModuleUI moduleUI = new ModuleUI(module, moduleFactory.createView());
                            moduleUI.setLocation(container.getLocation());
                            modelSpaceController.getModel().getModuleList().put(module.getIdentifier(), moduleUI);
                        }
                        Logger.log();

                        // load dependencies
                        for (SaveContainer container : saveBox.getModelData()) {
                            String identifier = container.getSettings().getIdentifier();
                            ModuleUI moduleA = modelSpaceController.getModel().getModuleList().get(identifier);

                            for (SaveConnect saveConnect : container.getOut()) {
                                ModuleConnectedPointUI pointA = moduleA.getModuleConnectedPointUI(saveConnect.getSourcePointIndex(), ModuleConnectedPointUI.Type.OUTPUT);
                                ModuleUI moduleB = modelSpaceController.getModel().getModuleList().get(saveConnect.getDependency());
                                ModuleConnectedPointUI pointB = moduleB.getModuleConnectedPointUI(saveConnect.getTargetPointIndex(), ModuleConnectedPointUI.Type.INPUT);
                                modelSpaceController.connect(pointA, pointB);
                            }
                        }
                        Logger.log();

                        modelSpaceController.rebuildModel();
                        waitDialog.hide();
                    } catch (Exception exception) {
                        Logger.log(exception);
                        Logger.log(exception.getStackTrace());
                    }
                }
            });
            thread.start();
        }
    }

    @Override
    public void onModelSaveAsButtonClick(MouseEvent mouseEvent, Object sender) {
        ModelSpaceController modelSpaceController = getMainFrame().getController(ModelSpaceController.class);

        JFileChooser fd = new JFileChooser();
        fd.setMultiSelectionEnabled(false);
        fd.setCurrentDirectory(new File("."));
        fd.setDialogTitle("Select model for configure");
        fd.setFileHidingEnabled(true);
        fd.setApproveButtonText("Open model");
        FileFilter fileFilter = new FileNameExtensionFilter("jDistsim model (*.jdsim)", "jdsim");
        fd.addChoosableFileFilter(fileFilter);
        fd.setAcceptAllFileFilterUsed(false);
        fd.setFileFilter(fileFilter);

        int returnVal = fd.showSaveDialog(modelSpaceController.getMainFrame().getFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String fileName = addJDistsimExtension(fd.getSelectedFile().getPath());
            String modelName = addJDistsimExtension(fd.getSelectedFile().getName());

            save(modelSpaceController, fileName, modelName);
        }
    }

    private void save(ModelSpaceController controller, String fileName, String modelName) {
        controller.unselectedActiveModule();
        try {
            SaveBox saveBox = new SaveBox();
            for (ModuleUI moduleUI : controller.getModel().getModuleList().values()) {
                Module module = moduleUI.getModule();
                SaveContainer saveContainer = new SaveContainer(module, moduleUI.getLocation());

                ReadOnlyList<ModuleConnectedPoint> inputDependencies = module.getInputConnectedPoints();
                for (int index = 0; index < inputDependencies.size(); index++) {
                    ModuleConnectedPoint moduleConnectedPoint = (ModuleConnectedPoint) inputDependencies.get(index);

                    for (Module dependency : moduleConnectedPoint.getDependencies()) {
                        saveContainer.getIn().add(new SaveConnect(dependency.getIdentifier(), index, 0));
                    }
                }
                ReadOnlyList<ModuleConnectedPoint> outputDependencies = module.getOutputConnectedPoints();
                for (int index = 0; index < outputDependencies.size(); index++) {
                    ModuleConnectedPoint moduleConnectedPoint = (ModuleConnectedPoint) outputDependencies.get(index);

                    for (Module dependency : moduleConnectedPoint.getDependencies()) {
                        saveContainer.getOut().add(new SaveConnect(dependency.getIdentifier(), index, 0));
                    }
                }
                saveBox.store(saveContainer);
            }
            for (DistributedModelDefinition modelDefinition : Application.global().getDistributedModels().values()) {
                saveBox.store(modelDefinition);
            }
            saveBox.setNetworkSettings(Application.global().getNetworkSettings());
            Persistor.save(saveBox, fileName);

            Application.global().setModelName(modelName);
            Application.global().setModelFullPath(fileName);
            Application.global().notifyObservers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String addJDistsimExtension(String fileName) {
        return fileName.contains(".jdsim") ? fileName : fileName + ".jdsim";
    }
}
