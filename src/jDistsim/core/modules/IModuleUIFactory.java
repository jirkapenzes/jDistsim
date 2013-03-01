package jDistsim.core.modules;

import jDistsim.ui.dialog.BaseModuleSettingsDialog;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 25.2.13
 * Time: 9:45
 */
public interface IModuleUIFactory<TModule extends  Module> {

    BaseModuleSettingsDialog makeSettingsDialog(JFrame parent, TModule module);
}
