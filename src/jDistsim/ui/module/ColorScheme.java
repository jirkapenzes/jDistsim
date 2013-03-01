package jDistsim.ui.module;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 23.2.13
 * Time: 15:11
 */
public class ColorScheme {

    private Color backgroundColorA;
    private Color backgroundColorB;
    private Color borderColor;

    /*
    public ColorScheme(Color backgroundColor, Color borderColor) {
        this.backgroundColorA = backgroundColor;
        this.backgroundColorB = backgroundColor;
        this.borderColor = borderColor;
    }
    */
    public ColorScheme(Color backgroundColorA, Color backgroundColorB, Color borderColor) {
        this.backgroundColorA = backgroundColorA;
        this.backgroundColorB = backgroundColorB;
        this.borderColor = borderColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public Color getBackgroundColorA() {
        return backgroundColorA;
    }

    public Color getBackgroundColorB() {
        return backgroundColorB;
    }
}
