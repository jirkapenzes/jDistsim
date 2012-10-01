package jDistsim.designer.ui.control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 26.9.12
 * Time: 14:36
 */
public class IconButton extends JComponent {

    private Icon icon;
    private Icon iconHover;
    private JLabel iconLabel;

    public IconButton(Icon icon) {
        this(icon, null);
    }

    public IconButton(Icon icon, Icon iconHover) {
        this(icon, iconHover, new Dimension(15, 15));
    }

    public IconButton(Icon icon, Icon iconHover, Dimension dimension) {
        this.icon = icon;
        this.iconHover = iconHover;

        setSize(dimension);
        setLayout(new BorderLayout());
        iconLabel = new JLabel(icon);
        add(iconLabel, BorderLayout.CENTER);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                iconLabelMouseEntered();
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                iconLabelMouseExited();
            }
        });
    }

    private void iconLabelMouseExited() {
        iconLabel.setIcon(icon);
    }

    private void iconLabelMouseEntered() {
        if (iconHover != null)
            iconLabel.setIcon(iconHover);
    }


}
