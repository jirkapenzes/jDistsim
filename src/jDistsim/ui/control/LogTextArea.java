package jDistsim.ui.control;

import jDistsim.ui.control.button.ImageButton;
import jDistsim.ui.skins.ScrollBarUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 11.3.13
 * Time: 13:49
 */
public class LogTextArea extends JComponent {

    private ControlPanel controlPanel;
    private TextAreaPanel textAreaPanel;
    private boolean border;
    private JTextArea textArea;

    public LogTextArea() {
        this(true);
    }

    public LogTextArea(boolean border) {
        this(border, new JTextArea());
    }

    public LogTextArea(boolean border, JTextArea textArea) {
        this.textArea = textArea;
        this.border = border;

        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        controlPanel = new ControlPanel();
        controlPanel.setPreferredSize(new Dimension(40, controlPanel.getHeight()));
        textAreaPanel = new TextAreaPanel();

        add(controlPanel, BorderLayout.WEST);
        add(textAreaPanel, BorderLayout.CENTER);
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setAutoCaretPosition(boolean autoCaretPosition) {
        textAreaPanel.setCaret(autoCaretPosition);
        textAreaPanel.setAutoCaretPosition(autoCaretPosition);
    }

    public void setWordWrap(boolean wordWrap) {
        textAreaPanel.setWordWrap(wordWrap);
    }

    public void setCaretPosition() {
        textAreaPanel.setCaretPosition();
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public class ControlPanel extends JComponent {

        public ControlPanel() {
            setLayout(new FlowLayout(FlowLayout.LEADING));
        }

        public void addButton(ImageButton imageButton) {
            add(imageButton);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            int rightPadding = 6;

            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            graphics2D.setColor(new Color(214, 214, 214));
            graphics2D.fillRect(0, 0, getWidth(), getHeight());

            graphics2D.setColor(new Color(240, 240, 240));
            graphics2D.fillRect(getWidth() - rightPadding, 0, getWidth(), getHeight());

            if (border) {
                graphics2D.setColor(new Color(192, 192, 192));
                graphics2D.drawLine(0, 0, getWidth() - 1, 0);
                graphics2D.drawLine(0, 0, 0, getHeight() - 1);
                graphics2D.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
            }
            graphics2D.setColor(new Color(128, 128, 128));
            Stroke currentStroke = graphics2D.getStroke();
            BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{2.0f}, 0.0f);
            graphics2D.setStroke(dashed);

            graphics2D.drawLine(getWidth() - 1, 1, getWidth() - 1, getHeight() - 2);
            graphics2D.setStroke(currentStroke);
        }
    }

    public class TextAreaPanel extends JComponent {

        private boolean isCaret;

        public TextAreaPanel() {
            setBorder(new EmptyBorder(1, 1, 1, 1));

            textArea.setFont(new Font("Consolas", Font.PLAIN, 11));
            textArea.setBorder(new EmptyBorder(3, 6, 1, 2));
            textArea.setForeground(new Color(40, 40, 40));
            //textArea.setEnabled(false);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
            scrollPane.getHorizontalScrollBar().setUI(new ScrollBarUI());
            scrollPane.setBorder(null);

            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;

            graphics2D.setColor(Color.white);
            graphics2D.fillRect(0, 0, getWidth(), getHeight());

            if (border) {
                graphics2D.setColor(new Color(192, 192, 192));
                graphics2D.drawLine(0, 0, getWidth() - 1, 0);
                graphics2D.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
                graphics2D.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
            }
        }

        public JTextArea getTextArea() {
            return textArea;
        }

        public void setAutoCaretPosition(boolean autoCaretPosition) {
            DefaultCaret caret = (DefaultCaret) textArea.getCaret();
            if (autoCaretPosition) {
                caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
            } else {
                caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
            }
        }

        public void setWordWrap(boolean wordWrap) {
            textArea.setLineWrap(wordWrap);
            textArea.setWrapStyleWord(wordWrap);
        }

        private void setCaretPosition() {
            if (isCaret()) {
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
        }

        public boolean isCaret() {
            return isCaret;
        }

        public void setCaret(boolean caret) {
            isCaret = caret;
        }
    }
}
