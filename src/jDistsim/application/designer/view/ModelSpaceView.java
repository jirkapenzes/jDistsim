package jDistsim.application.designer.view;

import jDistsim.SampleControl;
import jDistsim.ui.panel.workspace.ModelSpacePanel;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractView;

import java.awt.*;
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
public class ModelSpaceView extends AbstractView<ModelSpacePanel> {

    public ModelSpaceView(AbstractFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    protected ModelSpacePanel layout() {
        return new ModelSpacePanel();
    }
}



