package jDistsim.ui.panel.tab;

import jDistsim.ui.skins.ScrollBarUI;
import jDistsim.utils.ui.ListenerablePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 15.2.13
 * Time: 21:27
 */
public class NotesTabPanel extends ListenerablePanel {

    public NotesTabPanel() {
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5));

        add(new Header(), BorderLayout.NORTH);
        add(new NotesArea(), BorderLayout.CENTER);
        add(new Bottom(), BorderLayout.SOUTH);
    }

    private class Header extends JComponent {

        public Header() {
            setPreferredSize(new Dimension(getWidth(), 14));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.setColor(new Color(255, 255, 204));
            graphics2D.fillRect(0, 0, getWidth(), getHeight());

            graphics2D.setColor(new Color(192, 192, 192));
            graphics2D.drawLine(0, 0, getWidth(), 0);
            graphics2D.drawLine(0, 0, 0, getHeight());
            graphics2D.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);

            graphics2D.setColor(new Color(100, 100, 100));
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setFont(new Font("Consolas", Font.PLAIN, 10));
            //graphics2D.drawString("Last saved on 16.11.2013 23:10", getWidth() - 185, 12);
            graphics2D.drawString("Last saved on 16.11.2013 23:10", 5, 12);
        }
    }

    private class NotesArea extends JComponent {

        public NotesArea() {
            setLayout(new BorderLayout());
            setBorder(new EmptyBorder(0, 1, 0, 1));

            JTextArea textArea = new JTextArea() {
                @Override
                protected void paintComponent(Graphics graphics) {
                    super.paintComponent(graphics);
                    graphics.setColor(new Color(200, 200, 200));
                    for (int index = 1; index < getHeight(); index++) {
                        graphics.drawLine(10, (index * 16), getWidth() - 10, (index * 16));
                    }
                }
            };
            textArea.setOpaque(false);
            textArea.setBorder(new EmptyBorder(0, 10, 0, 10));
            textArea.setFont(new Font("Calibri", Font.PLAIN, 12));
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.getViewport().setBackground(new Color(255, 255, 204));
            scrollPane.setBorder(null);
            add(scrollPane, BorderLayout.CENTER);
        }


        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            graphics.setColor(new Color(255, 255, 204));
            graphics.fillRect(0, 0, getWidth(), getHeight());

            graphics.setColor(new Color(192, 192, 192));
            graphics.drawLine(0, 0, 0, getHeight() - 1);
            graphics.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
        }
    }

    private class Bottom extends JComponent {

        public Bottom() {
            setPreferredSize(new Dimension(getWidth(), 20));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            graphics2D.setColor(new Color(214, 214, 214));
            graphics2D.fillRect(0, 0, getWidth(), getHeight());

            graphics2D.setColor(new Color(240, 240, 240));
            graphics2D.fillRect(0, 0, getWidth(), 6);

            graphics2D.setColor(new Color(192, 192, 192));
            graphics2D.drawLine(0, 0, 0, getHeight() - 1);
            graphics2D.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
            graphics2D.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);

            graphics2D.setColor(new Color(128, 128, 128));
            Stroke currentStroke = graphics2D.getStroke();
            BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{3.0f}, 0.0f);
            graphics2D.setStroke(dashed);

            graphics2D.drawLine(0, 1, getWidth() - 1, 1);
            graphics2D.setStroke(currentStroke);

            graphics2D.setColor(new Color(100, 100, 100));
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setFont(new Font("Consolas", Font.PLAIN, 10));
            graphics2D.drawString("The notes are saved automatically with the project", 5, 16);

        }
    }
}
