package jDistsim.designer.ui.form;

import jDistsim.core.model.EventToolbarModel;
import jDistsim.core.module.EventToolbarModule;
import jDistsim.core.simulation.event.description.CreateEventDescription;
import jDistsim.core.simulation.event.description.DisposeDescription;
import jDistsim.core.simulation.event.ui.preview.CreateUIEventPreview;
import jDistsim.core.simulation.event.ui.preview.DisposeUIEventPreview;
import jDistsim.designer.ui.MenuBar;
import jDistsim.designer.ui.StatusBar;
import jDistsim.designer.ui.panel.EventToolbar;
import jDistsim.designer.ui.panel.InternalPanel;
import jDistsim.designer.ui.panel.ToolbarPanel;
import jDistsim.designer.ui.panel.WorkSpacePanel;
import jDistsim.utils.logging.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 18.9.12
 * Time: 23:00
 */
public class DesignerForm extends JFrame {

    public DesignerForm(String title) {
        Logger.log("Initialize GUI designer");
        initializeComponents();
        setTitle(title);
        Logger.log("Designer GUI application initialization is complete");
    }

    private void initializeComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 300));
        setJMenuBar(new MenuBar(this));
        setLayout(new BorderLayout());

        EventToolbarModel eventToolbarModel = new EventToolbarModel();
        eventToolbarModel
                .addEventToolbarModule(new EventToolbarModule(new CreateUIEventPreview(new CreateEventDescription())))
                .addEventToolbarModule(new EventToolbarModule(new DisposeUIEventPreview(new DisposeDescription())));

        add(new ToolbarPanel(), BorderLayout.NORTH);
        add(new StatusBar(), BorderLayout.SOUTH);
        add(new InternalPanel("Information"), BorderLayout.EAST);
        add(new EventToolbar(eventToolbarModel), BorderLayout.WEST);
        add(new WorkSpacePanel(), BorderLayout.CENTER);

        pack();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 600);
    }
}
