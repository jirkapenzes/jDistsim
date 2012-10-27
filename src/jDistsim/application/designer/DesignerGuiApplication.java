package jDistsim.application.designer;

import jDistsim.application.designer.common.ComponentFactory;
import jDistsim.main.IGuiApplication;
import jDistsim.utils.logging.Logger;

/**
 * Author: Jirka Pénzeš
 * Date: 18.9.12
 * Time: 23:02
 */
public class DesignerGuiApplication implements IGuiApplication {

    @Override
    public void Start() {
        Logger.log("Prepare GUI jDistsim designer");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame(new ComponentFactory()).show();
            }
        });
    }
}
