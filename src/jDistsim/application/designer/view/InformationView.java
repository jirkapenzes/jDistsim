package jDistsim.application.designer.view;

import jDistsim.ui.panel.InternalPanel;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractView;

/**
 * Author: Jirka Pénzeš
 * Date: 27.10.12
 * Time: 13:24
 */
public class InformationView extends AbstractView<InternalPanel> {

    public InformationView(AbstractFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    protected InternalPanel layout() {
        return new InternalPanel("Information");
    }
}