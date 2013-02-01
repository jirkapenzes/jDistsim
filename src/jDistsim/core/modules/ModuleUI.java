package jDistsim.core.modules;


import jDistsim.ui.module.ModuleView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 17:14
 */
public class ModuleUI extends JComponent {

    private boolean active;
    private Module module;
    private final List<ModuleConnectedPointUI> connectedPoints;

    public ModuleUI(Module module) {
        this.module = module;
        this.connectedPoints = new ArrayList<>();
        initializeUI();
    }

    private void initializeUI() {
        setSize(80, 50);

        ModuleView view = module.getView();
        view.invalidateConnectedPoints(80, 50);

        for (int index = 0; index < module.getInputConnectedPoints().size(); index++) {
            ModuleConnectedPoint connectedPoint = module.getInputConnectedPoints().get(index);
            connectedPoints.add(new ModuleConnectedPointUI(connectedPoint, ModuleConnectedPointUI.Type.INPUT, view.getInputPoints().get(index), this));
        }

        for (int index = 0; index < module.getOutputConnectedPoints().size(); index++) {
            ModuleConnectedPoint connectedPoint = module.getOutputConnectedPoints().get(index);
            connectedPoints.add(new ModuleConnectedPointUI(connectedPoint, ModuleConnectedPointUI.Type.OUTPUT, view.getOutputPoints().get(index), this));
        }
    }

    public Module getModule() {
        return module;
    }

    public List<ModuleConnectedPointUI> getConnectedPoints() {
        return connectedPoints;
    }

    public List<ModuleConnectedPointUI> getInputPoints() {
        ArrayList<ModuleConnectedPointUI> result = new ArrayList<>();
        for (ModuleConnectedPointUI connectedPointUI : connectedPoints) {
            if (connectedPointUI.getType() == ModuleConnectedPointUI.Type.INPUT)
                result.add(connectedPointUI);
        }
        return result;
    }


    public List<ModuleConnectedPointUI> getOutputPoints() {
        ArrayList<ModuleConnectedPointUI> result = new ArrayList<>();
        for (ModuleConnectedPointUI connectedPointUI : connectedPoints) {
            if (connectedPointUI.getType() == ModuleConnectedPointUI.Type.OUTPUT)
                result.add(connectedPointUI);
        }
        return result;
    }

    public void setActive(boolean active) {
        this.active = active;
        repaint();
    }

    public boolean isActive() {
        return active;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        module.getView().draw(graphics2D, getWidth(), getHeight());

        if (active) {
            graphics2D.setColor(new Color(160, 160, 160));
            int size = 6;
            graphics2D.fillRect(0, 0, size, size);
            graphics2D.fillRect(getWidth() - size, 0, size, size);
            graphics2D.fillRect(0, getHeight() - size, size, size);
            graphics2D.fillRect(getWidth() - size, getHeight() - size, size, size);
            graphics2D.fillRect(getWidth() / 2 - size / 2, 0, size, size);
            graphics2D.fillRect(getWidth() / 2 - size / 2, getHeight() - size, size, size);
            graphics2D.fillRect(0, getHeight() / 2 - size / 2, size, size);
            graphics2D.fillRect(getWidth() - size, getHeight() / 2 - size / 2, size, size);

            BasicStroke lineStroke = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, new float[]{5, 5}, 0);
            graphics2D.setColor(new Color(115, 115, 115));
            graphics2D.setStroke(lineStroke);
            graphics2D.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }

        Font titleFont = new Font("Calibri", Font.PLAIN, 12);
        graphics2D.setFont(titleFont);
        graphics2D.setColor(new Color(58, 58, 58));
        drawCenteredString(module.getIdentifier(), getWidth(), getHeight(), graphics2D);
    }

    public void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }

    public void setIdentifier(String identifier) {
        module.setIdentifier(identifier);
        repaint();
    }

    public void setActiveBackgroundColor() {
        module.getView().setBackgroundColor(new Color(141, 210, 60));
        repaint();
    }

    public void setDefaultBackgroundColor() {
        module.getView().setDefaultBackgroundColor();
        repaint();
    }

    public String getIdentifier() {
        return module.getIdentifier();
    }

    public boolean getActive() {
        return active;
    }

    @Override
    public boolean contains(int x, int y) {
        return module.getView().getBounds(getWidth(), getHeight()).contains(x, y);
    }

    public ArrayList<ModuleConnectedPointUI> getModuleConnectedPointsForConnectWith(ModuleUI moduleUI) {
        ArrayList<ModuleConnectedPointUI> possiblePoints = new ArrayList<>();
        for (ModuleConnectedPointUI connectedPointUI : connectedPoints) {
            if (getIdentifier().equals(moduleUI.getIdentifier()))
                continue;

            if (connectedPointUI.getType() == ModuleConnectedPointUI.Type.OUTPUT)
                continue;

            ModuleConnectedPoint connectedPoint = connectedPointUI.getParent();
            if (connectedPoint.isFull())
                continue;

            possiblePoints.add(connectedPointUI);
        }
        return possiblePoints;
    }
}
