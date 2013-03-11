package jDistsim.ui.panel.tab;

import jDistsim.ui.control.LogTextArea;
import jDistsim.ui.control.button.ImageButton;
import jDistsim.ui.panel.listener.LogTabListener;
import jDistsim.utils.resource.Resources;
import jDistsim.utils.ui.ListenerablePanel;
import jDistsim.utils.ui.control.IconBackgroundColorHoverStyle;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 13.2.13
 * Time: 23:03
 */
public class LogTabPanel extends ListenerablePanel<LogTabListener> {

    private ImageButton wordWrapButton;
    private ImageButton copyToClipboardButton;
    private ImageButton scrollToEndButton;
    private ImageButton trashButton;
    private LogTextArea logTextArea;

    public LogTabPanel() {
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5));

        logTextArea = new LogTextArea();
        add(logTextArea, BorderLayout.CENTER);

        IconBackgroundColorHoverStyle buttonHoverStyle = new IconBackgroundColorHoverStyle();
        int buttonPadding = 3;

        wordWrapButton = new ImageButton(Resources.getImage("system/panels/lp_wrap.png"), buttonHoverStyle, new Dimension(16, 16), buttonPadding);
        wordWrapButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                getListener().onWordWrapButtonClick(wordWrapButton, mouseEvent);
            }
        });

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
                getListener().onScrollToEndButtonClick(scrollToEndButton, mouseEvent);
            }
        });

        trashButton = new ImageButton(Resources.getImage("system/panels/lp_trash.png"), buttonHoverStyle, new Dimension(16, 16), buttonPadding);
        trashButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                getListener().onTrashButtonClick(trashButton, mouseEvent);
            }
        });

        logTextArea.getControlPanel().addButton(wordWrapButton);
        logTextArea.getControlPanel().addButton(copyToClipboardButton);
        logTextArea.getControlPanel().addButton(scrollToEndButton);
        logTextArea.getControlPanel().addButton(trashButton);
    }

    public ImageButton getWordWrapButton() {
        return wordWrapButton;
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

    public LogTextArea getLogTextArea() {
        return logTextArea;
    }
}
