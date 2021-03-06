package jDistsim.ui.control;

import jDistsim.application.designer.common.UIConfiguration;
import jDistsim.ui.control.button.ImageButton;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.resource.Resources;
import jDistsim.utils.resource.TextResources;
import jDistsim.utils.ui.control.IconHoverStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Author: Jirka Pénzeš
 * Date: 25.9.12
 * Time: 22:03
 */
public class GradientTitle extends JComponent {

    private JComponent parentControl;
    private ImageButton minimizeImageButton;
    private JLabel labelTitle;
    private Color color1;
    private Color color2;
    private boolean showTopBorderLine;

    public GradientTitle(JComponent parentControl) {
        this(parentControl, TextResources.NULL_TEXT);
    }

    public GradientTitle(JComponent parentControl, String titleText) {
        this(parentControl, titleText, new Color(236, 236, 236), new Color(228, 228, 228));
    }

    public GradientTitle(JComponent parentControl, String titleText, Color color1, Color color2) {
        this(parentControl, titleText, color1, color2, false);
    }

    public GradientTitle(JComponent parentControl, String titleText, Color color1, Color color2, boolean showTopBorderLine) {
        this.parentControl = parentControl;
        this.color1 = color1;
        this.color2 = color2;
        this.showTopBorderLine = showTopBorderLine;
        initializeComponents(titleText);
    }

    private void initializeComponents(String titleText) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0, 20, 0, 10));
        setMinimumSize(new Dimension(100, 20));

        labelTitle = new JLabel();
        setTitleText(titleText);
        setTitleTextFont(UIConfiguration.getInstance().getDefaultFont());
        setTitleTextForeground(new Color(96, 96, 96));

        minimizeImageButton = new ImageButton(Resources.getImage("system/toolbar-minimize-icon.png"), new IconHoverStyle(Resources.getImage("system/toolbar-minimize-icon-hover.png")));
        minimizeImageButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                iconButtonMousePressed(mouseEvent);
            }
        });

        Container container = new Container();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        // container.add(minimizeImageButton);

        add(container, BorderLayout.EAST);
        add(labelTitle, BorderLayout.WEST);
    }

    public void setTitleText(String text) {
        labelTitle.setText(text);
    }

    public void setTitleTextFont(Font font) {
        labelTitle.setFont(font);
    }

    public void setTitleTextForeground(Color color) {
        labelTitle.setForeground(color);
    }

    private void iconButtonMousePressed(MouseEvent mouseEvent) {
        Logger.log("Minimize parent control");
        parentControl.setVisible(false);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(parentControl.getWidth(), 20);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradientPaint = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        graphics2D.setPaint(gradientPaint);
        graphics2D.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        graphics2D.setColor(new Color(192, 192, 192));
        graphics2D.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);

        graphics2D.setColor(new Color(245, 245, 245));
        graphics2D.drawLine(0, 0, getWidth(), 0);

        graphics2D.setColor(new Color(157, 157, 157));
        graphics2D.drawRect(5, 5, 8, 8);

        if (showTopBorderLine) {
            graphics2D.setColor(new Color(192, 192, 192));
            graphics2D.drawLine(0, 0, getWidth(), 0);
        }
    }
}

