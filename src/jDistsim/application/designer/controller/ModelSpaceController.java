package jDistsim.application.designer.controller;

import jDistsim.application.designer.model.ModelSpaceModel;
import jDistsim.application.designer.view.ModelSpaceView;
import jDistsim.core.modules.IModuleFactory;
import jDistsim.core.modules.Module;
import jDistsim.core.modules.ModuleConnectedPointUI;
import jDistsim.core.modules.ModuleUI;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.pattern.mvc.AbstractController;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.observer.IObserver;
import jDistsim.utils.pattern.observer.Observable;

import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 15:01
 */
public class ModelSpaceController extends AbstractController<ModelSpaceModel> implements DropTargetListener, IObserver {

    private ModelSpaceView view;
    private ModuleUI currentDragModule;
    private ModuleUI currentActiveModule;
    private HashMap<String, ModuleUI> moduleList;
    private Point mousePositionDown;
    private List<ModuleConnectedPointUI> currentShowPoints;

    public ModelSpaceController(AbstractFrame mainFrame, ModelSpaceModel model) {
        super(mainFrame, model);
        view = getMainFrame().getView(ModelSpaceView.class);
        moduleList = new HashMap<>();
        currentShowPoints = new ArrayList<>();
        getModel().addObserver(this);

        new DropTarget(view.getContentPane(), this);
        view.getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                unselectedActiveModule();
            }
        });
    }

    private void unselectedActiveModule() {
        if (currentActiveModule != null)
            currentActiveModule.setActive(false);
    }

    private void selectedActiveModule(ModuleUI module) {
        module.setActive(true);
        currentActiveModule = module;
    }

    @Override
    public void dragEnter(DropTargetDragEvent dropTargetDragEvent) {
        Transferable transferable = dropTargetDragEvent.getTransferable();
        try {
            IModuleFactory moduleFactory = (IModuleFactory) transferable.getTransferData(transferable.getTransferDataFlavors()[0]);
            Module module = moduleFactory.create();

            currentDragModule = new ModuleUI(module);
            currentDragModule.setLocation(calculateDragLocation(dropTargetDragEvent.getLocation(), currentDragModule.getSize()));
            view.getContentPane().add(currentDragModule, 0);
            view.getContentPane().repaint();
        } catch (Exception exception) {
            Logger.log(exception);
        }
    }

    private Point calculateDragLocation(Point location, Dimension dimension) {
        Point point = new Point();
        point.setLocation(location.getX() - (dimension.width / 2), location.getY() - (dimension.height / 2));
        return point;
    }

    @Override
    public void dragOver(DropTargetDragEvent dropTargetDragEvent) {
        Point location = calculateDragLocation(dropTargetDragEvent.getLocation(), currentDragModule.getSize());
        currentDragModule.setLocation(location);
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dropTargetDragEvent) {
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        view.getContentPane().remove(currentDragModule);
        view.getContentPane().repaint();
    }

    @Override
    public void drop(DropTargetDropEvent dropTargetDropEvent) {
        try {
            Transferable transferable = dropTargetDropEvent.getTransferable();
            IModuleFactory moduleFactory = (IModuleFactory) transferable.getTransferData(transferable.getTransferDataFlavors()[0]);

            String identifier = moduleFactory.createIdentifier();
            currentDragModule.setIdentifier(identifier);
            moduleList.put(identifier, currentDragModule);
            currentDragModule.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    ModuleUI module = (ModuleUI) mouseEvent.getSource();
                    if (module == currentActiveModule && module.getActive()) {
                        module.setActive(false);
                        return;
                    }

                    unselectedActiveModule();
                    module.setActive(true);
                    currentActiveModule = moduleList.get(module.getIdentifier());
                }

                @Override
                public void mousePressed(MouseEvent mouseEvent) {
                    ModuleUI moduleUI = (ModuleUI) mouseEvent.getSource();
                    switch (mouseEvent.getButton()) {
                        case MouseEvent.BUTTON1:
                            mousePositionDown = new Point(mouseEvent.getX(), mouseEvent.getY());
                            moduleUI.repaint();
                            break;
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    ModuleUI moduleUI = (ModuleUI) e.getSource();

                    for(ModuleConnectedPointUI connectedPointUI : currentShowPoints)         {
                        connectedPointUI.setLocation(calculatePointPosition(connectedPointUI,  moduleUI));
                        connectedPointUI.setVisible(true);
                    }
                    view.getContentPane().repaint();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (getModel().isRelations()) {
                        ModuleUI moduleUI = (ModuleUI) e.getSource();
                        ModelSpaceView view = getMainFrame().getView(ModelSpaceView.class);

                        for (ModuleConnectedPointUI connectedPointUI : moduleUI.getInputPointsUI()) {
                            connectedPointUI.setLocation(calculatePointPosition(connectedPointUI,  moduleUI));
                            view.getContentPane().add(connectedPointUI, 0);
                        }
                        currentShowPoints.addAll(moduleUI.getInputPointsUI());
                        for (ModuleConnectedPointUI connectedPointUI : moduleUI.getOutputPointsUI()) {
                            connectedPointUI.setLocation(calculatePointPosition(connectedPointUI,  moduleUI));
                            view.getContentPane().add(connectedPointUI, 0);
                        }
                        currentShowPoints.addAll(moduleUI.getOutputPointsUI());
                        view.getContentPane().repaint();
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    for (ModuleConnectedPointUI connectedPointUI : currentShowPoints) {
                        view.getContentPane().remove(connectedPointUI);
                    }
                    view.getContentPane().repaint();
                }
            });

            currentDragModule.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent mouseEvent) {
                    ModuleUI moduleUI = (ModuleUI) mouseEvent.getSource();
                    unselectedActiveModule();
                    selectedActiveModule(moduleUI);

                    Point newPosition = moduleUI.getLocation();
                    newPosition.translate(mouseEvent.getX() - mousePositionDown.x, mouseEvent.getY() - mousePositionDown.y);
                    moduleUI.setLocation(newPosition);
                    moduleUI.repaint();

                    for(ModuleConnectedPointUI connectedPointUI : currentShowPoints)
                        connectedPointUI.setVisible(false);
                }
            });
        } catch (Exception exception) {
            Logger.log(exception);
        }
    }

    private Point calculatePointPosition(ModuleConnectedPointUI connectedPointUI, ModuleUI moduleUI)
    {
        int offset = connectedPointUI.getSize().width / 2;
        return  new Point(
                (int) ((int) moduleUI.getLocation().getX() + connectedPointUI.getComponentOffset().getX() - offset),
                (int) ((int) moduleUI.getLocation().getY() + connectedPointUI.getComponentOffset().getY() - offset));
    }

    @Override
    public void update(Observable observable, Object arguments) {
        if (arguments.equals("relations")) relations();
    }

    private void relations() {

    }
}
