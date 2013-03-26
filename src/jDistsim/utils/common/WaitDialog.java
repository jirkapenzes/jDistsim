package jDistsim.utils.common;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 25.3.13
 * Time: 21:16
 */
public class WaitDialog {

    private Frame owner;
    private String waitMessage;
    private boolean isDotProgress;
    private boolean isVisible;
    private WDialog dialog;

    public WaitDialog() {
        this(null, "Please wait");
    }

    public WaitDialog(Frame owner) {
        this(owner, "Please wait");
    }


    public WaitDialog(Frame owner, String waitMessage) {
        this.owner = owner;
        this.waitMessage = waitMessage;
        isDotProgress = true;
        isVisible = false;
    }

    public void show() {
        dialog = owner == null ? new WDialog() : new WDialog(owner);
        new Thread(new Runnable() {
            @Override
            public void run() {
                dialog.repaint();
                dialog.setModal(false);
                dialog.setVisible(true);
                isVisible = true;
            }
        }).start();

        while (!isVisible)
            ThreadWaiter.waitCurrentThreadFor(1);
    }

    public void hide() {
        dialog.setVisible(false);
    }

    private class WDialog extends JDialog {

        private Frame owner;
        private Dimension dimension;

        private WDialog() {
            this(null);
        }

        public WDialog(Frame owner) {
            super(owner);
            this.owner = owner;
            this.dimension = new Dimension(100, 100);
            initialize();
        }

        private void initialize() {
            setSize(dimension);
            setTitle("jDistsim wait window");
            setIconImage(null);
            setResizable(false);
            if (owner != null)
                setLocation(owner.getX() + owner.getWidth() / 2 - dimension.width / 2, owner.getY() + owner.getHeight() / 2 - dimension.height);

            setLayout(new BorderLayout());
            JLabel label = new JLabel(waitMessage);
            add(label, BorderLayout.CENTER);
        }
    }
}
