package jDistsim.utils.pattern.mvc;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Jirka Pénzeš
 * Date: 26.10.12
 * Time: 17:31
 */
public abstract class AbstractFrame {

    private IComponentFactory componentFactory;

    protected JFrame frame;
    protected final Map<Class<? extends AbstractView<? extends JComponent>>, AbstractView<? extends JComponent>> views;
    protected final Map<Class<? extends AbstractController>, AbstractController> controllers;

    public AbstractFrame(IComponentFactory componentFactory) {
        this.componentFactory = componentFactory;
        this.views = new HashMap<Class<? extends AbstractView<? extends JComponent>>, AbstractView<? extends JComponent>>();
        this.controllers = new HashMap<Class<? extends AbstractController>, AbstractController>();
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

    public <V extends AbstractView<? extends JComponent>> V getView(Class<V> viewClass) {
        return (V) views.get(viewClass);
    }

    public <C extends AbstractController> C getController(Class<C> controllerClass) {
        return (C) controllers.get(controllerClass);
    }
}
