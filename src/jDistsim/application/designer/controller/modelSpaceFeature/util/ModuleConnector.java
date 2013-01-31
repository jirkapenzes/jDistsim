package jDistsim.application.designer.controller.modelSpaceFeature.util;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Author: Jirka Pénzeš
 * Date: 31.1.13
 * Time: 15:29
 */
public class ModuleConnector extends JComponent {

    private java.util.List<Point> points;
    private Point pointA;
    private Point pointB;
    private boolean drawingMode = false;

    public ModuleConnector() {
        points = new ArrayList<>();
    }

    public boolean isDrawingMode() {
        return drawingMode;
    }

    public void setDrawingMode(boolean drawingMode) {
        this.drawingMode = drawingMode;
        repaint();
    }

    public void setPoints(Point pointA, Point pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
        recalculatePointsCoordinates();
        repaint();
    }

    private void recalculatePointsCoordinates() {
        points.clear();

        if (Math.abs(pointA.y - pointB.y) == 0) {
            points.add(new Point(0, 0));
            points.add(new Point(getWidth() - 1, getHeight() - 1));
            return;
        }
        if (pointA.y > pointB.y && pointA.x < pointB.x) {
            points.add(new Point(0, getHeight() - 1));
            points.add(new Point(getWidth() / 2, getHeight() - 1));
            points.add(new Point(getWidth() / 2, 0));
            points.add(new Point(getWidth() - 1, 0));
            return;
        }
        if (pointA.y < pointB.y && pointA.x < pointB.x) {
            points.add(new Point(0, 0));
            points.add(new Point(getWidth() / 2, 0));
            points.add(new Point(getWidth() / 2, getHeight() - 1));
            points.add(new Point(getWidth() - 1, getHeight() - 1));
            return;
        }
        if (pointA.y > pointB.y && pointA.x > pointB.x) {
            points.add(new Point(getWidth() - 1, getHeight() - 1));
            points.add(new Point(getWidth() - 1, getHeight() / 2));
            points.add(new Point(0, getHeight() / 2));
            points.add(new Point(0, 0));
            return;
        }
        if (pointA.y < pointB.y && pointA.x > pointB.x) {
            points.add(new Point(getWidth() - 1, 0));
            points.add(new Point(getWidth() - 1, getHeight() / 2));
            points.add(new Point(0, getHeight() / 2));
            points.add(new Point(0, getHeight() - 1));
            return;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(30, 30);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (isDrawingMode()) {
            graphics2D.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, new float[]{5, 5}, 0));
        } else {
            graphics2D.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        }
        graphics.setColor(new Color(40, 40, 40));
        for (int index = 0; index < points.size() - 1; index++) {
            Point p1 = points.get(index);
            Point p2 = points.get(index + 1);
            graphics2D.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }
}
