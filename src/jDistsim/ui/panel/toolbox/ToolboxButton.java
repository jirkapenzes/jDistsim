package jDistsim.ui.panel.toolbox;

import jDistsim.core.simulation.modules.IModuleFactory;
import jDistsim.core.simulation.modules.IModuleView;
import jDistsim.utils.common.TransferableObject;
import jDistsim.utils.logging.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

/**
 * Author: Jirka Pénzeš
 * Date: 4.11.12
 * Time: 15:38
 */
public class ToolboxButton extends JComponent implements DragGestureListener, DragSourceListener {

    private static final int DefaultSize = 50;
    private static final int DefaultPadding = 5;
    private static final int FontIndentation = 15;

    private IModuleView moduleView;
    private boolean active = false;
    private boolean mouseEntered = false;
    private boolean mouseEnteredMode = true;
    private int padding;
    private String title;
    private String identifier;

    private DragSource dragSource;
    private IModuleFactory moduleFactory;

    public ToolboxButton(IModuleView moduleView, IModuleFactory moduleFactory, String title, String identifier) {
        this(moduleView, moduleFactory, title, identifier, DefaultPadding, DefaultSize, DefaultSize);
    }

    public ToolboxButton(IModuleView moduleView, IModuleFactory moduleFactory, String title, String identifier, int padding, int width, int height) {
        this.moduleView = moduleView;
        this.title = title;
        this.padding = padding;
        this.identifier = identifier;
        this.moduleFactory = moduleFactory;
        setPreferredSize(new Dimension(width, height));
        initialize();

        dragSource = new DragSource();
        dragSource.createDefaultDragGestureRecognizer(moduleView.getContentPane(), DnDConstants.ACTION_COPY_OR_MOVE, this);
    }

    private void initialize() {
        resizeComponentView();
        add(moduleView.getContentPane());
        moduleView.getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                MouseEvent mouseEvent = new MouseEvent(ToolboxButton.this, event.getID(), event.getWhen(), event.getModifiers(), event.getX(), event.getY(), event.getClickCount(), event.isPopupTrigger());
                for (MouseListener listener : ToolboxButton.this.getMouseListeners()) {
                    listener.mouseClicked(mouseEvent);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ToolboxButton.this.mouseEntered();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ToolboxButton.this.mouseExited();
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponentView();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ToolboxButton.this.mouseEntered();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ToolboxButton.this.mouseExited();
            }

        });
    }

    private void mouseEntered() {
        if (mouseEnteredMode) {
            mouseEntered = true;
            repaint();
        }
    }

    private void mouseExited() {
        if (mouseEnteredMode) {
            mouseEntered = false;
            repaint();
        }
    }

    public void setMouseEnteredMode(boolean mouseEnteredMode) {
        this.mouseEnteredMode = mouseEnteredMode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        repaint();
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
        resizeComponentView();
    }

    public String getIdentifier() {
        return identifier;
    }

    private void resizeComponentView() {
        moduleView.getContentPane().setLocation(getPadding(), getPadding());
        moduleView.getContentPane().setSize(new Dimension(getWidth() - (getPadding() * 2), getHeight() - (getPadding() * 2) - FontIndentation));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (active || mouseEntered) {
            BasicStroke lineStroke = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, new float[]{5, 5}, 0);
            graphics2D.setStroke(lineStroke);

            if (active)
                graphics2D.setColor(new Color(217, 245, 249));
            else if (mouseEntered)
                graphics2D.setColor(new Color(242, 252, 253));

            graphics2D.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            graphics2D.setColor(new Color(0, 187, 221));
            graphics2D.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }

        Font titleFont = new Font("Calibri", Font.PLAIN, 12);
        graphics2D.setFont(titleFont);

        graphics2D.setColor(new Color(58, 58, 58));
        FontRenderContext context = graphics2D.getFontRenderContext();
        Rectangle2D bounds = new TextLayout(title, titleFont, context).getBounds();
        graphics2D.drawString(title, (int) ((getWidth() - bounds.getWidth()) / 2.0), getHeight() - 6);
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dragGestureEvent) {
        Logger.log();
        Transferable transferable = new TransferableObject(moduleFactory);
        dragGestureEvent.startDrag(DragSource.DefaultCopyDrop, transferable, this);
    }

    @Override
    public void dragEnter(DragSourceDragEvent dragSourceDragEvent) {
    }

    @Override
    public void dragOver(DragSourceDragEvent dragSourceDragEvent) {
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dragSourceDragEvent) {
    }

    @Override
    public void dragExit(DragSourceEvent dragSourceEvent) {
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent dragSourceDropEvent) {
        MouseEvent mouseEvent = new MouseEvent(moduleView.getContentPane(), 0, 0, 0, dragSourceDropEvent.getX(), dragSourceDropEvent.getY(), 0, false);
        for (MouseListener listener : moduleView.getContentPane().getMouseListeners()) {
            listener.mouseExited(mouseEvent);
        }
    }
}
