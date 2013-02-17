package jDistsim.application.designer.model;

import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractModel;

import java.util.*;

/**
 * Author: Jirka Pénzeš
 * Date: 6.10.12
 * Time: 21:38
 */
public class ToolboxModel extends AbstractModel {

    private HashMap<String, ToolboxModelItem> items;

    public ToolboxModel(AbstractFrame mainFrame) {
        super(mainFrame);
        this.items = new HashMap<>();
    }

    @Override
    public void initialize() {
    }

    public ToolboxModel add(String identifier, ToolboxModelItem toolboxModelItem) {
        items.put(identifier, toolboxModelItem);
        return this;
    }

    public Collection<ToolboxModelItem> getItems() {
        return items.values();
    }

    public ToolboxModelItem getItemByIdentifier(String identifier) {
        return items.get(identifier);
    }
}
