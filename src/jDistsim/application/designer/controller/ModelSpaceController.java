package jDistsim.application.designer.controller;

import jDistsim.application.designer.model.ModelSpaceModel;
import jDistsim.application.designer.view.ModelSpaceView;
import jDistsim.core.modules.IModuleFactory;
import jDistsim.core.modules.Module;
import jDistsim.core.modules.ModuleUI;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.pattern.mvc.AbstractController;
import jDistsim.utils.pattern.mvc.AbstractFrame;

import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 15:01
 */
public class ModelSpaceController extends AbstractController<ModelSpaceModel> implements DropTargetListener {

    private ModelSpaceView view;
    private Module currentDragModule;
    private Module currentActiveModule;
    private HashMap<String, Module> moduleList;

    public ModelSpaceController(AbstractFrame mainFrame, ModelSpaceModel model) {
        super(mainFrame, model);
        view = getMainFrame().getView(ModelSpaceView.class);
        moduleList = new HashMap<>();

        new DropTarget(view.getContentPane(), this);

        view.getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                unselectedActiveModule();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseDragged(MouseEvent mouseEvent) {

            }
        });
    }

    private void unselectedActiveModule() {
        if (currentActiveModule != null)
            currentActiveModule.getUI().setActive(false);
    }

    @Override
    public void dragEnter(DropTargetDragEvent dropTargetDragEvent) {
        Transferable transferable = dropTargetDragEvent.getTransferable();
        try {
            IModuleFactory moduleFactory = (IModuleFactory) transferable.getTransferData(transferable.getTransferDataFlavors()[0]);
            currentDragModule = moduleFactory.create();
            currentDragModule.getUI().setLocation(calculateDragLocation(dropTargetDragEvent.getLocation(), currentDragModule.getUI().getSize()));
            view.getContentPane().add(currentDragModule.getUI());
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
        Point location = calculateDragLocation(dropTargetDragEvent.getLocation(), currentDragModule.getUI().getSize());
        currentDragModule.getUI().setLocation(location);
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dropTargetDragEvent) {
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        view.getContentPane().remove(currentDragModule.getUI());
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
            currentDragModule.getUI().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    ModuleUI moduleUI = (ModuleUI) mouseEvent.getSource();
                    Module module = moduleList.get(moduleUI.getIdentifier());
                    if (module == currentActiveModule && module.getUI().getActive()) {
                        moduleUI.setActive(false);
                        return;
                    }

                    unselectedActiveModule();
                    moduleUI.setActive(true);
                    currentActiveModule = moduleList.get(moduleUI.getIdentifier());
                }
            });
        } catch (Exception exception) {
            Logger.log(exception);
        }
    }
}
