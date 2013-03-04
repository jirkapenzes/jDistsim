package jDistsim.application.designer.model;

import jDistsim.application.designer.controller.modelSpaceFeature.util.ConnectorLine;
import jDistsim.core.simulation.modules.ui.ModuleUI;
import jDistsim.utils.collection.observable.ObservableHashMap;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractModel;
import jDistsim.utils.pattern.observer.IObserver;
import jDistsim.utils.pattern.observer.Observable;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 15:02
 */
public class ModelSpaceModel extends AbstractModel implements IObserver {

    private ModuleUI currentActiveModule;
    private ModuleUI currentDragModule;
    private ConnectorLine currentSelectedLine;
    private ObservableHashMap<String, ModuleUI> moduleList;

    public ModelSpaceModel(AbstractFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    public void initialize() {
        moduleList = new ObservableHashMap<>();
        moduleList.addObserver(this);
    }

    public ModuleUI getCurrentActiveModule() {
        return currentActiveModule;
    }

    public void setCurrentActiveModule(ModuleUI currentActiveModule) {
        this.currentActiveModule = currentActiveModule;
        setChanged();
    }

    public ConnectorLine getCurrentSelectedLine() {
        return currentSelectedLine;
    }

    public void setCurrentSelectedLine(ConnectorLine currentSelectedLine) {
        this.currentSelectedLine = currentSelectedLine;
        setChanged();
    }

    public ModuleUI getCurrentDragModule() {
        return currentDragModule;
    }

    public void setCurrentDragModule(ModuleUI currentDragModule) {
        this.currentDragModule = currentDragModule;
    }

    public ObservableHashMap<String, ModuleUI> getModuleList() {
        return moduleList;
    }

    @Override
    public void update(Observable observable, Object arguments) {
        setChanged();
        notifyObservers("moduleList");
    }
}
