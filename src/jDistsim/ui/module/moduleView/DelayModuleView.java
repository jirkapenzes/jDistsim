package jDistsim.ui.module.moduleView;

import jDistsim.ui.module.ModuleView;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 10.11.12
 * Time: 20:58
 */
public class DelayModuleView extends ModuleView {

    @Override
    public Polygon getBounds(int width, int height) {
        Polygon polygon = new Polygon();
        polygon.addPoint(1, 1);
        polygon.addPoint(width - 1, 1);
        polygon.addPoint(width - 1, height - 1);
        polygon.addPoint(1, height - 1);
        return polygon;
    }
}
