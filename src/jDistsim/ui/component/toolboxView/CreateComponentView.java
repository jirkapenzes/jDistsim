package jDistsim.ui.component.toolboxView;

import jDistsim.ui.component.ComponentView;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 2.11.12
 * Time: 12:52
 */
public class CreateComponentView extends ComponentView {

    public CreateComponentView() {
    }

    @Override
    protected JComponent makeView() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                System.out.println(getWidth() + "x" + getHeight());
                g.setColor(Color.red);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
    }
}
