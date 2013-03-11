package jDistsim.ui.control.button;

import jDistsim.utils.ui.control.IIconButtonHoverStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;

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
    private boolean deactivateMode;

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
        this.deactivateMode = false;

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
                if (deactivateMode) return;
                if (active) return;
                iconLabelMouseEntered();
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                if (deactivateMode) return;
                iconLabelMouseExited();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (deactivateMode) return;
                iconLabelMousePressed();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (deactivateMode) return;
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
        if (deactivateMode) return;
        if (iconButtonHoverStyle != null)
            iconButtonHoverStyle.applyHoverStyle(this);
    }

    private void iconLabelMousePressed() {
        if (deactivateMode) return;
        if (iconButtonHoverStyle != null)
            iconButtonHoverStyle.applyPressedStyle(this);
    }

    private void iconLabelMouseReleased() {
        if (deactivateMode) return;
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
        if (deactivateMode)
            graphics.drawImage(createDisabledImage(image), paddingSize, paddingSize, getWidth() - 2 * paddingSize, getHeight() - 2 * paddingSize, this);
        else
            graphics.drawImage(image, paddingSize, paddingSize, getWidth() - 2 * paddingSize, getHeight() - 2 * paddingSize, this);
    }

    public static Image createDisabledImage (Image i) {
        GrayFilter filter = new GrayFilter(true, 60);
        ImageProducer prod = new FilteredImageSource(i.getSource(), filter);
        Image grayImage = Toolkit.getDefaultToolkit().createImage(prod);
        return grayImage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        repaint();
    }

    public void deactivate() {
        this.deactivateMode = true;
        repaint();
    }

    public void activate() {
        this.deactivateMode = false;
        repaint();
    }

    public IIconButtonHoverStyle getIconButtonHoverStyle() {
        return iconButtonHoverStyle;
    }

    public void setIconButtonHoverStyle(IIconButtonHoverStyle iconButtonHoverStyle) {
        this.iconButtonHoverStyle = iconButtonHoverStyle;
    }
}
