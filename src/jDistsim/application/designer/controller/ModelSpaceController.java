package jDistsim.application.designer.controller;

import jDistsim.application.designer.model.ModelSpaceModel;
import jDistsim.application.designer.view.ModelSpaceView;
import jDistsim.core.modules.IModuleFactory;
import jDistsim.core.modules.Module;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.pattern.mvc.AbstractController;
import jDistsim.utils.pattern.mvc.AbstractFrame;

import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 15:01
 */
public class ModelSpaceController extends AbstractController<ModelSpaceModel> implements DropTargetListener {

    private ModelSpaceView view;
    private Module currentDragModule;

    public ModelSpaceController(AbstractFrame mainFrame, ModelSpaceModel model) {
        super(mainFrame, model);
        view = getMainFrame().getView(ModelSpaceView.class);

        new DropTarget(view.getContentPane(), this);
    }

    @Override
    public void dragEnter(DropTargetDragEvent dropTargetDragEvent) {
        Transferable transferable = dropTargetDragEvent.getTransferable();
        try {
            IModuleFactory moduleFactory = (IModuleFactory) transferable.getTransferData(transferable.getTransferDataFlavors()[0]);
            currentDragModule = moduleFactory.create();
            currentDragModule.getUI().getView().getContentPane().setLocation(calculateDragLocation(dropTargetDragEvent.getLocation(), currentDragModule.getUI().getView().getContentPane().getSize()));
            view.getContentPane().add(currentDragModule.getUI().getView().getContentPane());
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
        Point location = calculateDragLocation(dropTargetDragEvent.getLocation(), currentDragModule.getUI().getView().getContentPane().getSize());
        currentDragModule.getUI().getView().getContentPane().setLocation(location);
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dropTargetDragEvent) {
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        view.getContentPane().remove(currentDragModule.getUI().getView().getContentPane());
        view.getContentPane().repaint();
        currentDragModule = null;
    }

    @Override
    public void drop(DropTargetDropEvent dropTargetDropEvent) {
        Logger.log();
        /*
        try {
            //Transferable transferable = dropTargetDropEvent.getTransferable();
            //SampleControl sampleControl = (SampleControl) transferable.getTransferData(transferable.getTransferDataFlavors()[0]);
            //view.getContentPane().add(sampleControl);

            if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                dropTargetDropEvent.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                String dragContents = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                dropTargetDropEvent.getDropTargetContext().dropComplete(true);
                Logger.log(dragContents);
                SampleControl control = new SampleControl(Color.green);
                control.setLocation(dropTargetDropEvent.getLocation());
                view.getContentPane().add(control);
                view.getContentPane().repaint();
                Logger.log(dropTargetDropEvent.getLocation());


            } else {
                dropTargetDropEvent.rejectDrop();
            }
        } catch (Exception e) {
            dropTargetDropEvent.rejectDrop();
        }
        */
    }
}
