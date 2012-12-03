package jDistsim.core.modules;

import jDistsim.ui.module.ModuleView;
import jDistsim.utils.pattern.observer.Observable;


/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 12:16
 */
public class Module extends Observable {

    private String identifier;
    private ModuleView view;

    public Module(ModuleView view, ModuleConfiguration moduleConfiguration) {
        this.identifier = moduleConfiguration.getBaseIdentifier();
        this.view = view;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
        notifyObservers("identifier");
    }

    public String getIdentifier() {
        return identifier;
    }

    public ModuleView getView() {
        return view;
    }
}
