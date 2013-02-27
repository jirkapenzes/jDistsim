package jDistsim.ui.module;

import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 23.2.13
 * Time: 15:11
 */
public class ColorScheme {

    private Color backgroundColor;
    private Color borderColor;

    public ColorScheme(Color backgroundColor, Color borderColor) {
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }
}
