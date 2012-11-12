package jDistsim.ui.component;

import jDistsim.utils.logging.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;

/**
 * Author: Jirka Pénzeš
 * Date: 2.11.12
 * Time: 12:41
 */
public abstract class ComponentView implements IComponentView, DragGestureListener, DragSourceListener {

    private JComponent componentView;
    private DragSource dragSource;

    protected ComponentView() {
        componentView = makeView();
        dragSource = new DragSource();
        dragSource.createDefaultDragGestureRecognizer(componentView, DnDConstants.ACTION_COPY_OR_MOVE, this);
    }

    protected abstract JComponent makeView();

    @Override
    public JComponent getView() {
        return componentView;
    }

    protected void setDefaultRenderingMode(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    protected void setDefaultBasicStroke(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    protected Color getBackgroundColor() {
        return new Color(67, 201, 224);
    }

    protected Color getBorderColor() {
        return new Color(70, 127, 137);
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dragGestureEvent) {
        Logger.log();
        Transferable transferable = new StringSelection("Hello!");
        dragSource.startDrag(dragGestureEvent, DragSource.DefaultCopyDrop, transferable, this);
    }

    @Override
    public void dragEnter(DragSourceDragEvent dragSourceDragEvent) {
        Logger.log();
    }

    @Override
    public void dragOver(DragSourceDragEvent dragSourceDragEvent) {
        Logger.log();
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dragSourceDragEvent) {
        Logger.log();
    }

    @Override
    public void dragExit(DragSourceEvent dragSourceEvent) {
        Logger.log();
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent dragSourceDropEvent) {
        Logger.log();
    }
}
