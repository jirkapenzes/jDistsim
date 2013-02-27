package jDistsim.core.modules;

import jDistsim.ui.dialog.BaseModuleSettingsDialog;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 25.2.13
 * Time: 9:45
 */
public interface IModuleUIFactory {

    BaseModuleSettingsDialog makeSettingsDialog(JFrame parent, Module module);
}
