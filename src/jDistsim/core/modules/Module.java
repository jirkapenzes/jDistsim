package jDistsim.core.modules;

import jDistsim.ui.module.ModuleView;
import jDistsim.utils.pattern.observer.Observable;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 12:16
 */
public class Module extends Observable {

    private String identifier;
    private final ModuleView view;
    private final List<ModuleConnectedPoint> inputConnectedPoints;
    private final List<ModuleConnectedPoint> outputConnectedPoints;

    public Module(ModuleView view, ModuleConfiguration moduleConfiguration) {
        this.identifier = moduleConfiguration.getBaseIdentifier();
        this.view = view;
        inputConnectedPoints = new ArrayList<>();
        outputConnectedPoints = new ArrayList<>();
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

    public List<ModuleConnectedPoint> getInputConnectedPoints() {
        return inputConnectedPoints;
    }

    public List<ModuleConnectedPoint> getOutputConnectedPoints() {
        return outputConnectedPoints;
    }


    public boolean canInputConnected() {
        for (ModuleConnectedPoint moduleConnectedPoint : inputConnectedPoints) {
            if (moduleConnectedPoint.canBeConnected())
                return true;
        }
        return false;
    }

    public boolean canOutputConnected() {
        for (ModuleConnectedPoint moduleConnectedPoint : outputConnectedPoints) {
            if (moduleConnectedPoint.canBeConnected())
                return true;
        }
        return false;
    }
}
