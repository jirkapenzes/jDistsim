package jDistsim.ui.panel;

import jDistsim.application.designer.common.UIConfiguration;
import jDistsim.ui.renderer.ValueTableCellRenderer;
import jDistsim.ui.skins.ScrollBarUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 18:26
 */
public class EnvironmentPanel extends JComponent {

    private JPanel controlPanel;
    private JTable table;
    private JScrollPane scrollPane;

    public EnvironmentPanel(JTable table) {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setLayout(new BorderLayout(0, 0));
        this.table = table;

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);

        for (int index = 0; index < table.getColumnCount(); index++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(index);
            tableColumn.setCellRenderer(new TableCellRenderer());
        }

        table.setBorder(new EmptyBorder(1, 1, 1, 1));
        table.setOpaque(false);
        //table.setShowGrid(false);
        table.setTableHeader(null);
        table.setShowVerticalLines(false);
        table.setGridColor(new Color(212, 212, 212));

        controlPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 3, 3));
        controlPanel.setOpaque(false);
        controlPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
        controlPanel.setPreferredSize(new Dimension(30, getWidth()));

        scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
        scrollPane.setBorder(BorderFactory.createEmptyBorder());


        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(new EmptyBorder(1, 1, 1, 1));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.setOpaque(false);

        add(controlPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);
    }

    public JPanel getControlPanel() {
        return controlPanel;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(new Color(237, 237, 237));
        graphics2D.fillRect(0, 0, 30, getHeight());

        graphics2D.setColor(new Color(212, 212, 212));
        graphics2D.drawLine(30, 0, 30, getHeight());
    }

    public void renderTable() {
        for (int index = 0; index < table.getColumnCount(); index++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(index);
            tableColumn.setCellRenderer(new ValueTableCellRenderer());
        }

        repaint();
    }

    private class TableCellRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel cellLabel = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            cellLabel.setBorder(new EmptyBorder(3, 5, 3, 0));
            cellLabel.setFont(UIConfiguration.getInstance().getDefaultFont(false));
            cellLabel.setForeground(new Color(20, 20, 20));

            Color backgroundColor = row % 2 == 0 ? new Color(255, 255, 255) : new Color(245, 245, 245);
            cellLabel.setBackground(backgroundColor);
            return cellLabel;
        }
    }
}
