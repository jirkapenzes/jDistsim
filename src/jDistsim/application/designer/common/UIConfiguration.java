package jDistsim.application.designer.common;

import jDistsim.ui.control.button.ImageButton;
import jDistsim.ui.module.ColorScheme;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.pattern.singleton.SingletonFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 28.9.12
 * Time: 14:40
 */
public final class UIConfiguration {

    private List<ImageButton> buttonRegister;

    public UIConfiguration() {
        Logger.log("Created designer ui configuration");
        buttonRegister = new ArrayList<>();
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

    public ColorScheme getColorSchemeForDistributedModule() {
        return new ColorScheme(new Color(184, 172, 205), new Color(168, 153, 193), new Color(64, 64, 64));
    }

    public ColorScheme getColorSchemeForActiveModule() {
        return new ColorScheme(new Color(209, 218, 180), new Color(198, 208, 159), new Color(64, 64, 64));
    }

    public ColorScheme getColorSchemeForAnimateActionModule() {
        return new ColorScheme(new Color(242, 191, 149), new Color(239, 175, 123), new Color(64, 64, 64));
    }

    public List<ImageButton> buttonRegister() {
        return buttonRegister;
    }
}
