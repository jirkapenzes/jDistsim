package jDistsim.ui.dialog;

import jDistsim.core.simulation.modules.lib.assign.Assign;
import jDistsim.core.simulation.simulator.entity.Attribute;
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

    public AssignModuleSettingsDialog(JFrame parent, Assign module) {
        super(parent, module);

    }

    @Override
    protected void initializeUI() {
        Vector<String> columns = makeColumns();
        Vector<Vector<String>> rows = new Vector<>();
        for (Attribute attribute : module.getAttributes()) {
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

        JScrollPane scrollPane = new JScrollPane(table);
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
        JButton editButton = getComponentFactory().makeButton("edit");
        editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onEditAttributeMouseClicked();
            }
        });
        JButton removeButton = getComponentFactory().makeButton("remove");
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
    }

    private void onEditAttributeMouseClicked() {
    }

    private void onRemoveAttributeMouseClicked() {
    }

    private Vector<String> makeColumns() {
        Vector<String> columns = new Vector<>();
        columns.addElement("Attribute name");
        columns.addElement("Attribute value");
        return columns;
    }

    @Override
    protected boolean okButtonLogic() {
        return true;
    }
}
