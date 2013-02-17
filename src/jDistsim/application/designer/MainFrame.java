package jDistsim.application.designer;

import jDistsim.application.designer.controller.*;
import jDistsim.application.designer.model.*;
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
        Logger.log("Initialize GUI designer MainFrame");
    }

    @Override
    protected void registerAllModels() {
        Logger.log("Register all models: start");
        models.bind(StatusBarModel.class, new StatusBarModel(this));
        models.bind(ToolboxModel.class, new ToolboxModel(this));
        models.bind(ModelSpaceModel.class, new ModelSpaceModel(this));
        models.bind(PropertiesModel.class, new PropertiesModel(this));
        models.bind(ToolbarModel.class, new ToolbarModel(this));
        models.bind(InformationModel.class, new InformationModel(this));
        Logger.log("Register all models: done");
    }

    @Override
    protected void registerAllViews() {
        Logger.log("Register all views: start");
        views.bind(DesignerView.class, new DesignerView(this));
        views.bind(PropertiesView.class, new PropertiesView(this));
        views.bind(ToolbarView.class, new ToolbarView(this));
        views.bind(StatusBarView.class, new StatusBarView(this));
        views.bind(ToolboxView.class, new ToolboxView(this));
        views.bind(WorkSpaceView.class, new WorkSpaceView(this));
        views.bind(ModelSpaceView.class, new ModelSpaceView(this));
        views.bind(InformationView.class, new InformationView(this));
        Logger.log("Register all views: done");
    }

    @Override
    protected void registerAllControllers() {
        Logger.log("Register all controllers: start");
        controllers.bind(StatusBarController.class, new StatusBarController(this, getModel(StatusBarModel.class)));
        controllers.bind(ToolboxController.class, new ToolboxController(this, getModel(ToolboxModel.class)));
        controllers.bind(ModelSpaceController.class, new ModelSpaceController(this, getModel(ModelSpaceModel.class)));
        controllers.bind(ToolbarController.class, new ToolbarController(this, getModel(ToolbarModel.class)));
        controllers.bind(PropertiesController.class, new PropertiesController(this, getModel(PropertiesModel.class)));
        controllers.bind(InformationController.class, new InformationController(this, getModel(InformationModel.class)));
        Logger.log("Register all controllers: done");
    }

    @Override
    protected JFrame layout() {
        Logger.log("Component factory make main frame");
        return componentFactory.frame(TextResources.APPLICATION_NAME, getView(DesignerView.class).getContentPane(), new MenuBar());
    }
}
