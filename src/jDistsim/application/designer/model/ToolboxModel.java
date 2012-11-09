package jDistsim.application.designer.model;

import jDistsim.utils.pattern.mvc.AbstractModel;

import java.util.*;

/**
 * Author: Jirka Pénzeš
 * Date: 6.10.12
 * Time: 21:38
 */
public class ToolboxModel extends AbstractModel {

    private HashMap<String, ToolboxModelItem> items;

    public ToolboxModel() {
        this.items = new HashMap<>();
    }

    public ToolboxModel addToolboxModelItem(ToolboxModelItem toolboxModelItem) {
        items.put(toolboxModelItem.getIdentifier(), toolboxModelItem);
        return this;
    }

    public Collection<ToolboxModelItem> getItems() {
        return items.values();
    }

    public ToolboxModelItem getItemByIdentifier(String identifier) {
        return items.get(identifier);
    }
}
