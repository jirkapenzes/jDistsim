package jDistsim.ui.panel.toolbox;

import jDistsim.ui.component.ComponentView;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 4.11.12
 * Time: 15:38
 */
public class ToolboxButton extends JComponent {

    private ComponentView componentView;

    public ToolboxButton(ComponentView componentView) {
        this.componentView = componentView;
        this.setPreferredSize(new Dimension(100, 100));
        JComponent component = componentView.getView();
        component.setLocation(new Point(10, 10));
        component.setSize(new Dimension(80, 80));
        add(component);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.blue);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        System.out.println("DRAWING");
    }
}
