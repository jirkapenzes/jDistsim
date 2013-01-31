package jDistsim.ui.module;

import jDistsim.core.modules.IModuleView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 2.11.12
 * Time: 12:41
 */
public abstract class ModuleView implements IModuleView {

    private JComponent view;
    protected final List<Point> inputPoints;
    protected final List<Point> outputPoints;

    private Color backgroundColor;
    private Color borderColor;

    public ModuleView() {
        setDefaultBackgroundColor();
        setDefaultBorderColor();

        view = new ModuleViewComponent();
        inputPoints = new ArrayList<>();
        outputPoints = new ArrayList<>();

        view.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                invalidateConnectedPoints();
            }
        });
    }

    public void invalidateConnectedPoints() {
        invalidateConnectedPoints(view.getWidth(), view.getHeight());
    }

    public void invalidateConnectedPoints(int width, int height) {
        inputPoints.clear();
        outputPoints.clear();
        initializeConnectedPoints(width, height);
    }

    protected abstract void initializeConnectedPoints(int width, int height);

    @Override
    public JComponent getContentPane() {
        return view;
    }

    @Override
    public void draw(Graphics2D graphics, int width, int height) {
        Graphics2D graphics2D = graphics;
        setDefaultRenderingMode(graphics2D);
        setDefaultBasicStroke(graphics2D);

        Polygon polygon = getBounds(width, height);
        graphics2D.setColor(getBackgroundColor());
        graphics2D.fillPolygon(polygon);
        graphics2D.setColor(getBorderColor());
        graphics2D.drawPolygon(polygon);
    }


    protected void setDefaultRenderingMode(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    protected void setDefaultBasicStroke(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setDefaultBackgroundColor() {
        setBackgroundColor(new Color(67, 201, 224));
    }

    public void setDefaultBorderColor() {
        setBorderColor(new Color(70, 127, 137));
    }

    @Override
    public List<Point> getInputPoints() {
        return inputPoints;
    }

    @Override
    public List<Point> getOutputPoints() {
        return outputPoints;
    }

    private class ModuleViewComponent extends JComponent {

        public ModuleViewComponent() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            draw((Graphics2D) graphics, getWidth(), getHeight());
        }
    }
}
