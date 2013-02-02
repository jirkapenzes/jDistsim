package jDistsim.application.designer.controller.modelSpaceFeature.util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Author: Jirka Pénzeš
 * Date: 31.1.13
 * Time: 15:29
 */
public class ConnectorLine extends JComponent {

    private java.util.List<Point> points;
    private Point pointA;
    private Point pointB;
    private boolean drawingMode = false;
    private boolean active = false;
    private int margin = 20;
    private float lineSize = 1.0f;

    public ConnectorLine() {
        points = new ArrayList<>();
        setActive(false);
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        repaint();
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
        recalculateMargin();
        repaint();

    }

    private void recalculatePointsCoordinates() {
        points.clear();

        if (Math.abs(pointA.y - pointB.y) == 0) {
            points.add(new Point(0, 0));
            points.add(new Point(getWidth(), getHeight()));
            return;
        }
        if (pointA.y > pointB.y && pointA.x < pointB.x) {
            points.add(new Point(0, getHeight()));
            points.add(new Point(getWidth() / 2, getHeight()));
            points.add(new Point(getWidth() / 2, 0));
            points.add(new Point(getWidth(), 0));
            return;
        }
        if (pointA.y < pointB.y && pointA.x < pointB.x) {
            points.add(new Point(0, 0));
            points.add(new Point(getWidth() / 2, 0));
            points.add(new Point(getWidth() / 2, getHeight()));
            points.add(new Point(getWidth(), getHeight()));
            return;
        }
        if (pointA.y > pointB.y && pointA.x > pointB.x) {
            points.add(new Point(getWidth() - 1, getHeight() - 1));
            points.add(new Point(getWidth() - 1, getHeight() / 2));
            points.add(new Point(0, getHeight() / 2));
            points.add(new Point(0, 0));
            return;
        }
        if (pointA.y <= pointB.y && pointA.x >= pointB.x) {
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
            graphics2D.setStroke(new BasicStroke(lineSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, new float[]{5, 5}, 0));
        } else {
            graphics2D.setStroke(new BasicStroke(lineSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        }

        if (isActive()) {
            graphics2D.setColor(new Color(221, 36, 36));
            for (Point point : points)
                graphics2D.fillOval(point.x - 3, point.y - 3, 6, 6);
        } else {
            graphics2D.setColor(new Color(40, 40, 40));
        }

        for (int index = 0; index < points.size() - 1; index++) {
            Point p1 = points.get(index);
            Point p2 = points.get(index + 1);
            graphics2D.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    @Override
    public boolean contains(int x, int y) {
        int offset = 6;

        for (int index = 0; index < points.size() - 1; index++) {
            Point p1 = points.get(index);
            Point p2 = points.get(index + 1);

            Rectangle rectangle = new Rectangle(
                    Math.min(p1.x, p2.x) - offset,
                    Math.min(p1.y, p2.y) - offset,
                    Math.abs(p1.x - p2.x) + 2 * offset,
                    Math.abs(p1.y - p2.y) + 2 * offset);

            if (rectangle.contains(x, y))
                return true;
        }
        return false;
    }

    private void recalculateMargin() {
        for (int index = 0; index < points.size(); index++) {
            Point point = points.get(index);
            points.set(index, new Point(point.x + margin, point.y + margin));
        }
        Point currentLocation = getLocation();
        Dimension currentSize = getSize();
        setLocation(currentLocation.x - margin, currentLocation.y - margin);
        setSize(currentSize.width + 2 * margin, currentSize.height + 2 * margin);
        repaint();
    }
}
