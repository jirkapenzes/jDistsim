package jDistsim.utils.pattern.mvc;

import jDistsim.utils.ioc.IObjectContainer;
import jDistsim.utils.ioc.ObjectContainer;

import javax.swing.*;
import java.util.Map;

/**
 * Author: Jirka Pénzeš
 * Date: 26.10.12
 * Time: 17:31
 */
public abstract class AbstractFrame {

    protected JFrame frame;
    protected final IObjectContainer<Class> views;
    protected final IObjectContainer<Class> controllers;
    protected final IObjectContainer<Class> models;
    protected IComponentFactory componentFactory;

    public AbstractFrame(IComponentFactory componentFactory) {
        this.componentFactory = componentFactory;
        this.views = new ObjectContainer<>();
        this.controllers = new ObjectContainer<>();
        this.models = new ObjectContainer<>();

        registerAllModels();
        initializeModels();

        registerAllViews();
        registerAllControllers();
    }

    protected abstract void registerAllModels();

    protected abstract void registerAllViews();

    protected abstract void registerAllControllers();

    private void initializeModels() {
        for (Map.Entry<Class, Object> entry : models.entrySet()) {
            AbstractModel model = (AbstractModel) entry.getValue();
            model.initialize();
        }
    }

    protected abstract JFrame layout();

    public void show() {
        if (frame == null) frame = layout();
        componentFactory.showFrame(frame);
    }

    public JFrame getFrame() {
        return frame;
    }

    public <View extends AbstractView<? extends JComponent>> View getView(Class<View> viewClass) {
        return views.get(viewClass);
    }

    public <Controller extends AbstractController> Controller getController(Class<Controller> controllerClass) {
        return controllers.get(controllerClass);
    }

    public <Model extends AbstractModel> Model getModel(Class<Model> modelClass) {
        return models.get(modelClass);
    }
}
