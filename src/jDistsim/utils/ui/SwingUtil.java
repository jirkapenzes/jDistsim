package jDistsim.utils.ui;

import java.awt.*;
import java.util.ArrayList;

/**
 * Author: Jirka Pénzeš
 * Date: 26.1.13
 * Time: 0:44
 */
public final class SwingUtil {

    public static final int getComponentIndex(Component component) {
        if (component != null && component.getParent() != null) {
            Container currentComponent = component.getParent();
            for (int index = 0; index < currentComponent.getComponentCount(); index++) {
                if (currentComponent.getComponent(index) == component)
                    return index;
            }
        }
        return -1;
    }

    public static void getComponentTreePosition(Component component, ArrayList position) {
        if (component.getParent() == null) {
            return;
        }
        getComponentTreePosition(component.getParent(), position);
        position.add(new Integer(component.getParent().getComponentCount() - getComponentIndex(component)));
    }
}
