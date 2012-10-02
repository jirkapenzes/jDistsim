package jDistsim.designer.ui.form;

import jDistsim.designer.ui.MenuBar;
import jDistsim.designer.ui.StatusBar;
import jDistsim.designer.ui.panel.EventToolbar;
import jDistsim.designer.ui.panel.InternalPanel;
import jDistsim.designer.ui.panel.ToolbarPanel;
import jDistsim.designer.ui.panel.WorkSpacePanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 18.9.12
 * Time: 23:00
 */
public class DesignerForm extends JFrame {

    public DesignerForm(String title) {
        initializeComponents();
        setTitle(title);
    }

    private void initializeComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 300));
        setJMenuBar(new MenuBar(this));
        setLayout(new BorderLayout());


        add(new ToolbarPanel(), BorderLayout.NORTH);
        add(new StatusBar(), BorderLayout.SOUTH);
        add(new InternalPanel("Information"), BorderLayout.EAST);
        add(new EventToolbar(), BorderLayout.WEST);
        add(new WorkSpacePanel(), BorderLayout.CENTER);

        pack();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 600);
    }
}
