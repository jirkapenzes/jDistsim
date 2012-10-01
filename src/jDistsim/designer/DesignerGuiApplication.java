package jDistsim.designer;

import jDistsim.designer.ui.form.DesignerForm;
import jDistsim.main.IGuiApplication;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.resource.TextResources;

/**
 * Author: Jirka Pénzeš
 * Date: 18.9.12
 * Time: 23:02
 */
public class DesignerGuiApplication implements IGuiApplication {

    @Override
    public void Start() {
        Logger.log("Initialize ui designer");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DesignerForm(TextResources.APPLICATION_NAME).setVisible(true);
            }
        });
    }
}
