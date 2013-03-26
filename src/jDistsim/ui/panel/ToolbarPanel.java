package jDistsim.ui.panel;

import jDistsim.ui.control.MenuSeparator;
import jDistsim.ui.control.button.ImageButton;
import jDistsim.ui.panel.listener.ToolbarListener;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.resource.Resources;
import jDistsim.utils.ui.ListenerablePanel;
import jDistsim.utils.ui.control.IconBackgroundColorHoverStyle;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 3.10.12
 * Time: 0:24
 */
public class ToolbarPanel extends ListenerablePanel<ToolbarListener> {

    private Component relationsButton;

    private ImageButton modelSaveButton;
    private ImageButton modelOpenButton;
    private ImageButton simulationStartButton;
    private ImageButton simulationStopButton;

    public ToolbarPanel() {
        Logger.log("Initialize toolbox panel");
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
        setBorder(new EmptyBorder(0, 0, 0, 0));

        IconBackgroundColorHoverStyle hoverStyle = new IconBackgroundColorHoverStyle();
        Dimension iconDimension = new Dimension(16, 16);
        int padding = 5;

        simulationStartButton = new ImageButton(Resources.getImage("system/toolbar/simulation_start.png"), hoverStyle, iconDimension, padding, true);
        simulationStartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Logger.log("Pressed the simulation start button on toolbar");
                getListener().onSimulationStartButtonClick(mouseEvent, simulationStartButton);
            }
        });

        simulationStopButton = new ImageButton(Resources.getImage("system/toolbar/simulation_stop.png"), hoverStyle, iconDimension, padding, true);
        simulationStopButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Logger.log("Pressed the simulation stop button on toolbar");
                getListener().onSimulationStopButtonClick(mouseEvent, simulationStopButton);
            }
        });

        modelSaveButton = new ImageButton(Resources.getImage("system/toolbar-icon-save-as.png"), hoverStyle, iconDimension, padding);
        modelSaveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Logger.log("Pressed the save mode button on toolbar");
                getListener().onModelSaveButtonClick(mouseEvent, modelSaveButton);
            }
        });
        modelOpenButton = new ImageButton(Resources.getImage("system/toolbar-icon-open.png"), hoverStyle, iconDimension, padding);
        modelOpenButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Logger.log("Pressed the open button on toolbar");
                getListener().onModelOpenButtonClick(mouseEvent, modelOpenButton);
            }
        });

        add(new ImageButton(Resources.getImage("system/toolbar-icon-new.png"), hoverStyle, new Dimension(16, 16), padding));
        add(modelOpenButton);
        add(modelSaveButton);
        add(new MenuSeparator());
        relationsButton = add(new ImageButton(Resources.getImage("system/toolbar-icon-relationship.png"), hoverStyle, iconDimension, padding, true));
        add(new ImageButton(Resources.getImage("system/zajimave/application-network.png"), hoverStyle, iconDimension, padding, true));
        add(new ImageButton(Resources.getImage("system/zajimave/sitemap-application-blue.png"), hoverStyle, iconDimension, padding, true));
        add(new ImageButton(Resources.getImage("system/zajimave/flag-green.png"), hoverStyle, iconDimension, padding, true));
        add(new MenuSeparator());

        add(simulationStartButton);
        add(new ImageButton(Resources.getImage("system/zajimave/control-pause.png"), hoverStyle, iconDimension, padding, true));
        add(simulationStopButton);
        add(new MenuSeparator());
        add(new ImageButton(Resources.getImage("system/zajimave/screwdriver.png"), hoverStyle, iconDimension, padding, true));
        add(new ImageButton(Resources.getImage("system/zajimave/pp_controls_help.png"), hoverStyle, iconDimension, padding, true));

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 45);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setColor(new Color(240, 240, 240));
        graphics.fillRect(0, 0, getWidth(), getHeight());

        graphics.setColor(new Color(220, 220, 220));
        graphics.fillRect(0, getHeight() - 15, getWidth(), getHeight());

        graphics.setColor((new Color(165, 165, 165)));
        graphics.drawLine(0, getHeight() - 15, getWidth(), getHeight() - 15);

        graphics.setColor((new Color(165, 165, 165)));
        graphics.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }

    public Component getRelationsButton() {
        return relationsButton;
    }
}
