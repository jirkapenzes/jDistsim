package jDistsim.designer.ui.panel;

import jDistsim.utils.logging.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 30.9.12
 * Time: 18:15
 */
public class ModelSpacePanel extends JPanel {

    public ModelSpacePanel() {
        Logger.log("Initialize model space");
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(2000, 2000);
    }

    @Override
    protected void paintComponent(Graphics graphics2D) {
        super.paintComponent(graphics2D);
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());

        for (int i = 0; i < getWidth(); i = i + 10) {
            if (i % 6 == 0) {
                graphics2D.setColor(new Color(210, 210, 210));
            } else {
                graphics2D.setColor(new Color(241, 241, 241));
            }
            graphics2D.drawLine(i, 0, i, getHeight());
        }

        for (int i = 0; i < getHeight(); i = i + 10) {
            if (i % 6 == 0) {
                graphics2D.setColor(new Color(210, 210, 210));
            } else {
                graphics2D.setColor(new Color(241, 241, 241));
            }
            graphics2D.drawLine(0, i, getWidth(), i);
        }
    }
}
