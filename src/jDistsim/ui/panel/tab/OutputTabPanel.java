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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 19.2.13
 * Time: 0:40
 */
public class OutputTabPanel extends ListenerablePanel<OutputTabListener> {

    private ImageButton copyToClipboardButton;
    private ImageButton scrollToEndButton;
    private ImageButton trashButton;
    private ImageButton localStatistics;
    private ImageButton networkStatistics;
    private ImageButton modulesStatistics;
    private ImageButton distributedStatistics;
    private JTextArea textArea;
    private JTable table;

    private LightContainer outputContainer;
    private EnvironmentPanel environmentPanel;

    public OutputTabPanel(JTextArea textArea, JTable table) {
        this.textArea = textArea;
        this.table = table;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5));

        LightContainer environmentContainer = makeEnvironment(table);
        environmentContainer.setPreferredSize(new Dimension(190, getHeight()));

        outputContainer = makeSimulatorOutput();


        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(false);
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 3));
        contentPane.add(environmentContainer, BorderLayout.CENTER);

        add(contentPane, BorderLayout.WEST);
        add(outputContainer, BorderLayout.CENTER);
    }

    public LightContainer getOutputContainer() {
        return outputContainer;
    }

    private LightContainer makeSimulatorOutput() {
        final OutputPanel outputPanel = new OutputPanel(textArea);
        final LightContainer outputContainer = new LightContainer("Simulator output", outputPanel);

        IconBackgroundColorHoverStyle buttonHoverStyle = new IconBackgroundColorHoverStyle();
        int buttonPadding = 3;

        copyToClipboardButton = new ImageButton(Resources.getImage("system/panels/lp_copy.png"), buttonHoverStyle, new Dimension(16, 16), buttonPadding);
        copyToClipboardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                getListener().onCopyToClipboardButtonClick(copyToClipboardButton, mouseEvent);
            }
        });

        scrollToEndButton = new ImageButton(Resources.getImage("system/panels/lp_dock.png"), buttonHoverStyle, new Dimension(16, 16), buttonPadding);
        scrollToEndButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                getListener().onScrollToEndButtonClick(scrollToEndButton, outputPanel.getLogTextArea(), mouseEvent);
            }
        });

        trashButton = new ImageButton(Resources.getImage("system/panels/lp_trash.png"), buttonHoverStyle, new Dimension(16, 16), buttonPadding);
        trashButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                getListener().onTrashButtonClick(trashButton, mouseEvent);
            }
        });

        outputContainer.setImageButton(new ImageButton(Resources.getImage("system/panels/ip_new_window.png"), new IconBackgroundColorHoverStyle(), new Dimension(16, 16), 4));
        outputContainer.getImageButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                getListener().onSimulatorOutputDialogOpenButtonClick(outputContainer.getImageButton(), mouseEvent);
            }
        });

        outputPanel.getLogTextArea().getControlPanel().addButton(copyToClipboardButton);
        outputPanel.getLogTextArea().getControlPanel().addButton(scrollToEndButton);
        outputPanel.getLogTextArea().getControlPanel().addButton(trashButton);

        return outputContainer;
    }

    private LightContainer makeEnvironment(JTable table) {
        environmentPanel = new EnvironmentPanel(table);
        LightContainer environmentContainer = new LightContainer("Environment", environmentPanel);

        IconBackgroundColorHoverStyle buttonHoverStyle = new IconBackgroundColorHoverStyle();
        int buttonPadding = 3;

        localStatistics = new ImageButton(Resources.getImage("system/panels/op_other.png"), buttonHoverStyle, new Dimension(16, 16), buttonPadding);
        localStatistics.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                getListener().onLocalEnvironmentButtonClick(localStatistics, mouseEvent);
            }
        });
        modulesStatistics = new ImageButton(Resources.getImage("system/panels/op_main.png"), buttonHoverStyle, new Dimension(16, 16), buttonPadding);
        modulesStatistics.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                getListener().onModulesEnvironmentButtonClick(modulesStatistics, mouseEvent);
            }
        });
        distributedStatistics = new ImageButton(Resources.getImage("system/panels/op_remote.png"), buttonHoverStyle, new Dimension(16, 16), buttonPadding);
        distributedStatistics.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                getListener().onMessagesEnvironmentButtonClick(distributedStatistics, mouseEvent);
            }
        });
        networkStatistics = new ImageButton(Resources.getImage("system/panels/op_local.png"), buttonHoverStyle, new Dimension(16, 16), buttonPadding);
        networkStatistics.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                getListener().onNetworkEnvironmentButtonClick(networkStatistics, mouseEvent);
            }
        });


        environmentPanel.getControlPanel().add(localStatistics);
        environmentPanel.getControlPanel().add(modulesStatistics);
        environmentPanel.getControlPanel().add(networkStatistics);
        environmentPanel.getControlPanel().add(distributedStatistics);

        return environmentContainer;
    }

    public ImageButton getCopyToClipboardButton() {
        return copyToClipboardButton;
    }

    public ImageButton getScrollToEndButton() {
        return scrollToEndButton;
    }

    public ImageButton getTrashButton() {
        return trashButton;
    }

    public ImageButton getLocalStatistics() {
        return localStatistics;
    }

    public ImageButton getNetworkStatistics() {
        return networkStatistics;
    }

    public ImageButton getModulesStatistics() {
        return modulesStatistics;
    }

    public ImageButton getDistributedStatistics() {
        return distributedStatistics;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void renderEnvironmentTable() {
        environmentPanel.renderTable();
    }
}
