package jDistsim.core.modules;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 12:16
 */
public abstract class Module {

    private ModuleUI moduleUI;

    protected Module(ModuleUI moduleUI) {
        this.moduleUI = moduleUI;
    }

    public ModuleUI getUI() {
        return moduleUI;
    }
}
