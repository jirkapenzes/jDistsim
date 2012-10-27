package jDistsim.application.designer.view;

import jDistsim.ui.panel.workspace.InformationPanel;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractView;

/**
 * Author: Jirka Pénzeš
 * Date: 27.10.12
 * Time: 23:41
 */
public class InformationView extends AbstractView<InformationPanel>{

    public InformationView(AbstractFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    protected InformationPanel layout() {
        return new InformationPanel();
    }
}
