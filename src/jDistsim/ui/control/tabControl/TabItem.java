package jDistsim.ui.control.tabControl;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 26.9.12
 * Time: 19:44
 */
public class TabItem {

    private String name;
    private JPanel panel;

    public TabItem(String name, JPanel panel) {
        this.name = name;
        this.panel = panel;
    }

    public String getName() {
        return name;
    }

    public JPanel getPanel() {
        return panel;
    }
}
