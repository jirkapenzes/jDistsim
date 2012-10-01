package jDistsim.main;

import jDistsim.designer.DesignerGuiApplication;
import jDistsim.utils.logging.Logger;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 18.9.12
 * Time: 23:03
 */
public class MainApplication {

    public static void main(String[] args) {
        Logger.defaultInitialize();
        Logger.log("Start jDistsim application");
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            IGuiApplication application = new DesignerGuiApplication();
            application.Start();
        } catch (Exception ex) {
        }
    }
}
