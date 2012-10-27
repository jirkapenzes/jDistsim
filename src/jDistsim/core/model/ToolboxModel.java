package jDistsim.core.model;

import jDistsim.core.module.IEventToolbarModule;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 6.10.12
 * Time: 21:38
 */
public class ToolboxModel {

    private List<IEventToolbarModule> modules;

    public ToolboxModel() {
        this.modules = new ArrayList<IEventToolbarModule>();
    }

    public ToolboxModel addEventToolbarModule(IEventToolbarModule eventToolbarModule) {
        modules.add(eventToolbarModule);
        return this;
    }

    public List<IEventToolbarModule> getModules() {
        return modules;
    }
}
