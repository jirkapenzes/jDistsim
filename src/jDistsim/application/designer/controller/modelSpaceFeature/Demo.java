package jDistsim.application.designer.controller.modelSpaceFeature;

import jDistsim.application.designer.controller.ModelSpaceController;
import jDistsim.core.modules.ModuleConnectedPointUI;
import jDistsim.core.modules.ModuleUI;
import jDistsim.utils.common.ModelSpaceListener;
import jDistsim.utils.ui.SwingUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 29.12.12
 * Time: 20:14
 */
public class Demo extends ModelSpaceListener {

    private final int pointSize = 12;

    private class ConnectPointHelper extends JComponent {

        private ModuleConnectedPointUI moduleConnectedPointUI;
        private Color color;

        private ConnectPointHelper(ModuleConnectedPointUI moduleConnectedPointUI, int size, Point location) {
            this.moduleConnectedPointUI = moduleConnectedPointUI;
            setLocation(location);
            setSize(size, size);
            setDefaultColor();
        }

        public ModuleConnectedPointUI getModuleConnectedPointUI() {
            return moduleConnectedPointUI;
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(getSize().width, getSize().height);
        }


        public void setBackgroundColor(Color color) {
            this.color = color;
            repaint();
        }

        public void setDefaultColor() {
            setBackgroundColor(new Color(62, 109, 0));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            graphics2D.setColor(new Color(188, 246, 47));
            graphics2D.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            graphics2D.setColor(color);
            graphics2D.fillRect(2, 2, getWidth() - 5, getHeight() - 5);
        }
    }

    private List<ConnectPointHelper> connectPointHelperList;
    private ModuleUI currentActiveModule;

    public Demo() {
        connectPointHelperList = new ArrayList<>();
        currentActiveModule = null;
    }

    @Override
    public void onModelSelectedActiveModule(ModuleUI module, ModelSpaceController modelSpaceController) {
        removeAlCurrentHelperPoints(modelSpaceController);
        showHelperPoints(module, modelSpaceController);
        showPossibleDependecies(modelSpaceController);
    }

    @Override
    public void onModelUnselectedActiveModule(ModuleUI module, ModelSpaceController modelSpaceController) {
        removeAlCurrentHelperPoints(modelSpaceController);
    }

    @Override
    public void modelSpaceMotionMouseMoved(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
        if (!showHelperPoints(mouseEvent))
            removeAlCurrentHelperPoints(modelSpaceController);
    }

    private boolean showHelperPoints(MouseEvent mouseEvent) {
        if (currentActiveModule == null || currentActiveModule.contains(mouseEvent.getPoint()))
            return true;

        if (currentActiveModule.isActive())
            return true;

        for (ConnectPointHelper pointHelper : connectPointHelperList) {
            if (pointHelper.getBounds().contains(mouseEvent.getPoint())) {
                return true;
            }
        }
        return false;
    }

    // Pokud z modulem tahnu - pak body odstranim
    @Override
    public void moduleMotionMouseDragged(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
        removeAlCurrentHelperPoints(modelSpaceController);
    }

    // Pokud najedu mysi na modul -> zobrazi se pripojny body (pouze pokud neni nejaky modul aktivni)
    @Override
    public void moduleMouseEntered(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
        // pokud je modul aktivni -> body jsou videt trvale a neni potreba je zobrazovat znovu
        if (currentActiveModule != null && currentActiveModule.isActive())
            return;

        ModuleUI moduleUI = getModuleUIFromMouseEvent(mouseEvent);
        removeAlCurrentHelperPoints(modelSpaceController);
        showHelperPoints(moduleUI, modelSpaceController);
        currentActiveModule = moduleUI;
    }

    // Pokud jsem tahl mysi a pak pustil tlacitko - body se musi opet objevit
    @Override
    public void moduleMouseReleased(MouseEvent mouseEvent, ModelSpaceController modelSpaceController) {
        if (currentActiveModule != null && !currentActiveModule.isActive())
            return;

        showHelperPoints((ModuleUI) mouseEvent.getSource(), modelSpaceController);
        showPossibleDependecies(modelSpaceController);
    }

    private void showHelperPoints(ModuleUI moduleUI, ModelSpaceController modelSpaceController) {
        for (ModuleConnectedPointUI connectedPointUI : moduleUI.getConnectedPoints()) {
            addConnectedPointHelper(moduleUI, modelSpaceController, connectedPointUI);
        }
    }

    private void showPossibleDependecies(ModelSpaceController modelSpaceController) {
        for (ModuleUI moduleUI : modelSpaceController.getModuleList().values()) {
            if (moduleUI.getIdentifier().equals(currentActiveModule.getIdentifier()))
                continue;

            for (ModuleConnectedPointUI connectedPointUI : moduleUI.getConnectedPoints()) {
                if (connectedPointUI.getType() == ModuleConnectedPointUI.Type.INPUT) {
                    addConnectedPointHelper(moduleUI, modelSpaceController, connectedPointUI);
                }
            }
        }
    }

    private void removeAlCurrentHelperPoints(ModelSpaceController controller) {
        for (ConnectPointHelper pointHelper : connectPointHelperList) {
            controller.getView().getContentPane().remove(pointHelper);
        }
        connectPointHelperList.clear();
    }

    private void addConnectedPointHelper(ModuleUI moduleUI, ModelSpaceController modelSpaceController, ModuleConnectedPointUI connectedPointUI) {
        Point location = ModelSpaceHelper.calculatePointPosition(pointSize, connectedPointUI, moduleUI);
        ConnectPointHelper connectPointHelper = new ConnectPointHelper(connectedPointUI, pointSize, location);
        int moduleIndex = SwingUtil.getComponentIndex(moduleUI);
        modelSpaceController.getView().getContentPane().add(connectPointHelper, moduleIndex);
        connectPointHelperList.add(connectPointHelper);
        ConnectorDrawerAdapter connectorDrawerAdapter = new ConnectorDrawerAdapter(modelSpaceController.getView().getContentPane(), ModelSpaceHelper.calculatePointPosition(0, connectedPointUI, moduleUI), new ArrayList<>(modelSpaceController.getModuleList().values()));
        connectPointHelper.addMouseMotionListener(connectorDrawerAdapter.getMouseMotionAdapter());
        connectPointHelper.addMouseListener(connectorDrawerAdapter.getMouseAdapter());
    }

    private class ConnectorDrawerAdapter {

        private JComponent canvas;
        private SampleBox box;
        private Point startPosition;
        private ArrayList<ModuleUI> modules;
        private Point currentPosition;
        private MouseMotionAdapter mouseMotionAdapter;
        private MouseAdapter mouseAdapter;
        private ConnectPointHelper currentPointHelpler;

        public ConnectorDrawerAdapter(JComponent container, Point initialPosition, ArrayList<ModuleUI> modulesList) {
            this.canvas = container;
            this.startPosition = initialPosition;
            this.modules = modulesList;

            mouseMotionAdapter = new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent mouseEvent) {
                    currentPosition = mouseEvent.getPoint();
                    currentPosition.setLocation(currentPosition.x - pointSize / 2, currentPosition.y - pointSize / 2);
                    Point currentCanvasPosition = new Point(startPosition.x + currentPosition.x, startPosition.y + currentPosition.y);
                    Point connectorLocation = new Point(Math.min(startPosition.x, currentCanvasPosition.x), Math.min(startPosition.y, currentCanvasPosition.y));
                    Dimension dimension = new Dimension(Math.abs(currentCanvasPosition.x - startPosition.x), Math.abs(currentCanvasPosition.y - startPosition.y));
                    if (dimension.getSize().height < 2) {
                        dimension = new Dimension(dimension.width, 2);
                    }

                    box.setSize(dimension);
                    box.setLocation(connectorLocation);
                    box.setPoints(startPosition, currentCanvasPosition);

                    if (currentPointHelpler != null)
                        currentPointHelpler.setDefaultColor();

                    for (ConnectPointHelper pointHelper : connectPointHelperList) {
                        if (pointHelper.contains(currentCanvasPosition.x - pointHelper.getX(), currentCanvasPosition.y - pointHelper.getY())) {
                            currentPointHelpler = pointHelper;
                            currentPointHelpler.setBackgroundColor(Color.red);
                            break;
                        }
                    }
                }
            };

            mouseAdapter = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    box = new SampleBox();
                    box.setDrawingMode(true);
                    box.setLocation(startPosition.x, startPosition.y);
                    canvas.add(box);
                    canvas.repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    canvas.remove(box);
                    canvas.repaint();
                }
            };
        }

        public MouseMotionAdapter getMouseMotionAdapter() {
            return mouseMotionAdapter;
        }

        public MouseAdapter getMouseAdapter() {
            return mouseAdapter;
        }

        private class SampleBox extends JComponent {

            private List<Point> points;
            private Point pointA;
            private Point pointB;
            private boolean drawingMode = false;

            private SampleBox() {
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

        private class SuperRectangle {
            private Point begin;
            private Point end;
            private Dimension size;

            private SuperRectangle(Point begin, Point end) {
                this.begin = new Point(Math.min(begin.x, end.x), Math.min(begin.y, end.y));
                this.end = new Point(Math.max(begin.x, end.x), Math.max(begin.y, end.y));
                this.size = new Dimension(Math.abs(end.x - begin.x), Math.abs(end.y - begin.y));
            }

            public Point getBegin() {
                return begin;
            }

            public Point getEnd() {
                return end;
            }

            public Dimension getSize() {
                return size;
            }

            @Override
            public String toString() {
                return "SuperRectangle{" +
                        "begin=" + begin +
                        ", end=" + end +
                        ", size=" + size +
                        '}';
            }
        }
    }
}
