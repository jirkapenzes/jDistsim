package jDistsim.ui.component;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 2.11.12
 * Time: 12:41
 */
public abstract class ComponentView implements IComponentView {

    private JComponent componentView;

    protected ComponentView() {
        componentView = makeView();
    }

    protected abstract JComponent makeView();

    @Override
    public JComponent getView() {
        return componentView;
    }

    protected void setDefaultRenderingMode(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    protected void setDefaultBasicStroke(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    protected Color getBackgroundColor() {
        return new Color(67, 201, 224);
    }

    protected Color getBorderColor() {
        return new Color(70, 127, 137);
    }
}
