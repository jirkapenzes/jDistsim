package jDistsim.ui.panel.toolbox;

import jDistsim.application.designer.model.ToolboxModel;
import jDistsim.application.designer.model.ToolboxModelItem;
import jDistsim.ui.panel.InternalPanel;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.resource.TextResources;
import jDistsim.utils.ui.WrapLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 16:09
 */
public class ToolboxPanel extends InternalPanel {

    private JPanel controls;
    private ToolboxModel toolboxModel;

    public ToolboxPanel() {
        super(TextResources.TOOLBAR_EVENT_PANEL_TITLE);
        Logger.log("Initialize event toolbox panel");
        initializeComponents();
    }

    private void initializeComponents() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        controls = new JPanel();
        controls.setLayout(new WrapLayout(FlowLayout.LEFT, 10, 0));
        controls.setBackground(Color.white);
        controls.setBorder(new EmptyBorder(2, 0, 0, 0));

        scrollPane.setViewportView(controls);
        add(scrollPane, BorderLayout.CENTER);
        add(new ToolboxDescriptionPanel(), BorderLayout.SOUTH);
    }

    public void setModel(ToolboxModel toolboxModel) {
        this.toolboxModel = toolboxModel;

        controls.removeAll();
        for (ToolboxModelItem item : toolboxModel.getItems()) {
            ToolboxButton toolboxButton = new ToolboxButton(item.getComponentView(), "Create");
            toolboxButton.setMouseEnteredMode(true);
            toolboxButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    ToolboxButton source = (ToolboxButton) event.getSource();
                    source.setActive(!source.isActive());
                }
            });
            toolboxButton.setPreferredSize(new Dimension(75, 65));
            controls.add(toolboxButton);
        }
    }
}
