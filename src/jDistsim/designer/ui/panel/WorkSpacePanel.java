package jDistsim.designer.ui.panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 24.9.12
 * Time: 22:28
 */
public class WorkSpacePanel extends JPanel {

    public WorkSpacePanel() {
        initializeComponents();
    }

    private void initializeComponents() {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setLayout(new BorderLayout());
        InformationPanel informationPanel = new InformationPanel();
        add(informationPanel, BorderLayout.SOUTH);

        ModelSpacePanel modelSpacePanel = new ModelSpacePanel();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollPane.setViewportView(modelSpacePanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());


    }
}
