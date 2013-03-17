package jDistsim.application.designer.view;

import jDistsim.application.designer.model.InformationModel;
import jDistsim.ui.panel.listener.LogTabListener;
import jDistsim.ui.panel.listener.OutputTabListener;
import jDistsim.ui.panel.listener.RemoteModelsTabListener;
import jDistsim.ui.panel.workspace.InformationPanel;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractView;

/**
 * Author: Jirka Pénzeš
 * Date: 27.10.12
 * Time: 23:41
 */
public class InformationView extends AbstractView<InformationPanel> {

    private InformationPanel informationPanel;

    public InformationView(AbstractFrame mainFrame) {
        super(mainFrame);
    }

    public void setLogTabListener(LogTabListener logTabListener) {
        informationPanel.getLogTabPanel().setListener(logTabListener);
    }

    public void setOutputListener(OutputTabListener outputListener) {
        informationPanel.getOutputTabPanel().setListener(outputListener);
    }

    public void setRemoteModelsListener(RemoteModelsTabListener remoteModelsListener) {
        informationPanel.getRemoteModelsTabPanel().setListener(remoteModelsListener);
    }

    @Override
    protected InformationPanel layout() {
        InformationModel model = getMainFrame().getModel(InformationModel.class);
        informationPanel = new InformationPanel(model.getOutputPanelTextArea(), model.getEntitiesInfoTable(), model.getRemoteModelsTable(), model.getEnvironmentTable());
        return informationPanel;
    }

    public void renderEntitiesTable() {
        informationPanel.getEntitiesTabPanel().renderTable();
    }

    public void renderRemoteModelsTable() {
        informationPanel.getRemoteModelsTabPanel().renderTable();
    }
}
