package jDistsim.application.designer.model;

import jDistsim.core.modules.ModuleUI;
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
    private boolean relations;

    public ModelSpaceModel(ToolbarModel toolbarModel) {
        toolbarModel.addObserver(this);
    }

    public boolean isRelations() {
        return relations;
    }

    public void setRelations(boolean relations) {
        this.relations = relations;
        notifyObservers("relations");
    }

    public ModuleUI getCurrentActiveModule() {
        return currentActiveModule;
    }

    public void setCurrentActiveModule(ModuleUI currentActiveModule) {
        this.currentActiveModule = currentActiveModule;
        notifyObservers("currentActiveModule");
    }

    @Override
    public void update(Observable observable, Object arguments) {
        if (observable instanceof ToolbarModel) {
            ToolbarModel toolbarModel = (ToolbarModel) observable;
            setRelations(toolbarModel.isRelations());
        }
    }
}
