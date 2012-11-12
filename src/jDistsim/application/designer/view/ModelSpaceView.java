package jDistsim.application.designer.view;

import jDistsim.ui.panel.workspace.ModelSpacePanel;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractView;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.IOException;

/**
 * Author: Jirka Pénzeš
 * Date: 27.10.12
 * Time: 23:40
 */
public class ModelSpaceView extends AbstractView<ModelSpacePanel> implements DropTargetListener {

    public ModelSpaceView(AbstractFrame mainFrame) {
        super(mainFrame);
        new DropTarget(getContentPane(), this);
    }

    @Override
    protected ModelSpacePanel layout() {
        return new ModelSpacePanel();
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        Logger.log();
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        Logger.log();
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        Logger.log();
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        Logger.log();
    }

    @Override
    public void drop(DropTargetDropEvent evt) {
        Logger.log();

        try {
            Transferable transferable = evt.getTransferable();
            if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                evt.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                String dragContents = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                evt.getDropTargetContext().dropComplete(true);
                Logger.log(dragContents);
            } else {
                evt.rejectDrop();
            }
        } catch (IOException e) {
            evt.rejectDrop();
        } catch (UnsupportedFlavorException e) {
            evt.rejectDrop();
        }
    }
}
