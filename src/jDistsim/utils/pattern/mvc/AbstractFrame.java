package jDistsim.utils.pattern.mvc;

import jDistsim.utils.ioc.IObjectContainer;
import jDistsim.utils.ioc.ObjectContainer;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 26.10.12
 * Time: 17:31
 */
public abstract class AbstractFrame {

    private IComponentFactory componentFactory;

    protected JFrame frame;
    protected final IObjectContainer<AbstractView<? extends JComponent>> views;
    protected final IObjectContainer<AbstractController> controllers;

    public AbstractFrame(IComponentFactory componentFactory) {
        this.componentFactory = componentFactory;
        this.views = new ObjectContainer<AbstractView<? extends JComponent>>();
        this.controllers = new ObjectContainer<AbstractController>();
        registerAllViews();
        registerAllControllers();
    }

    protected abstract void registerAllViews();

    protected abstract void registerAllControllers();

    protected abstract JFrame layout();

    public void show() {
        if (frame == null) frame = layout();
        componentFactory.showFrame(frame);
    }

    public <View extends AbstractView<? extends JComponent>> View getView(Class<View> viewClass) {
        return views.get(viewClass);
    }

    public <Controller extends AbstractController> Controller getController(Class<Controller> controllerClass) {
        return controllers.get(controllerClass);
    }
}
