package jDistsim.ui.panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 5.2.13
 * Time: 1:00
 */
public class ModulesPanel extends InternalPanel {

    private JTree tree;

    public ModulesPanel() {
        super("Modules");

        initialize();
    }

    private void initialize() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("model1");
        DefaultMutableTreeNode a = new DefaultMutableTreeNode("A");
        top.add(a);

        a.add(new DefaultMutableTreeNode("A1"));
        a.add(new DefaultMutableTreeNode("A2"));

        DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode("B");
        top.add(defaultMutableTreeNode);

        defaultMutableTreeNode.add(new DefaultMutableTreeNode("B1"));
        defaultMutableTreeNode.add(new DefaultMutableTreeNode("B2"));
        for (int i = 0; i < 20; i++)
            defaultMutableTreeNode.add(new DefaultMutableTreeNode("B3"));

        tree = new JTree(top);
        /*
        tree.setCellRenderer(new DefaultTreeCellRenderer() {
            private Border border = BorderFactory.createEmptyBorder(4, 4, 4, 4);

            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                label.setBorder(border);
                return label;
            }
        });
        */
        tree.setDragEnabled(false);
        tree.setEditable(false);


        int verticalScrollbarAsNeeded = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int horizontalScrollbarAsNeeded = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        JScrollPane jsp = new JScrollPane(tree, verticalScrollbarAsNeeded, horizontalScrollbarAsNeeded);

        tree.setBorder(new EmptyBorder(3, 3, 3, 3));
        add(jsp, BorderLayout.CENTER);
    }
}
