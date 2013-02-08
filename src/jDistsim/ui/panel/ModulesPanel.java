package jDistsim.ui.panel;

import jDistsim.ui.control.MenuSeparator;
import jDistsim.ui.control.button.ImageButton;
import jDistsim.utils.resource.Resources;
import jDistsim.utils.ui.control.IconBackgroundColorHoverStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 5.2.13
 * Time: 1:00
 */
public class ModulesPanel extends InternalPanel {

    private JTree jTree;

    public ModulesPanel(JTree jTree) {
        super("Modules on model");
        this.jTree = jTree;
        initialize();
    }

    private void initialize() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBorder(null);

        initializeControlPanel(contentPane);
        initializeContentPanel(contentPane);
        add(contentPane, BorderLayout.CENTER);
    }

    private void initializeControlPanel(JPanel contentPane) {
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new Color(237, 237, 237));
        controlPanel.setPreferredSize(new Dimension(getWidth(), 26));
        controlPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(203, 203, 203)));
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));

        IconBackgroundColorHoverStyle hoverStyle = new IconBackgroundColorHoverStyle();
        int padding = 3;

        controlPanel.add(new ImageButton(Resources.getImage("system/panels/mp_controls_expand.png"), hoverStyle, new Dimension(16, 16), padding));
        controlPanel.add(new ImageButton(Resources.getImage("system/panels/mp_controls_collapse.png"), hoverStyle, new Dimension(16, 16), padding));
        controlPanel.add(new MenuSeparator(14));
        controlPanel.add(new ImageButton(Resources.getImage("system/panels/mp_controls_list.png"), hoverStyle, new Dimension(16, 16), padding));
        controlPanel.add(new ImageButton(Resources.getImage("system/panels/mp_controls_tree.png"), hoverStyle, new Dimension(16, 16), padding));

        contentPane.add(controlPanel, BorderLayout.NORTH);
    }

    private void initializeContentPanel(JPanel contentPane) {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("model1");
        DefaultMutableTreeNode create_1 = new DefaultMutableTreeNode("create_1");
        top.add(create_1);


        DefaultMutableTreeNode delay = new DefaultMutableTreeNode("delay_1");
        delay.add(new DefaultMutableTreeNode("dispose_1"));
        create_1.add(delay);

        DefaultMutableTreeNode create_2 = new DefaultMutableTreeNode("create_2");
        top.add(create_2);

        DefaultMutableTreeNode delay1 = new DefaultMutableTreeNode("delay_1");
        delay1.add(new DefaultMutableTreeNode("dispose_1"));
        create_2.add(delay1);

        jTree = new JTree(top);
        jTree.setCellRenderer(new DefaultTreeCellRenderer() {
            public Component getTreeCellRendererComponent(final JTree tree, Object value,
                                                          boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

                JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

                if (((DefaultMutableTreeNode) value).isRoot())
                    label.setIcon(new ImageIcon(Resources.getImage("system/t_icon_model.png")));
                else {
                    if (label.getText().contains("create")) {
                        label.setIcon(new ImageIcon(Resources.getImage("system/t_icon_create.png")));
                    } else {
                        label.setIcon(new ImageIcon(Resources.getImage("system/t_icon_classic.png")));
                    }
                }
                return label;
            }
        });
        jTree.setDragEnabled(false);
        jTree.setEditable(false);

        int verticalScrollbarAsNeeded = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int horizontalScrollbarAsNeeded = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        JScrollPane jsp = new JScrollPane(jTree, verticalScrollbarAsNeeded, horizontalScrollbarAsNeeded);
        jsp.setBorder(BorderFactory.createEmptyBorder());

        jTree.setBorder(new EmptyBorder(3, 3, 3, 3));
        contentPane.add(jsp, BorderLayout.CENTER);
    }
}
