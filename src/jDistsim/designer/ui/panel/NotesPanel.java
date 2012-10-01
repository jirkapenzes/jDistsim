package jDistsim.designer.ui.panel;

import jDistsim.utils.logging.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 27.9.12
 * Time: 21:16
 */
public class NotesPanel extends JPanel {

    private JTextArea jTextArea;

    public NotesPanel() {
        initializeComponents();
        Logger.log("Initialize");
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setMinimumSize(new Dimension(100, 100));
        jTextArea = new JTextArea();
        add(jTextArea, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //  g.fillRect(0, 0, getWidth(),getHeight());
    }
}
