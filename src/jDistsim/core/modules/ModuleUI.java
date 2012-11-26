package jDistsim.core.modules;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 17:14
 */
public class ModuleUI extends JComponent {

    private boolean active;
    private IModuleView moduleView;
    private String identifier;
    private Point mousePositionDown;

    public ModuleUI(IModuleView moduleView, String identifier) {
        this.moduleView = moduleView;
        this.identifier = identifier;
        initializeUI();
    }

    private void initializeUI() {
        setSize(80, 50);

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                Point newPosition = getLocation();
                newPosition.translate(mouseEvent.getX() - mousePositionDown.x, mouseEvent.getY() - mousePositionDown.y);
                setLocation(newPosition);
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                switch (e.getButton()) {
                    case MouseEvent.BUTTON1:
                        mousePositionDown = new Point(e.getX(), e.getY());
                        repaint();
                        break;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }
        });
    }

    public void setActive(boolean active) {
        this.active = active;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        moduleView.draw(graphics2D, getWidth(), getHeight());

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
        drawCenteredString(identifier, getWidth(), getHeight(), graphics2D);
    }

    public void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
        repaint();
    }

    public String getIdentifier() {
        return identifier;
    }

    public boolean getActive() {
        return active;
    }

    @Override
    public boolean contains(int x, int y) {
        return moduleView.getBounds(getWidth(), getHeight()).contains(x, y);
    }
}
