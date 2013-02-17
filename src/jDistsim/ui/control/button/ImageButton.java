package jDistsim.ui.control.button;

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
    private boolean active;

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
        this(image, iconButtonHoverStyle, dimension, paddingSize, false);
    }

    public ImageButton(Image image, IIconButtonHoverStyle iconButtonHoverStyle, Dimension dimension, int paddingSize, boolean activeButton) {
        this.image = image;
        this.iconButtonHoverStyle = iconButtonHoverStyle;
        this.paddingSize = paddingSize;
        this.active = false;

        initialize(dimension, paddingSize);
    }

    private void initialize(Dimension dimension, int paddingSize) {
        Dimension temporaryDimension = new Dimension(dimension.width, dimension.height);
        temporaryDimension.setSize(dimension.width + 2 * paddingSize, dimension.height + 2 * paddingSize);
        setPreferredSize(temporaryDimension);
        setSize(temporaryDimension);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                if (active) return;
                iconLabelMouseEntered();
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                iconLabelMouseExited();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                iconLabelMousePressed();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                iconLabelMouseReleased();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
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

    private void iconLabelMousePressed() {
        if (iconButtonHoverStyle != null)
            iconButtonHoverStyle.applyPressedStyle(this);
    }

    private void iconLabelMouseReleased() {
        if (iconButtonHoverStyle != null)
            iconButtonHoverStyle.applyReleaseStyle(this);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (active) {
            graphics.setColor(new Color(199, 199, 199));
            graphics.fillRect(0, 0, getWidth(), getHeight());
            graphics.setColor(new Color(116, 116, 116));
            graphics.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
        graphics.drawImage(image, paddingSize, paddingSize, getWidth() - 2 * paddingSize, getHeight() - 2 * paddingSize, this);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        repaint();
    }
}
