package jDistsim.ui.panel.tab;

import jDistsim.ui.control.button.ImageButton;
import jDistsim.ui.panel.EnvironmentPanel;
import jDistsim.ui.panel.OutputPanel;
import jDistsim.ui.panel.container.LightContainer;
import jDistsim.ui.panel.listener.OutputTabListener;
import jDistsim.utils.resource.Resources;
import jDistsim.utils.ui.ListenerablePanel;
import jDistsim.utils.ui.control.IconBackgroundColorHoverStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 19.2.13
 * Time: 0:40
 */
public class OutputTabPanel extends ListenerablePanel<OutputTabListener> {

    private int titleHeight = 20;

    public OutputTabPanel(JTextArea textArea) {
        initialize(textArea);
    }

    private void initialize(JTextArea textArea) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5));

        LightContainer environmentContainer = new LightContainer("Environment", titleHeight, new EnvironmentPanel());
        environmentContainer.setPreferredSize(new Dimension(190, getHeight()));

        LightContainer outputContainer = new LightContainer("Simulator output", titleHeight, new OutputPanel(textArea));
        outputContainer.setImageButton(new ImageButton(Resources.getImage("system/panels/ip_new_window.png"), new IconBackgroundColorHoverStyle(), new Dimension(12, 12), 4));

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(false);
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 3));
        contentPane.add(environmentContainer, BorderLayout.CENTER);

        add(contentPane, BorderLayout.WEST);
        add(outputContainer, BorderLayout.CENTER);
    }
}
