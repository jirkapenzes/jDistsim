package jDistsim.ui.panel.workspace;

import jDistsim.ui.control.GradientTitle;
import jDistsim.ui.control.tabControl.TabControl;
import jDistsim.ui.control.tabControl.TabItem;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.resource.TextResources;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 25.9.12
 * Time: 21:21
 */
public class InformationPanel extends JPanel {

    public InformationPanel() {
        Logger.log("Initialize information panel");
        initializeComponents();
    }

    private void initializeComponents() {
        setBackground(new Color(240, 240, 240));
        setBorder(new EmptyBorder(1, 0, 1, 0));
        setLayout(new BorderLayout());
        add(new GradientTitle(this, TextResources.INFORMATION_PANEL_TITLE), BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 10, 5, 10));
        panel.setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        //panel1.setBackground(Color.red);

        JPanel panel2 = new JPanel();
        //panel2.setBackground(Color.blue);

        JPanel panel3 = new JPanel();
        //panel3.setBackground(Color.gray);

        JPanel panel4 = new JPanel();
        //panel4.setBackground(Color.yellow);

        panel3.setBackground(new Color(231, 231, 231));
        TabControl tabControl = new TabControl();
        tabControl.addTabItem(new TabItem("Entities", panel3));
        tabControl.addTabItem(new TabItem("Application logs", panel3));
        tabControl.addTabItem(new TabItem("Remote models", panel3));
        tabControl.addTabItem(new TabItem("Notes", panel3));

        panel.add(tabControl, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(192, 192, 192));
        g.drawLine(0, 0, getWidth(), 0);
    }
}
