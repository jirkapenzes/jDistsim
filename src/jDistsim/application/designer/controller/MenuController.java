package jDistsim.application.designer.controller;

import jDistsim.application.designer.model.MenuModel;
import jDistsim.application.designer.view.InformationView;
import jDistsim.application.designer.view.PropertiesView;
import jDistsim.application.designer.view.ToolboxView;
import jDistsim.ui.IMenuListener;
import jDistsim.utils.pattern.mvc.AbstractController;
import jDistsim.utils.pattern.mvc.AbstractFrame;

/**
 * Author: Jirka Pénzeš
 * Date: 10.3.13
 * Time: 12:58
 */
public class MenuController extends AbstractController<MenuModel> implements IMenuListener {

    public MenuController(AbstractFrame mainFrame, MenuModel model) {
        super(mainFrame, model);
    }

    @Override
    public void viewToolboxOnClick(boolean selected) {
        ToolboxView toolboxView = getMainFrame().getView(ToolboxView.class);
        toolboxView.getContentPane().setVisible(selected);
    }

    @Override
    public void viewPropertiesClick(boolean selected) {
        PropertiesView view = getMainFrame().getView(PropertiesView.class);
        view.getInternalPanel1().setVisible(selected);
    }

    @Override
    public void viewModulesNavigatorOnClick(boolean selected) {
        PropertiesView view = getMainFrame().getView(PropertiesView.class);
        view.getInternalPanel2().setVisible(selected);
    }

    @Override
    public void viewModelInformationOnClick(boolean selected) {
        InformationView view = getMainFrame().getView(InformationView.class);
        view.getContentPane().setVisible(selected);
    }
}
