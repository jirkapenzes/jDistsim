package jDistsim.application.designer.view;

import jDistsim.application.designer.model.PropertiesModel;
import jDistsim.ui.control.button.ImageButton;
import jDistsim.ui.panel.ModulesPanel;
import jDistsim.ui.panel.PropertiesPanel;
import jDistsim.ui.panel.listener.ModulesViewListener;
import jDistsim.ui.panel.listener.PropertiesViewListener;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractView;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 27.10.12
 * Time: 13:24
 */
public class PropertiesView extends AbstractView<JPanel> {

    private PropertiesModel model;
    private PropertiesPanel internalPanel1;
    private ModulesPanel internalPanel2;
    private ModulesViewListener modulesViewListener;
    private PropertiesViewListener propertiesViewListener;

    public PropertiesView(AbstractFrame mainFrame) {
        super(mainFrame);
    }

    public void setModel(PropertiesModel model) {
        this.model = model;
    }

    public void setModulesViewListener(ModulesViewListener modulesViewListener) {
        this.modulesViewListener = modulesViewListener;
    }

    public void setPropertiesViewListener(PropertiesViewListener propertiesViewListener) {
        this.propertiesViewListener = propertiesViewListener;
    }

    @Override
    protected JPanel layout() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        internalPanel1 = new PropertiesPanel(model.getTable());
        internalPanel1.setViewListener(propertiesViewListener);
        internalPanel2 = new ModulesPanel(model.getTree());
        internalPanel2.setViewListener(modulesViewListener);

        // internalPanel1.showNothing();
        contentPane.add(internalPanel1, BorderLayout.CENTER);
        contentPane.add(internalPanel2, BorderLayout.SOUTH);
        return contentPane;
    }

    public void renderTable() {
        internalPanel1.renderTable();
    }

    public ImageButton getTreeViewImageButton() {
        return internalPanel2.getTreeViewButton();
    }

    public ImageButton getListViewImageButton() {
        return internalPanel2.getListViewButton();
    }


    public ImageButton getPinnedButton() {
        return internalPanel1.getPinnedButton();
    }


    public void setNothingToModuleView(boolean nothingToModuleView) {
        if (nothingToModuleView)
            internalPanel2.showNothing();
        else
            internalPanel2.hideNothing();
    }

    public void setNothingToPropertyView(boolean nothingToPropertyView) {
        if (nothingToPropertyView)
            internalPanel1.showNothing();
        else
            internalPanel1.hideNothing();
    }
}