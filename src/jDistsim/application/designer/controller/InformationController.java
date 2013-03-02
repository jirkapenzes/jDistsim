package jDistsim.application.designer.controller;

import jDistsim.application.designer.MainFrame;
import jDistsim.application.designer.controller.tabLogic.EntitiesInfoTabLogic;
import jDistsim.application.designer.controller.tabLogic.LogTabLogic;
import jDistsim.application.designer.controller.tabLogic.OutputTabLogic;
import jDistsim.application.designer.model.InformationModel;
import jDistsim.application.designer.view.InformationView;
import jDistsim.core.simulation.simulator.Writer;
import jDistsim.ui.panel.listener.LogTabListener;
import jDistsim.utils.pattern.mvc.AbstractController;

/**
 * Author: Jirka Pénzeš
 * Date: 15.2.13
 * Time: 17:58
 */
public class InformationController extends AbstractController<InformationModel> {

    private InformationView view;
    private LogTabListener logTabLogic;
    private OutputTabLogic outputTabLogic;
    private EntitiesInfoTabLogic entitiesInfoTabLogic;

    public InformationController(MainFrame mainFrame, InformationModel model) {
        super(mainFrame, model);
        view = getMainFrame().getView(InformationView.class);

        initialize();
    }

    private void initialize() {
        logTabLogic = new LogTabLogic(this);
        outputTabLogic = new OutputTabLogic(this);
        entitiesInfoTabLogic = new EntitiesInfoTabLogic(this);

        view.setLogTabListener(logTabLogic);
        view.setOutputListener(outputTabLogic);
    }

    public Writer makeSimulatorWriter() {
        return outputTabLogic;
    }
}
