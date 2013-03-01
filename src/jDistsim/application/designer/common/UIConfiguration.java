package jDistsim.application.designer.common;

import jDistsim.ui.module.ColorScheme;
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
        return getDefaultFont(false, 11);
    }

    public Font getDefaultFont(boolean bold) {
        return getDefaultFont(bold, 11);
    }

    public Font getDefaultFont(int size) {
        return getDefaultFont(false, size);
    }

    public Font getDefaultFont(boolean bold, int size) {
        return new Font("Calibri", bold ? Font.BOLD : Font.PLAIN, size);
    }

    public ColorScheme getColorSchemeForBasicModule() {
        return new ColorScheme(new Color(194, 214, 232), new Color(174, 202, 225), new Color(64, 64, 64));
    }

    public ColorScheme getColorSchemeForActiveModule() {
        return new ColorScheme(new Color(209, 218, 180), new Color(198, 208, 159), new Color(64, 64, 64));
    }

    public ColorScheme getColorSchemeForAnimateActionModule() {
        return new ColorScheme(new Color(242, 191, 149), new Color(239, 175, 123), new Color(64, 64, 64));
    }
}
