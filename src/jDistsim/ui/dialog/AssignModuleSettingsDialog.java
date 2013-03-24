package jDistsim.ui.dialog;

import jDistsim.application.designer.common.DialogBuilder;
import jDistsim.core.simulation.modules.lib.assign.Assign;
import jDistsim.core.simulation.simulator.entity.Attribute;
import jDistsim.core.simulation.simulator.entity.AttributeCollection;
import jDistsim.ui.renderer.ValueTableCellHeaderRenderer;
import jDistsim.ui.renderer.ValueTableCellRenderer;
import jDistsim.ui.skins.ScrollBarUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Author: Jirka Pénzeš
 * Date: 3.3.13
 * Time: 13:48
 */
public class AssignModuleSettingsDialog extends BaseModuleSettingsDialog<Assign> {

    private JTable table;
    private JScrollPane scrollPane;
    private AttributeCollection attributes;
    private DialogBuilder dialogBuilder;

    private JButton editButton;
    private JButton removeButton;

    public AssignModuleSettingsDialog(JFrame parent, Assign module) {
        super(parent, module);
        attributes = new AttributeCollection();
        dialogBuilder = new DialogBuilder(parent);
    }

    @Override
    protected void initializeUI() {
        for (Attribute attribute : module.getSettings().getAttributes()) {
            attributes.set(attribute);
        }
        rebuildTable();
    }

    private void rebuildTable() {
        Vector<String> columns = makeColumns();
        Vector<Vector<String>> rows = new Vector<>();
        for (Attribute attribute : attributes) {
            Vector<String> row = new Vector<>();
            row.addElement(attribute.getName());
            row.addElement(attribute.getValue());
            rows.addElement(row);
        }
        table.setModel(new DefaultTableModel(rows, columns));

        for (int index = 0; index < table.getColumnCount(); index++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(index);
            tableColumn.setCellRenderer(new ValueTableCellRenderer());
        }
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(true);
        tableHeader.setDefaultRenderer(new ValueTableCellHeaderRenderer());

        if (rows.isEmpty()) {
            editButton.setEnabled(false);
            removeButton.setEnabled(false);
        } else {
            editButton.setEnabled(true);
            removeButton.setEnabled(true);
        }
    }

    @Override
    protected void buildWindowBody() {
        setSize(290, 260);

        table = new JTable();
        table.setEnabled(false);
        table.setFocusable(true);
        table.setRowSelectionAllowed(false);
        table.setGridColor(new Color(156, 156, 156));
        table.setOpaque(false);
        table.setShowGrid(true);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(true);
        tableHeader.setDefaultRenderer(new ValueTableCellHeaderRenderer());

        scrollPane = new JScrollPane(table);
        scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
        scrollPane.setPreferredSize(new Dimension(200, 135));

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        constraints.insets = new Insets(0, 0, 4, 5);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(getComponentFactory().makeLabel("Define entity attributes assignments"));

        constraints.gridwidth = 1;
        constraints.gridheight = 3;
        build(scrollPane);

        constraints.insets = new Insets(0, 0, 3, 0);
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;

        JButton addButton = getComponentFactory().makeButton("add");
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onAddAttributeMouseClicked();
            }
        });
        editButton = getComponentFactory().makeButton("edit");
        editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onEditAttributeMouseClicked();
            }
        });
        removeButton = getComponentFactory().makeButton("remove");
        removeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onRemoveAttributeMouseClicked();
            }
        });

        build(addButton);
        constraints.gridy = 2;
        build(editButton);
        constraints.gridy = 3;
        build(removeButton);
    }

    private void onAddAttributeMouseClicked() {
        AttributeSettingsDialog dialog = new AttributeSettingsDialog(this, "Added new assign attribute");
        dialog.showDialog();
        if (checkExist(dialog)) {
            attributes.set(dialog.getAttribute());
            rebuildTable();
        }
    }

    private AttributeSettingsDialog makeDialogFor(Attribute attribute) {
        return new AttributeSettingsDialog(this, "Added new assign attribute", attribute);
    }

    private void onEditAttributeMouseClicked() {
        AttributeSelectDialog selectDialog = new AttributeSelectDialog(this, "Select attribute to edit", attributes);
        selectDialog.showDialog();
        if (selectDialog.getDialogResult() == Result.OK) {
            Attribute attribute = selectDialog.getAttribute();
            if (attribute == null) {
                dialogBuilder.buildErrorDialog("There was no attribute selected");
                onEditAttributeMouseClicked();
            } else {
                String origin = attribute.getName();
                AttributeSettingsDialog settingsDialog = new AttributeSettingsDialog(this, "Edit attribute", attribute);
                settingsDialog.showDialog();
                if (settingsDialog.getDialogResult() == Result.OK) {
                    attributes.remove(new Attribute(origin));
                    attributes.set(attribute);
                    rebuildTable();
                }
            }
        }
    }

    private void onRemoveAttributeMouseClicked() {
        AttributeSelectDialog selectDialog = new AttributeSelectDialog(this, "Select attribute to remove", attributes);
        selectDialog.showDialog();
        if (selectDialog.getDialogResult() == Result.OK) {
            Attribute attribute = selectDialog.getAttribute();
            if (attribute == null) {
                dialogBuilder.buildErrorDialog("There was no attribute selected");
                onRemoveAttributeMouseClicked();
            } else {
                if (attributes.contains(attribute)) {
                    attributes.remove(attribute);
                    rebuildTable();
                }
            }
        }
    }

    private boolean checkExist(AttributeSettingsDialog dialog) {
        if (dialog.getDialogResult() == Result.OK) {
            Attribute attribute = dialog.getAttribute();
            if (attributes.contains(attribute)) {
                dialogBuilder.buildErrorDialog("This attribute already exists");
                AttributeSettingsDialog newDialog = makeDialogFor(attribute);
                newDialog.showDialog();
                checkExist(newDialog);
            }
            return true;
        }
        return false;
    }

    private Vector<String> makeColumns() {
        Vector<String> columns = new Vector<>();
        columns.addElement("Attribute name");
        columns.addElement("Attribute value");
        return columns;
    }

    @Override
    protected boolean doLogic() {
        module.getSettings().getAttributes().clear();
        for (Attribute attribute : attributes)
            module.getSettings().getAttributes().set(attribute);

        return true;
    }
}
