package jDistsim.application.designer.controller;

import jDistsim.application.designer.common.Application;
import jDistsim.application.designer.model.ModelSpaceModel;
import jDistsim.application.designer.model.PropertiesModel;
import jDistsim.application.designer.view.PropertiesView;
import jDistsim.core.modules.Module;
import jDistsim.core.modules.ModuleConnectedPointUI;
import jDistsim.core.modules.ModuleUI;
import jDistsim.core.modules.common.ModuleProperty;
import jDistsim.ui.control.button.ImageButton;
import jDistsim.ui.panel.listener.ModulesViewListener;
import jDistsim.ui.panel.listener.PropertiesViewListener;
import jDistsim.utils.pattern.mvc.AbstractController;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.observer.IObserver;
import jDistsim.utils.pattern.observer.Observable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 8.2.13
 * Time: 22:16
 */
public class PropertiesController extends AbstractController<PropertiesModel> implements ModulesViewListener, PropertiesViewListener, IObserver {

    private enum ModuleView {Tree, List}

    private ImageButton currentSelectedModuleViewButton;
    private ModuleView currentSelectedView;
    private ModuleUI currentSelectedModule;
    private boolean pinnedProperties;

    public PropertiesController(AbstractFrame mainFrame, PropertiesModel model) {
        super(mainFrame, model);

        initializeView();
    }

    private void initializeView() {
        PropertiesView view = getMainFrame().getView(PropertiesView.class);
        view.setModel(getModel());
        view.setModulesViewListener(this);
        view.setPropertiesViewListener(this);
        view.make();
        currentSelectedView = ModuleView.Tree;
        pinnedProperties = true;

        if (currentSelectedView == ModuleView.Tree) {
            currentSelectedModuleViewButton = view.getTreeViewImageButton();
            view.getTreeViewImageButton().setActive(true);
        } else {
            currentSelectedModuleViewButton = view.getListViewImageButton();
            view.getListViewImageButton().setActive(true);
        }

        if (pinnedProperties)
            view.getPinnedButton().setActive(true);

        getModel().addObserver(this);
        rebuildModules();
        rebuildProperties();
    }

    private void expandTree() {
        JTree jTree = getModel().getTree();
        for (int index = 0; index < jTree.getRowCount(); index++) {
            jTree.expandRow(index);
        }
    }

    @Override
    public void onExpandButtonClick(MouseEvent mouseEvent, Object sender) {
        expandTree();
    }

    @Override
    public void onCollapseButtonClick(MouseEvent mouseEvent, Object sender) {
        JTree jTree = getModel().getTree();
        for (int index = jTree.getRowCount() - 1; index >= 1; index--) {
            jTree.collapseRow(index);
        }
    }

    @Override
    public void onTreeViewSelected(MouseEvent mouseEvent, Object sender) {
        switchModuleView((ImageButton) sender, ModuleView.Tree);
    }

    @Override
    public void onListViewSelected(MouseEvent mouseEvent, Object sender) {
        switchModuleView((ImageButton) sender, ModuleView.List);
    }

    @Override
    public void onPinButtonClick(MouseEvent mouseEvent, Object sender) {
        ImageButton imageButton = (ImageButton) sender;
        pinnedProperties = !imageButton.isActive();
        imageButton.setActive(!imageButton.isActive());
        rebuildProperties();
    }

    public void switchModuleView(ImageButton imageButton, ModuleView moduleView) {
        if (currentSelectedView == moduleView)
            return;

        if (currentSelectedModuleViewButton != null)
            currentSelectedModuleViewButton.setActive(false);

        imageButton.setActive(true);
        currentSelectedView = moduleView;
        currentSelectedModuleViewButton = imageButton;
        rebuildModules();
    }

    private void showModulesAsTreeView(ArrayList<ModuleUI> modules) {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(Application.global().getModelName());
        ArrayList<String> expand = new ArrayList<>();
        for (ModuleUI moduleUI : modules) {
            if (moduleUI.isCreateModule()) {
                expand.add(moduleUI.getIdentifier());

                DefaultMutableTreeNode mutableTreeNode = new DefaultMutableTreeNode(moduleUI.getIdentifier());
                for (ModuleConnectedPointUI connectedPointUI : moduleUI.getOutputPoints()) {
                    for (ModuleConnector moduleConnector : connectedPointUI.getDependencies().values()) {
                        if (!moduleConnector.getModuleA().getOutputPoints().isEmpty()) {
                            addNextTreeNode(mutableTreeNode, moduleConnector.getModuleB(), expand);
                        }
                    }
                }
                top.add(mutableTreeNode);
            }
        }

        for (ModuleUI moduleUI : modules) {
            if (!expand.contains(moduleUI.getIdentifier()))
                top.add(new DefaultMutableTreeNode(moduleUI.getIdentifier()));
        }

        getModel().getTree().setRootVisible(true);
        getModel().getTree().setModel(new DefaultTreeModel(top));
        expandTree();
    }

    private void showModulesAsListView(ArrayList<ModuleUI> modules) {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(Application.global().getModelName());
        for (ModuleUI moduleUI : modules) {
            top.add(new DefaultMutableTreeNode(moduleUI.getIdentifier()));
        }
        getModel().getTree().setRootVisible(false);
        getModel().getTree().setModel(new DefaultTreeModel(top));
    }

    private void addNextTreeNode(DefaultMutableTreeNode parent, ModuleUI moduleUI, ArrayList<String> expand) {
        expand.add(moduleUI.getIdentifier());
        DefaultMutableTreeNode mutableTreeNode = new DefaultMutableTreeNode(moduleUI.getIdentifier());
        for (ModuleConnectedPointUI connectedPointUI : moduleUI.getOutputPoints()) {
            for (ModuleConnector moduleConnector : connectedPointUI.getDependencies().values()) {
                addNextTreeNode(mutableTreeNode, moduleConnector.getModuleB(), expand);
            }
        }
        parent.add(mutableTreeNode);
    }

    @Override
    public void update(Observable observable, Object arguments) {
        if (arguments.equals("moduleSpace"))
            rebuildModules();

        if (arguments.equals("currentActiveModule"))
            rebuildProperties();

        if (observable instanceof Module) {
            rebuildProperties();
        }
    }

    private void rebuildProperties() {
        PropertiesView view = getMainFrame().getView(PropertiesView.class);
        ModuleUI moduleUI = getMainFrame().getModel(ModelSpaceModel.class).getCurrentActiveModule();
        if (moduleUI == null) {
            if (currentSelectedModule != null && pinnedProperties) {
                refreshTable(currentSelectedModule.getProperties());
            } else {
                view.setNothingToPropertyView(true);
                return;
            }
        } else {
            refreshTable(moduleUI.getProperties());
            if (currentSelectedModule != null)
                currentSelectedModule.getModule().removeObserver(this);

            currentSelectedModule = moduleUI;
            currentSelectedModule.getModule().addObserver(this);
        }
        view.setNothingToPropertyView(false);
        view.renderTable();
    }

    private void refreshTable(List<ModuleProperty> properties) {
        Object[][] data = new Object[properties.size()][2];
        for (int index = 0; index < properties.size(); index++) {
            ModuleProperty property = properties.get(index);

            //defaultTableModel.addRow(new Object[]{property.getText(), property.getValue()});
            data[index][0] = property.getText();
            data[index][1] = property.getValue();
        }
        getModel().getTable().setModel(new DefaultTableModel(data, new Object[]{"key", "value"}));
    }

    private void rebuildModules() {
        ArrayList<ModuleUI> modules = new ArrayList<>(getMainFrame().getModel(ModelSpaceModel.class).getModuleList().values());
        PropertiesView view = getMainFrame().getView(PropertiesView.class);
        if (modules.isEmpty()) {
            view.setNothingToModuleView(true);
            return;
        }

        view.setNothingToModuleView(false);
        if (currentSelectedView == ModuleView.Tree)
            showModulesAsTreeView(modules);
        else
            showModulesAsListView(modules);
    }
}
