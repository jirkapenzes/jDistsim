package jDistsim.utils.pattern.mvc;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 26.10.12
 * Time: 17:33
 */
public abstract class AbstractView<Component extends JComponent> {
    private final AbstractFrame mainFrame;
    private Component contentPane;

    public AbstractView(AbstractFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    protected abstract Component layout();

    protected AbstractFrame getMainFrame() {
        return mainFrame;
    }

    public Component getContentPane() {
        if (contentPane == null) contentPane = layout();
        return contentPane;
    }

    public void make() {
        getContentPane();
    }
}