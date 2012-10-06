package jDistsim.designer.ui.control.button;

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

    private Image image;
    private IIconButtonHoverStyle iconButtonHoverStyle;
    private int paddingSize;

    public ImageButton(Image image) {
        this(image, null);
    }

    public ImageButton(Image image, IIconButtonHoverStyle iconButtonHoverStyle) {
        this(image, iconButtonHoverStyle, new Dimension(10, 10));
    }

    public ImageButton(Image image, IIconButtonHoverStyle iconButtonHoverStyle, Dimension dimension) {
        this(image, iconButtonHoverStyle, dimension, 0);
    }

    public ImageButton(Image image, IIconButtonHoverStyle iconButtonHoverStyle, Dimension dimension, int paddingSize) {
        this.image = image;
        this.iconButtonHoverStyle = iconButtonHoverStyle;
        this.paddingSize = paddingSize;

        dimension.setSize(dimension.width + 2 * paddingSize, dimension.height + 2 * paddingSize);
        setPreferredSize(dimension);
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

    public Image getImage() {
        return image;
    }

    public int getPaddingSize() {
        return paddingSize;
    }

    private void iconLabelMouseExited() {
        repaint();
    }

    private void iconLabelMouseEntered() {
        if (iconButtonHoverStyle != null)
            iconButtonHoverStyle.applyHoverStyle(this);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(image, paddingSize, paddingSize,
                getWidth() - 2 * paddingSize, getHeight() - 2 * paddingSize, this);
    }
}
