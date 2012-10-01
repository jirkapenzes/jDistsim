package jDistsim.designer.ui.control.tabControl;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 26.9.12
 * Time: 18:53
 */
public class TabControl extends JComponent {

    private TabGroupControl tabGroupControl;
    private TabPanel tabPanel;

    public TabControl() {
        setLayout(new BorderLayout());

        tabPanel = new TabPanel();
        add(tabPanel, BorderLayout.CENTER);

        JPanel tabGroupContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        add(tabGroupContainer, BorderLayout.NORTH);
        tabGroupControl = new TabGroupControl(tabPanel);
        tabGroupContainer.add(tabGroupControl);
    }

    public void addTabItem(TabItem tabItem) {
        tabGroupControl.addTabItem(tabItem);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
