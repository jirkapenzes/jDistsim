package jDistsim.ui.renderer;

import jDistsim.application.designer.common.UIConfiguration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 28.2.13
 * Time: 13:14
 */
public class ValueTableCellRenderer extends DefaultTableCellRenderer {
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