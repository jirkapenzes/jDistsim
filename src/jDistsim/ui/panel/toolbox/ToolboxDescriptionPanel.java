package jDistsim.ui.panel.toolbox;

import jDistsim.application.designer.common.UIConfiguration;
import jDistsim.core.simulation.modules.EmptyDescription;
import jDistsim.core.simulation.modules.IModuleDescription;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 27.10.12
 * Time: 23:56
 */
public class ToolboxDescriptionPanel extends JPanel {

    private IModuleDescription moduleDescription;
    private JLabel labelTitle;
    private JLabel labelDescription;

    public ToolboxDescriptionPanel() {
        this(new EmptyDescription());
    }

    public ToolboxDescriptionPanel(IModuleDescription moduleDescription) {
        initializeComponents();
        setModuleDescription(moduleDescription);
    }

    private void initializeComponents() {
        setBorder(new EmptyBorder(10, 5, 10, 5));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Font font = UIConfiguration.getInstance().getDefaultFont();
        labelTitle = new JLabel();
        labelTitle.setFont(font);

        labelDescription = new JLabel();
        labelDescription.setFont(font);

        add(labelTitle);
        add(labelDescription);
    }

    public IModuleDescription getModuleDescription() {
        return moduleDescription;
    }

    public void setModuleDescription(IModuleDescription moduleDescription) {
        this.moduleDescription = moduleDescription;

        labelTitle.setText(getModuleDescription().getTitle());
        labelDescription.setText(getModuleDescription().getDescription());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(192, 192, 192));
        g.drawLine(0, 0, getWidth(), 0);
        g.setColor(new Color(240, 240, 240));
        g.fillRect(0, 1, getWidth(), getHeight());
    }
}