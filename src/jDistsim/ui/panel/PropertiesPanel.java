package jDistsim.ui.panel;

import jDistsim.application.designer.common.UIConfiguration;
import jDistsim.ui.control.MenuSeparator;
import jDistsim.ui.control.button.ImageButton;
import jDistsim.ui.panel.listener.PropertiesViewListener;
import jDistsim.utils.resource.Resources;
import jDistsim.utils.ui.control.IconBackgroundColorHoverStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 5.2.13
 * Time: 0:19
 */
public class PropertiesPanel extends InternalPanel {

    private JTable table;
    private JScrollPane scrollPane;
    private JPanel contentPane;
    private PropertiesViewListener viewListener;

    private ImageButton pinButton;
    private ImageButton ascendingButton;
    private ImageButton descendingButton;
    private ImageButton settingsButton;

    public PropertiesPanel(JTable table) {
        super("Properties", false, false);
        this.table = table;

        setFooterBorderLine(true);
        initialize();
    }

    private void initialize() {
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBorder(null);

        initializeControlPanel();
        initializeTable();
        add(contentPane, BorderLayout.CENTER);
    }

    private void initializeControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new Color(237, 237, 237));
        controlPanel.setPreferredSize(new Dimension(getWidth(), 26));
        controlPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(156, 156, 156)));
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));

        IconBackgroundColorHoverStyle hoverStyle = new IconBackgroundColorHoverStyle();
        int padding = 3;

        pinButton = new ImageButton(Resources.getImage("system/panels/g_pin.png"), hoverStyle, new Dimension(16, 16), padding);
        pinButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                viewListener.onPinButtonClick(mouseEvent, pinButton);
            }
        });
        ascendingButton = new ImageButton(Resources.getImage("system/panels/pp_controls_sort_az.png"), hoverStyle, new Dimension(16, 16), padding);
        ascendingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                viewListener.onAscendingButtonClick(mouseEvent, ascendingButton);
            }
        });
        descendingButton = new ImageButton(Resources.getImage("system/panels/pp_controls_sort_za.png"), hoverStyle, new Dimension(16, 16), padding);
        descendingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                viewListener.onDescendingButtonClick(mouseEvent, descendingButton);
            }
        });
        settingsButton = new ImageButton(Resources.getImage("system/panels/pp_controls_edit.png"), hoverStyle, new Dimension(16, 16), padding);
        settingsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                viewListener.onEditButtonClick(mouseEvent, settingsButton);
            }
        });

        controlPanel.add(settingsButton);
        controlPanel.add(new ImageButton(Resources.getImage("system/panels/pp_controls_depen.png"), hoverStyle, new Dimension(16, 16), padding));
        controlPanel.add(new ImageButton(Resources.getImage("system/panels/pp_controls_help.png"), hoverStyle, new Dimension(16, 16), padding));
        controlPanel.add(pinButton);
        controlPanel.add(new MenuSeparator(14));
        controlPanel.add(ascendingButton);
        controlPanel.add(descendingButton);
        controlPanel.add(new MenuSeparator(14));
        controlPanel.add(new ImageButton(Resources.getImage("system/panels/pp_controls_remove.png"), hoverStyle, new Dimension(16, 16), padding));


        contentPane.add(controlPanel, BorderLayout.NORTH);
    }

    private void initializeTable() {
        table.setModel(new DefaultTableModel(0, 3));
        table.setEnabled(false);
        table.setFocusable(false);
        table.setRowSelectionAllowed(false);
        table.setShowGrid(true);
        table.setGridColor(new Color(156, 156, 156));
        table.setBorder(null);

        renderTable();

        int verticalScrollbarAsNeeded = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int horizontalScrollbarAsNeeded = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        scrollPane = new JScrollPane(table, verticalScrollbarAsNeeded, horizontalScrollbarAsNeeded);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.white);
        contentPane.add(table, BorderLayout.CENTER);
    }

    public void setViewListener(PropertiesViewListener viewListener) {
        this.viewListener = viewListener;
    }

    public void renderTable() {
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);
        tableHeader.setDefaultRenderer(new TableCellHeaderRenderer());

        for (int index = 0; index < table.getColumnCount(); index++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(index);
            tableColumn.setCellRenderer(new TableCellRenderer());
        }

        setColumnWidth(0, 125);
        setColumnWidth(2, 0);
    }

    private void setColumnWidth(int index, int width) {
        table.getColumnModel().getColumn(index).setMinWidth(width);
        table.getColumnModel().getColumn(index).setMaxWidth(width);
        table.getColumnModel().getColumn(index).setPreferredWidth(width);
    }

    public ImageButton getPinnedButton() {
        return pinButton;
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
            panel.setBackground(new Color(205, 205, 205));
            panel.add(titleLabel);
            return panel;
        }
    }

    @Override
    public void showNothing() {
        table.setVisible(false);
        contentPane.setOpaque(false);
        super.showNothing();
    }

    @Override
    public void hideNothing() {
        table.setVisible(true);
        contentPane.setOpaque(true);
        super.hideNothing();
    }
}
