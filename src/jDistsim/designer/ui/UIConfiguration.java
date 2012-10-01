package jDistsim.designer.ui;

import jDistsim.utils.logging.Logger;
import jDistsim.utils.pattern.singleton.SingletonFactory;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 28.9.12
 * Time: 14:40
 */
public final class UIConfiguration {

    public UIConfiguration() {
        Logger.log("Created designer ui configuration");
    }

    public static UIConfiguration getInstance() {
        return SingletonFactory.getInstance(UIConfiguration.class);
    }

    public Font getDefaultFont() {
        return new Font("Calibri", Font.PLAIN, 11);
    }

    // Event controls
    public Stroke getEventControlBorderStroke() {
        return new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    }

    public Color getEventControlBorderColor() {
        return new Color(79, 79, 79);
    }

    public Color getEventControlFillColor() {
        return new Color(158, 180, 228);
        //return new Color(255, 255, 164);
    }
}
