package jDistsim.core.modules;


/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 17:14
 */
public class ModuleUI {

    private IModuleView moduleView;

    public ModuleUI(IModuleView moduleView) {
        this.moduleView = moduleView;
        initializeUI();
    }

    private void initializeUI() {
        moduleView.getContentPane().setSize(80, 50);
    }

    public IModuleView getView() {
        return moduleView;
    }

}
