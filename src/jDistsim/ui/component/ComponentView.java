package jDistsim.ui.component;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 2.11.12
 * Time: 12:41
 */
public abstract class ComponentView {

    private JComponent componentView;
    private final Dimension DefaultDimension = new Dimension(10, 10);

    protected ComponentView() {
        componentView = makeView();
    }

    protected abstract JComponent makeView();

    public JComponent getView() {
        return componentView;
    }
}
