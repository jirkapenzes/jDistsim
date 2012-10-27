package jDistsim.application.designer;

import jDistsim.application.designer.controller.StatusBarController;
import jDistsim.application.designer.model.StatusBarModel;
import jDistsim.application.designer.view.*;
import jDistsim.ui.MenuBar;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.IComponentFactory;
import jDistsim.utils.resource.TextResources;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 27.10.12
 * Time: 13:05
 */
public class MainFrame extends AbstractFrame {

    public MainFrame(IComponentFactory componentFactory) {
        super(componentFactory);
        Logger.log("Initialize GUI designer");
    }

    @Override
    protected void registerAllViews() {
        Logger.log("Register all views");
        views.bind(DesignerView.class, new DesignerView(this));
        views.bind(PropertiesView.class, new PropertiesView(this));
        views.bind(ToolbarView.class, new ToolbarView(this));
        views.bind(StatusBarView.class, new StatusBarView(this));
        views.bind(ToolboxView.class, new ToolboxView(this));
        views.bind(WorkSpaceView.class, new WorkSpaceView(this));
        views.bind(ModelSpaceView.class, new ModelSpaceView(this));
        views.bind(InformationView.class, new InformationView(this));
    }

    @Override
    protected void registerAllControllers() {
        Logger.log("Register all controllers");
        controllers.bind(StatusBarController.class, new StatusBarController(this, new StatusBarModel()));
    }

    @Override
    protected JFrame layout() {
        return componentFactory.frame(TextResources.APPLICATION_NAME, getView(DesignerView.class).getContentPane(), new MenuBar());
    }
}
