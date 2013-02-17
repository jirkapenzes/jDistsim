package jDistsim.ui;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 24.9.12
 * Time: 21:22
 */
public class MenuBar extends JMenuBar {

    public MenuBar() {
        initializeMenuBar();
    }

    private void initializeMenuBar() {
        JMenu menu;
        JMenuItem menuItem;

        menu = new JMenu("File");
        menuItem = new JMenuItem("New model");
        menu.add(menuItem);

        JMenu viewMenu = new JMenu("View");
        JCheckBoxMenuItem toolbox = new JCheckBoxMenuItem("Toolbox");
        JCheckBoxMenuItem properties = new JCheckBoxMenuItem("Properties");
        JCheckBoxMenuItem modules = new JCheckBoxMenuItem("Modules navigator");
        JCheckBoxMenuItem information = new JCheckBoxMenuItem("Model information");

        viewMenu.add(toolbox);
        viewMenu.add(properties);
        viewMenu.add(modules);
        viewMenu.add(information);

        add(menu);
        add(viewMenu);
    }
}
