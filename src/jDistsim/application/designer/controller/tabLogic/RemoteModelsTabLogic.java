package jDistsim.application.designer.controller.tabLogic;

import jDistsim.application.designer.common.Application;
import jDistsim.application.designer.controller.InformationController;
import jDistsim.application.designer.view.InformationView;
import jDistsim.core.simulation.distributed.DistributedModelDefinition;
import jDistsim.ui.dialog.DistributedModelDialog;
import jDistsim.ui.panel.listener.RemoteModelsTabListener;
import jDistsim.utils.logging.Logger;

import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Vector;

/**
 * Author: Jirka Pénzeš
 * Date: 2.3.13
 * Time: 17:00
 */
public class RemoteModelsTabLogic implements RemoteModelsTabListener {

    private InformationController controller;

    public RemoteModelsTabLogic(InformationController controller) {
        this.controller = controller;
        rebuildTableModel();
    }

    @Override
    public void onOpenAddDialogButtonClick(MouseEvent mouseEvent, Object sender) {
        DistributedModelDialog dialog = new DistributedModelDialog(controller.getMainFrame().getFrame());
        dialog.showDialog();
    }

    @Override
    public void onOpenEditDialogButtonClick(MouseEvent mouseEvent, Object sender) {

    }

    @Override
    public void onOpenRemoveDialogButtonClick(MouseEvent mouseEvent, Object sender) {
    }

    private void rebuildTableModel() {
        Collection<DistributedModelDefinition> distributedModels = Application.global().getDistributedModels().values();
        Vector<String> columns = makeColumns();
        Vector<Vector<String>> rows = new Vector<>();

        for (DistributedModelDefinition modelDefinition : distributedModels) {
            rows.addElement(makeRow(modelDefinition));
        }

        controller.getModel().getRemoteModelsTable().setModel(new DefaultTableModel(rows, columns));
        InformationView view = controller.getMainFrame().getView(InformationView.class);
        view.renderRemoteModelsTable();
        Logger.log("Rebuild remote models table");

    }

    private Vector<String> makeRow(DistributedModelDefinition modelDefinition) {
        Vector<String> row = new Vector<>();
        row.addElement(modelDefinition.getModelName());
        row.addElement(modelDefinition.getRmiModelName());
        row.addElement(modelDefinition.getAddress());
        row.addElement(String.valueOf(modelDefinition.getPort()));
        row.addElement(String.valueOf(modelDefinition.isLookahead()));
        return row;
    }

    private Vector<String> makeColumns() {
        Vector columns = new Vector();
        columns.addElement("Model name");
        columns.addElement("RMI name");
        columns.addElement("Remote address");
        columns.addElement("Port");
        columns.addElement("Lookahead");
        return columns;
    }
}
