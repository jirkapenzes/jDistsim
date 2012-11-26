package jDistsim.core.modules;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 10.11.12
 * Time: 16:08
 */
public interface IModuleView {

    JComponent getContentPane();

    void draw(Graphics2D graphics, int width, int height);

    Polygon getBounds(int width, int height);
}
