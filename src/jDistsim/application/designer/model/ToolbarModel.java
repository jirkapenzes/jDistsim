package jDistsim.application.designer.model;

import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractModel;

/**
 * Author: Jirka Pénzeš
 * Date: 3.12.12
 * Time: 23:37
 */
public class ToolbarModel extends AbstractModel {

    private boolean relations;

    public ToolbarModel(AbstractFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    public void initialize() {
    }

}
