package jDistsim.ui.panel.toolbox;

import jDistsim.core.model.ToolboxModel;
import jDistsim.core.module.IEventToolbarModule;
import jDistsim.core.simulation.event.description.EmptyDescription;
import jDistsim.core.simulation.event.description.IEventDescription;
import jDistsim.application.designer.common.UIConfiguration;
import jDistsim.ui.control.event.ToolbarEventPreviewControl;
import jDistsim.ui.panel.InternalPanel;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.resource.TextResources;
import jDistsim.utils.ui.WrapLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 16:09
 */
public class ToolboxPanel extends InternalPanel {

    private ToolboxModel toolboxModel;

    public ToolboxPanel(ToolboxModel toolboxModel) {
        super(TextResources.TOOLBAR_EVENT_PANEL_TITLE);
        Logger.log("Initialize event toolbox panel");
        this.toolboxModel = toolboxModel;
        initializeComponents();
    }

    private void initializeComponents() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        JPanel controls = new JPanel();
        controls.setLayout(new WrapLayout(FlowLayout.LEFT, 10, 0));
        controls.setBackground(Color.white);
        controls.setBorder(new EmptyBorder(10, 0, 0, 0));

        scrollPane.setViewportView(controls);
        add(scrollPane, BorderLayout.CENTER);
        add(new ToolboxDescriptionPanel(), BorderLayout.SOUTH);

        for (IEventToolbarModule toolbarModule : toolboxModel.getModules()) {
            controls.add(new ToolbarEventPreviewControl(toolbarModule.getEventPreview()));
        }
    }
}
