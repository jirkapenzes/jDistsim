package jDistsim.designer.ui.control;

import jDistsim.utils.ui.control.IIconButtonHoverStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 26.9.12
 * Time: 14:36
 */
public class ImageButton extends JComponent {

    private Icon icon;
    private boolean active = false;
    private IIconButtonHoverStyle iconButtonHoverStyle;

    public ImageButton(Icon icon) {
        this(icon, null);
    }

    public ImageButton(Icon icon, IIconButtonHoverStyle iconButtonHoverStyle) {
        this(icon, iconButtonHoverStyle, new Dimension(15, 15));
    }

    public ImageButton(Icon icon, IIconButtonHoverStyle iconButtonHoverStyle, Dimension dimension) {
        this.icon = icon;
        this.iconButtonHoverStyle = iconButtonHoverStyle;

        setSize(dimension);
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                iconLabelMouseEntered();
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                iconLabelMouseExited();
            }
        });
    }

    private void iconLabelMouseExited() {
        active = false;
        repaint();
    }

    private void iconLabelMouseEntered() {
        active = true;
        if (iconButtonHoverStyle != null)
            iconButtonHoverStyle.applyHoverStyle(this);

        repaint();
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        icon.paintIcon(this, graphics, 0, 0);
    }
}
