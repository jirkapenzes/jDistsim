package jDistsim.application.designer.model;

import jDistsim.utils.pattern.mvc.AbstractModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 6.10.12
 * Time: 21:38
 */
public class ToolboxModel extends AbstractModel {

    private List<ToolboxModelItem> items;

    public ToolboxModel() {
        this.items = new ArrayList<ToolboxModelItem>();
    }

    public ToolboxModel addToolboxModelItem(ToolboxModelItem toolboxModelItem) {
        items.add(toolboxModelItem);
        return this;
    }

    public List<ToolboxModelItem> getItems() {
        return items;
    }
}
