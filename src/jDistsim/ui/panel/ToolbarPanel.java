package jDistsim.ui.panel;

import jDistsim.ui.control.MenuSeparator;
import jDistsim.ui.control.button.ImageButton;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.resource.Resources;
import jDistsim.utils.ui.control.IconBackgroundColorHoverStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 3.10.12
 * Time: 0:24
 */
public class ToolbarPanel extends JPanel {

    private Component relationsButton;

    public ToolbarPanel() {
        Logger.log("Initialize toolbox panel");
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
        setBorder(new EmptyBorder(0, 0, 0, 0));

        IconBackgroundColorHoverStyle hoverStyle = new IconBackgroundColorHoverStyle();
        Dimension iconDimension = new Dimension(16, 16);
        int padding = 5;

        add(new ImageButton(Resources.getImage("system/toolbar-icon-new.png"), hoverStyle, new Dimension(16, 16), padding));
        add(new ImageButton(Resources.getImage("system/toolbar-icon-open.png"), hoverStyle, iconDimension, padding));
        add(new ImageButton(Resources.getImage("system/toolbar-icon-save-as.png"), hoverStyle, iconDimension, padding));
        add(new MenuSeparator());
        relationsButton = add(new ImageButton(Resources.getImage("system/toolbar-icon-relationship.png"), hoverStyle, iconDimension, padding, true));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 45);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setColor(new Color(240, 240, 240));
        graphics.fillRect(0, 0, getWidth(), getHeight());

        graphics.setColor(new Color(220, 220, 220));
        graphics.fillRect(0, getHeight() - 15, getWidth(), getHeight());

        graphics.setColor((new Color(165, 165, 165)));
        graphics.drawLine(0, getHeight() - 15, getWidth(), getHeight() - 15);

        graphics.setColor((new Color(165, 165, 165)));
        graphics.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }

    public Component getRelationsButton() {
        return relationsButton;
    }
}
