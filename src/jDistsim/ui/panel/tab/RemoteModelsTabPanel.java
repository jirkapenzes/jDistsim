package jDistsim.ui.panel.tab;

import jDistsim.application.designer.common.UIConfiguration;
import jDistsim.ui.control.button.ImageButton;
import jDistsim.ui.renderer.ValueTableCellHeaderRenderer;
import jDistsim.ui.renderer.ValueTableCellRenderer;
import jDistsim.ui.skins.ScrollBarUI;
import jDistsim.utils.resource.Resources;
import jDistsim.utils.ui.ListenerablePanel;
import jDistsim.utils.ui.SwingUtil;
import jDistsim.utils.ui.control.IconBackgroundColorHoverStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Author: Jirka Pénzeš
 * Date: 1.3.13
 * Time: 23:20
 */
public class RemoteModelsTabPanel extends ListenerablePanel {

    private ControlPanel controlPanel;

    public RemoteModelsTabPanel() {
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5));

        controlPanel = new ControlPanel();
        controlPanel.setPreferredSize(new Dimension(40, controlPanel.getHeight()));

        //textAreaPanel = new TextAreaPanel();
        ContentPanel contentPanel = new ContentPanel();

        add(controlPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    private class ControlPanel extends JComponent {

        private ImageButton addButton;
        private ImageButton editButton;
        private ImageButton removeButton;
        private ImageButton trashButton;

        public ControlPanel() {
            setLayout(new FlowLayout(FlowLayout.LEADING));

            IconBackgroundColorHoverStyle hoverStyle = new IconBackgroundColorHoverStyle();
            int padding = 3;

            addButton = new ImageButton(Resources.getImage("system/panels/mt_add.png"), hoverStyle, new Dimension(16, 16), padding);
            addButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    //getListener().onWordWrapButtonClick(addButton, mouseEvent);
                }
            });

            editButton = new ImageButton(Resources.getImage("system/panels/mt_edit.png"), hoverStyle, new Dimension(16, 16), padding);
            editButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    //getListener().onCopyToClipboardButtonClick(editButton, mouseEvent);
                }
            });

            removeButton = new ImageButton(Resources.getImage("system/panels/lp_trash.png"), hoverStyle, new Dimension(16, 16), padding);
            removeButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    //getListener().onScrollToEndButtonClick(removeButton, mouseEvent);
                }
            });

            trashButton = new ImageButton(Resources.getImage("system/panels/lp_trash.png"), hoverStyle, new Dimension(16, 16), padding);
            trashButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    //getListener().onTrashButtonClick(trashButton, mouseEvent);
                }
            });

            add(addButton);
            add(editButton);
            add(removeButton);
            //add(trashButton);
        }

        public ImageButton getAddButton() {
            return addButton;
        }

        public ImageButton getEditButton() {
            return editButton;
        }

        public ImageButton getRemoveButton() {
            return removeButton;
        }

        public ImageButton getTrashButton() {
            return trashButton;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            int rightPadding = 6;

            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            graphics2D.setColor(new Color(214, 214, 214));
            graphics2D.fillRect(0, 0, getWidth(), getHeight());

            graphics2D.setColor(new Color(240, 240, 240));
            graphics2D.fillRect(getWidth() - rightPadding, 0, getWidth(), getHeight());

            graphics2D.setColor(new Color(156, 156, 156));
            graphics2D.drawLine(0, 0, getWidth() - 1, 0);
            graphics2D.drawLine(0, 0, 0, getHeight() - 1);
            graphics2D.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);

            graphics2D.setColor(new Color(128, 128, 128));
            Stroke currentStroke = graphics2D.getStroke();
            BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{2.0f}, 0.0f);
            graphics2D.setStroke(dashed);

            graphics2D.drawLine(getWidth() - 1, 1, getWidth() - 1, getHeight() - 2);
            graphics2D.setStroke(currentStroke);
        }
    }

    private class ContentPanel extends JComponent {

        public ContentPanel() {
            setLayout(new BorderLayout());

            Vector columns = new Vector();
            columns.addElement("Model name");
            columns.addElement("RMI name");
            columns.addElement("Remote address");
            columns.addElement("Port");
            columns.addElement("Lookahead");

            Vector<Vector<String>> rows = new Vector<>();
            for (int i = 0; i < 0; i++) {
                Vector<String> row = new Vector<>();
                row.addElement("Highway");
                row.addElement("highway");
                row.addElement("localhost");
                row.addElement("4096");
                row.addElement("true");
                rows.add(row);
            }

            JTable table = new JTable(rows, columns);
            JTableHeader tableHeader = table.getTableHeader();
            tableHeader.setReorderingAllowed(false);
            tableHeader.setResizingAllowed(true);
            tableHeader.setDefaultRenderer(new ValueTableCellHeaderRenderer());

            for (int index = 0; index < table.getColumnCount(); index++) {
                TableColumn tableColumn = table.getColumnModel().getColumn(index);
                tableColumn.setCellRenderer(new ValueTableCellRenderer());
            }

            table.setEnabled(false);
            table.setFocusable(false);
            table.setRowSelectionAllowed(false);
            table.setGridColor(new Color(156, 156, 156));
            table.setOpaque(false);
            table.setShowGrid(true);

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
            scrollPane.setBorder(BorderFactory.createEmptyBorder());

            JPanel contentPane = new JPanel(new BorderLayout());
            contentPane.add(scrollPane, BorderLayout.CENTER);

            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);

            JPanel ma = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 3)) {
                @Override
                public void paintComponent(Graphics graphics) {
                    super.paintComponent(graphics);
                    Graphics2D graphics2D = (Graphics2D) graphics;

                    graphics2D.setColor(new Color(237, 237, 237));
                    graphics2D.fillRect(0, 0, getWidth(), getHeight());

                    graphics2D.setColor(new Color(156, 156, 156));
                    graphics2D.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
                    graphics2D.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);

                    graphics2D.setColor(new Color(128, 128, 128));
                    Stroke currentStroke = graphics2D.getStroke();
                    BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{2.0f}, 0.0f);
                    graphics2D.setStroke(dashed);

                    graphics2D.drawLine(0, 0, getWidth() - 1, 0);
                    graphics2D.setStroke(currentStroke);
                }
            };
            ma.setPreferredSize(new Dimension(getWidth(), 24));
            JLabel label = new JLabel("Local settings: localhost:4095/model1");
            label.setForeground(new Color(99, 99, 99));
            label.setFont(UIConfiguration.getInstance().getDefaultFont(11));

            IconBackgroundColorHoverStyle hoverStyle = new IconBackgroundColorHoverStyle();
            int padding = 1;
            ImageButton settingsButton = new ImageButton(Resources.getImage("system/panels/mt_config.png"), hoverStyle, new Dimension(16, 16), padding);

            ma.add(settingsButton);
            ma.add(label);
            setBorder(new EmptyBorder(1, 0, 0, 0));

            add(scrollPane, BorderLayout.CENTER);
            add(ma, BorderLayout.SOUTH);
        }

        @Override
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, getWidth(), getHeight());

            graphics2D.setColor(new Color(156, 156, 156));
            graphics2D.drawLine(0, 0, getWidth(), 0);
            graphics2D.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);

            if (true) {
                Font titleFont = new Font("Calibri", Font.PLAIN, 12);
                graphics2D.setFont(titleFont);
                graphics2D.setColor(new Color(153, 153, 153));
                SwingUtil.drawCenteredString("Nothing to show", getWidth(), getHeight(), graphics2D);
            }
        }
    }
}
