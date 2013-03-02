package jDistsim.utils.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Author: Jirka Pénzeš
 * Date: 26.1.13
 * Time: 0:44
 */
public final class SwingUtil {

    public static final int getComponentIndex(Component component) {
        if (component != null && component.getParent() != null) {
            Container currentComponent = component.getParent();
            for (int index = 0; index < currentComponent.getComponentCount(); index++) {
                if (currentComponent.getComponent(index) == component)
                    return index;
            }
        }
        return -1;
    }

    public static void getComponentTreePosition(Component component, ArrayList position) {
        if (component.getParent() == null) {
            return;
        }
        getComponentTreePosition(component.getParent(), position);
        position.add(new Integer(component.getParent().getComponentCount() - getComponentIndex(component)));
    }

    public static void setAbsoluteDimension(JComponent component, int width, int height) {
        setAbsoluteDimension(component, new Dimension(width, height));
    }

    public static void setAbsoluteDimension(JComponent component, Dimension dimension) {
        component.setPreferredSize(dimension);
        component.setMinimumSize(dimension);
        component.setMaximumSize(dimension);
        component.setSize(dimension);
    }

    public static void setColumnWidth(JTable table, int index, int width) {
        table.getColumnModel().getColumn(index).setMinWidth(width);
        table.getColumnModel().getColumn(index).setMaxWidth(width);
        table.getColumnModel().getColumn(index).setPreferredWidth(width);
    }

    public static void setPrefColumnWidth(JTable table, int index, int width) {
        table.getColumnModel().getColumn(index).setMinWidth(width);
        table.getColumnModel().getColumn(index).setPreferredWidth(width);
    }

    public static void drawCenteredString(String string, int width, int height, Graphics graphics) {
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int x = (width - fontMetrics.stringWidth(string)) / 2;
        int y = (fontMetrics.getAscent() + (height - (fontMetrics.getAscent() + fontMetrics.getDescent())) / 2);
        graphics.drawString(string, x, y);
    }
}
