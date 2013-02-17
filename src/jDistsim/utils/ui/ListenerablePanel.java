package jDistsim.utils.ui;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 15.2.13
 * Time: 17:31
 */
public class ListenerablePanel<Listener extends IPanelListener> extends JPanel {

    private Listener listener;

    public ListenerablePanel() {
    }

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
