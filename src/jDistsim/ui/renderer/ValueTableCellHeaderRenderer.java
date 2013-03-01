package jDistsim.ui.renderer;

import jDistsim.application.designer.common.UIConfiguration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 28.2.13
 * Time: 13:14
 */
public class ValueTableCellHeaderRenderer extends ValueTableCellRenderer {
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