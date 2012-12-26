package jDistsim.core.modules;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 4.12.12
 * Time: 22:06
 */
public class ModuleConnectedPointUI extends JComponent {

    public enum State {
        Show, Active, Draw, Destination
    }

    private int size = 12;
    private final ModuleConnectedPoint connectedPoint;
    private final Point componentOffset;
    private State state;

    public ModuleConnectedPointUI(ModuleConnectedPoint connectedPoint, Point componentOffset) {
        this.connectedPoint = connectedPoint;
        this.componentOffset = componentOffset;
        this.state = State.Show;
        setSize(size, size);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        graphics2D.setColor(getBackgroundColor());
        graphics2D.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
        graphics2D.setColor(getBorderColor());
        graphics2D.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(size, size);
    }

    public Point getComponentOffset() {
        return componentOffset;
    }

    private Color getBackgroundColor() {
        switch (state) {
            case Show:
                return new Color(113, 113, 113);
            case Active:
                return new Color(188, 246, 47);
            default:
                return new Color(113, 113, 113);
        }
    }

    private Color getBorderColor() {
        switch (state) {
            case Show:
                return new Color(73, 64, 64);
            case Active:
                return new Color(62, 109, 0);
            default:
                return new Color(73, 64, 64);
        }
    }
}
