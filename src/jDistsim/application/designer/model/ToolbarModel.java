package jDistsim.application.designer.model;

import jDistsim.utils.pattern.mvc.AbstractModel;

/**
 * Author: Jirka Pénzeš
 * Date: 3.12.12
 * Time: 23:37
 */
public class ToolbarModel extends AbstractModel {

    private boolean relations;

    public boolean isRelations() {
        return relations;
    }

    public void setRelations(boolean relations) {
        this.relations = relations;
        notifyObservers("relations");
    }
}
