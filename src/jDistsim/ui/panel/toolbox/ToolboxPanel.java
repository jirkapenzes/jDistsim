package jDistsim.ui.panel.toolbox;

import jDistsim.application.designer.model.ToolboxModel;
import jDistsim.application.designer.model.ToolboxModelItem;
import jDistsim.core.simulation.modules.IModuleDescription;
import jDistsim.ui.panel.InternalPanel;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.resource.TextResources;
import jDistsim.utils.ui.WrapLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 16:09
 */
public class ToolboxPanel extends InternalPanel {

    private ToolboxModel toolboxModel;
    private JPanel toolboxPanel;
    private List<ToolboxButton> controls;
    private List<ToolboxListener> listeners;
    private ToolboxDescriptionPanel descriptionPanel;
    private ToolboxButton currentActiveButton;

    public ToolboxPanel() {
        super(TextResources.TOOLBAR_EVENT_PANEL_TITLE);
        listeners = new ArrayList<>();
        controls = new ArrayList<>();

        Logger.log("Initialize event toolbox panel");
        initializeComponents();
    }

    private void initializeComponents() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        toolboxPanel = new JPanel();
        toolboxPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 10, 0));
        toolboxPanel.setBackground(Color.white);
        toolboxPanel.setBorder(new EmptyBorder(2, 0, 0, 0));

        scrollPane.setViewportView(toolboxPanel);
        add(scrollPane, BorderLayout.CENTER);
        descriptionPanel = new ToolboxDescriptionPanel();
        add(descriptionPanel, BorderLayout.SOUTH);

        toolboxPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                notifyUnselectedComponent();
            }
        });
    }

    public void setModel(ToolboxModel toolboxModel) {
        this.toolboxModel = toolboxModel;
        buildToolbox();
    }

    public void buildToolbox() {
        toolboxPanel.removeAll();
        List<ToolboxModelItem> toolboxModelItems = new ArrayList<>(toolboxModel.getItems());
        Collections.sort(toolboxModelItems);
        for (ToolboxModelItem item : toolboxModelItems) {
            ToolboxButton toolboxButton = new ToolboxButton(item.getComponentView(), item.getModuleFactory(), item.getModuleDescription().getTitle(), item.getIdentifier());
            toolboxButton.setMouseEnteredMode(true);
            toolboxButton.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent event) {
                    ToolboxButton source = (ToolboxButton) event.getSource();
                    source.setActive(!source.isActive());
                    notifySelectedComponent(toolboxModel.getItemByIdentifier(source.getIdentifier()));
                    if (currentActiveButton != null) {
                        if (currentActiveButton != source) {
                            resetButtonState(currentActiveButton);
                        } else {
                            currentActiveButton = null;
                            notifyUnselectedComponent();
                            return;
                        }
                    }
                    currentActiveButton = source;
                }

                @Override
                public void mousePressed(MouseEvent event) {
                    ToolboxButton source = (ToolboxButton) event.getSource();
                    notifyPressedComponent(toolboxModel.getItemByIdentifier(source.getIdentifier()));
                }

                @Override
                public void mouseReleased(MouseEvent event) {
                    ToolboxButton source = (ToolboxButton) event.getSource();
                    notifyReleasedComponent(toolboxModel.getItemByIdentifier(source.getIdentifier()));
                }
            });
            toolboxButton.setPreferredSize(new Dimension(75, 65));
            toolboxPanel.add(toolboxButton);
            controls.add(toolboxButton);
        }
    }

    public void resetButtonsState() {
        for (ToolboxButton toolboxButton : controls) {
            resetButtonState(toolboxButton);
        }
    }

    private void resetButtonState(ToolboxButton toolboxButton) {
        toolboxButton.setActive(false);
        // toolboxButton.setMouseEnteredMode(false);
    }

    public void setDescriptionText(IModuleDescription description) {
        descriptionPanel.setModuleDescription(description);
    }

    public void addToolboxListener(ToolboxListener toolboxListener) {
        listeners.add(toolboxListener);
    }

    private void notifySelectedComponent(ToolboxModelItem toolboxModelItem) {
        for (ToolboxListener listener : listeners) {
            listener.componentSelected(toolboxModelItem);
        }
    }

    private void notifyUnselectedComponent() {
        for (ToolboxListener listener : listeners) {
            listener.componentUnselected();
        }
    }

    private void notifyPressedComponent(ToolboxModelItem toolboxModelItem) {
        for (ToolboxListener listener : listeners) {
            listener.componentPressed(toolboxModelItem);
        }
    }

    private void notifyReleasedComponent(ToolboxModelItem toolboxModelItem) {
        for (ToolboxListener listener : listeners) {
            listener.componentReleased(toolboxModelItem);
        }
    }
}
