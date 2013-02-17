package jDistsim.application.designer.model;

import jDistsim.application.designer.MainFrame;
import jDistsim.utils.pattern.mvc.AbstractModel;

/**
 * Author: Jirka Pénzeš
 * Date: 15.2.13
 * Time: 17:57
 */
public class InformationModel extends AbstractModel {

    private boolean logPanelScrollToEnd;
    private boolean logPanelWordWrap;

    public InformationModel(MainFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    public void initialize() {
        logPanelScrollToEnd = true;
    }

    public boolean isLogPanelScrollToEnd() {
        return logPanelScrollToEnd;
    }

    public void setLogPanelScrollToEnd(boolean logPanelScrollToEnd) {
        this.logPanelScrollToEnd = logPanelScrollToEnd;
    }

    public void setLogPanelWordWrap(boolean logPanelWordWrap) {
        this.logPanelWordWrap = logPanelWordWrap;
    }

    public boolean isLogPanelWordWrap() {
        return logPanelWordWrap;
    }
}
