package jDistsim.application.designer.view;

import jDistsim.ui.panel.listener.LogTabListener;
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

    @Override
    protected InformationPanel layout() {
        informationPanel = new InformationPanel();
        return informationPanel;
    }
}
