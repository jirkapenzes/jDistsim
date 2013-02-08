package jDistsim.ui.panel;

import jDistsim.application.designer.common.UIConfiguration;
import jDistsim.ui.control.MenuSeparator;
import jDistsim.ui.control.button.ImageButton;
import jDistsim.utils.resource.Resources;
import jDistsim.utils.ui.control.IconBackgroundColorHoverStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 5.2.13
 * Time: 0:19
 */
public class PropertiesPanel extends InternalPanel {

    private JTable table;
    final Object rows[][] = {
            {"identifier", "delay_1"},
            {"input max capacity", Integer.MAX_VALUE},
            {"occupied input", 2},
            {"input max capacity", Integer.MAX_VALUE},
            {"output max capacity", 1},
            {"occupied output", "1"},
            {"distributed", "false"},
            {"correct", "true"},
            {"location x", "54"},
            {"location y", "357"},
            {"delay time", "20"}
    };
    final Object headers[] = {"Property name", "Value"};

    public PropertiesPanel() {
        super("Properties");

        showNothing();
        setFooterBorderLine(true);
        setBackground(Color.white);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBorder(null);
        contentPane.setBackground(Color.white);

        initializeControlPanel(contentPane);
        initializeTable(contentPane);
        add(contentPane, BorderLayout.CENTER);
    }

    private void initializeControlPanel(JPanel contentPane) {
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new Color(237, 237, 237));
        controlPanel.setPreferredSize(new Dimension(getWidth(), 26));
        controlPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(156, 156, 156)));
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));

        IconBackgroundColorHoverStyle hoverStyle = new IconBackgroundColorHoverStyle();
        int padding = 3;

        controlPanel.add(new ImageButton(Resources.getImage("system/panels/pp_controls_edit.png"), hoverStyle, new Dimension(16, 16), padding));
        controlPanel.add(new ImageButton(Resources.getImage("system/panels/pp_controls_depen.png"), hoverStyle, new Dimension(16, 16), padding));
        controlPanel.add(new ImageButton(Resources.getImage("system/panels/pp_controls_help.png"), hoverStyle, new Dimension(16, 16), padding));
        controlPanel.add(new MenuSeparator(14));
        controlPanel.add(new ImageButton(Resources.getImage("system/panels/pp_controls_sort_az.png"), hoverStyle, new Dimension(16, 16), padding));
        controlPanel.add(new ImageButton(Resources.getImage("system/panels/pp_controls_sort_za.png"), hoverStyle, new Dimension(16, 16), padding));
        controlPanel.add(new MenuSeparator(14));
        controlPanel.add(new ImageButton(Resources.getImage("system/panels/pp_controls_remove.png"), hoverStyle, new Dimension(16, 16), padding));

        contentPane.add(controlPanel, BorderLayout.NORTH);
    }

    private void initializeTable(JPanel contentPane) {
        table = new JTable(rows, headers);
        table.setEnabled(false);
        table.setFocusable(false);
        table.setRowSelectionAllowed(false);
        table.setShowGrid(true);
        table.setGridColor(new Color(156, 156, 156));
        table.setBorder(null);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);
        tableHeader.setDefaultRenderer(new TableCellHeaderRenderer());

        for (int index = 0; index < table.getColumnCount(); index++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(index);
            tableColumn.setCellRenderer(new TableCellRenderer());
        }

        table.getColumnModel().getColumn(0).setMinWidth(115);
        table.getColumnModel().getColumn(0).setMaxWidth(115);
        table.getColumnModel().getColumn(0).setPreferredWidth(115);

        int verticalScrollbarAsNeeded = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int horizontalScrollbarAsNeeded = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        JScrollPane scrollPane = new JScrollPane(table, verticalScrollbarAsNeeded, horizontalScrollbarAsNeeded);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.white);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    private class TableCellRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel cellLabel = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            cellLabel.setBorder(new EmptyBorder(3, 5, 3, 0));
            cellLabel.setFont(UIConfiguration.getInstance().getDefaultFont(false));
            cellLabel.setForeground(new Color(30, 30, 30));

            Color backgroundColor = row % 2 == 0 ? new Color(255, 255, 255) : new Color(245, 245, 245);
            cellLabel.setBackground(backgroundColor);
            return cellLabel;
        }
    }

    private class TableCellHeaderRenderer extends TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            JLabel titleLabel = new JLabel(value.toString());
            titleLabel.setBorder(new EmptyBorder(5, 5, 5, 0));
            titleLabel.setFont(UIConfiguration.getInstance().getDefaultFont(true));
            titleLabel.setForeground(new Color(30, 30, 30));
            titleLabel.setBackground(new Color(0, 0, 0));

            panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(156, 156, 156)));
            panel.setBackground(new Color(205,205,205));
            panel.add(titleLabel);
            return panel;
        }
    }
}
