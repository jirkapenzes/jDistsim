package jDistsim.core.simulation.modules.ui;


import jDistsim.application.designer.common.UIConfiguration;
import jDistsim.core.simulation.modules.IModuleView;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConnectedPoint;
import jDistsim.core.simulation.modules.common.ModuleProperty;
import jDistsim.ui.module.ColorScheme;
import jDistsim.utils.pattern.observer.IObservable;
import jDistsim.utils.pattern.observer.IObserver;
import jDistsim.utils.pattern.observer.Observable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 17:14
 */
public class ModuleUI extends JComponent implements IObservable, IObserver {

    private Observable observable;
    private boolean active;
    private Module module;
    private IModuleView view;
    private final List<ModuleConnectedPointUI> connectedPoints;

    public ModuleUI(Module module, IModuleView view) {
        this.module = module;
        this.view = view;
        this.connectedPoints = new ArrayList<>();
        this.observable = new Observable();
        module.addObserver(this);
        initializeUI();
    }

    public boolean isCreateModule() {
        return module.isCreateModule();
    }

    private void initializeUI() {
        setSize(80, 50);

        view.invalidateConnectedPoints(80, 50);
        for (int index = 0; index < module.getInputConnectedPoints().size(); index++) {
            ModuleConnectedPoint connectedPoint = (ModuleConnectedPoint) module.getInputConnectedPoints().get(index);
            connectedPoints.add(new ModuleConnectedPointUI(connectedPoint, ModuleConnectedPointUI.Type.INPUT, view.getInputPoints().get(index), this));
        }

        for (int index = 0; index < module.getOutputConnectedPoints().size(); index++) {
            ModuleConnectedPoint connectedPoint = (ModuleConnectedPoint) module.getOutputConnectedPoints().get(index);
            connectedPoints.add(new ModuleConnectedPointUI(connectedPoint, ModuleConnectedPointUI.Type.OUTPUT, view.getOutputPoints().get(index), this));
        }
    }

    public List<ModuleProperty> getProperties() {
        List<ModuleProperty> properties = new ArrayList<>();
        properties.add(new ModuleProperty("moduleUI.x", getX(), "location x"));
        properties.add(new ModuleProperty("moduleUI.y", getY(), "location y"));
        properties.addAll(getModule().getProperties().getAll());
        Collections.sort(properties);
        return properties;
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
        view.draw(graphics2D, getWidth(), getHeight());

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
        module.getSettings().setIdentifier(identifier);
        repaint();
    }

    public void setActiveBackgroundColor() {
        view.setColorScheme(UIConfiguration.getInstance().getColorSchemeForActiveModule());
        repaint();
    }

    public void setDefaultBackgroundColor() {
        view.setDefaultColorScheme();
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
        return view.getBounds(getWidth(), getHeight()).contains(x, y);
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

    public ModuleConnectedPointUI getModuleConnectedPointUI(int index, ModuleConnectedPointUI.Type type) {
        return type == ModuleConnectedPointUI.Type.INPUT ? getInputPoints().get(index) : getOutputPoints().get(index);
    }

    public void setColorScheme(ColorScheme colorScheme) {
        view.setColorScheme(colorScheme);
        repaint();
    }

    public void setDefaultColorScheme() {
        view.setDefaultColorScheme();
        repaint();
    }

    @Override
    public void addObserver(IObserver observer) {
        observable.addObserver(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observable.removeObserver(observer);
    }

    @Override
    public int countObservers() {
        return observable.countObservers();
    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }

    @Override
    public void notifyObservers(Object argument) {
        observable.notifyObservers(argument);
    }

    @Override
    public void update(Observable observable, Object arguments) {
        notifyObservers(arguments);
    }
}
