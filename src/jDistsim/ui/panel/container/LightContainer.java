package jDistsim.ui.panel.container;

import jDistsim.application.designer.common.UIConfiguration;
import jDistsim.ui.control.button.ImageButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 10:19
 */
public class LightContainer extends JPanel {

    private JComponent contentPane;
    private JComponent titleContentPane;
    private int titleHeight;
    private ImageButton imageButton;

    public LightContainer(String titleText, int titleHeight) {
        this.titleHeight = titleHeight;
        initialize(titleText);
    }

    public LightContainer(String titleText, int titleHeight, JComponent contentPane) {
        this(titleText, titleHeight);
        setContentPane(contentPane);
    }

    public LightContainer(String titleText, JComponent contentPane) {
        this(titleText, 24, contentPane);
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

    public void setImageButton(ImageButton imageButton) {
        if (this.imageButton != null)
            remove(imageButton);

        this.imageButton = imageButton;
        titleContentPane.add(imageButton, BorderLayout.EAST);
    }

    private void initialize(String titleText) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(1, 1, 1, 1));

        titleContentPane = new JPanel();
        titleContentPane.setLayout(new BorderLayout());
        titleContentPane.setOpaque(false);

        JLabel titleLabel = new JLabel();
        titleLabel.setBorder(new EmptyBorder(5, 5, 5, 0));
        titleLabel.setText(titleText);
        titleLabel.setForeground(new Color(0, 0, 0));
        titleLabel.setFont(UIConfiguration.getInstance().getDefaultFont(true));

        titleContentPane.add(titleLabel, BorderLayout.WEST);
        add(titleContentPane, BorderLayout.NORTH);
    }

    public void setContentPane(JComponent contentPane) {
        if (this.contentPane != null)
            remove(contentPane);

        this.contentPane = contentPane;
        this.contentPane.setOpaque(false);
        add(contentPane, BorderLayout.CENTER);
    }

    public JComponent getContentPane() {
        return contentPane;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(240, 240, 240), 0, titleHeight, new Color(214, 214, 214));

        //Paint currentPaint = graphics2D.getPaint();
        //graphics2D.setPaint(gradientPaint);
        graphics.setColor(new Color(205, 205, 205));
        graphics2D.fill(new Rectangle2D.Double(0, 0, getWidth(), titleHeight));

        //graphics2D.setPaint(currentPaint);
        graphics2D.setColor(new Color(192, 192, 192));

        graphics2D.setColor(new Color(156, 156, 156));
        graphics2D.drawLine(0, titleHeight, getWidth(), titleHeight);
        graphics2D.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
