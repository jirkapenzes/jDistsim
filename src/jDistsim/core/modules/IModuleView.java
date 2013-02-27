package jDistsim.core.modules;

import jDistsim.ui.module.ColorScheme;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 10.11.12
 * Time: 16:08
 */
public interface IModuleView {

    JComponent getContentPane();

    void draw(Graphics2D graphics, int width, int height);

    Polygon getBounds(int width, int height);

    List<Point> getInputPoints();

    List<Point> getOutputPoints();

    void setDefaultColorScheme(ColorScheme defaultColorScheme);
}
