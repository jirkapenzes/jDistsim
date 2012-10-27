package jDistsim.ui.panel.workspace;

import jDistsim.utils.logging.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 24.9.12
 * Time: 22:28
 */
public class WorkSpacePanel extends JPanel {

    private InformationPanel informationPanel;
    private ModelSpacePanel modelSpacePanel;

    public WorkSpacePanel(ModelSpacePanel modelSpacePanel, InformationPanel informationPanel) {
        Logger.log("Initialize workspace panel");
        this.informationPanel = informationPanel;
        this.modelSpacePanel = modelSpacePanel;
        initializeComponents();
    }

    private void initializeComponents() {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setLayout(new BorderLayout());
        add(informationPanel, BorderLayout.SOUTH);
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
