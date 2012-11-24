package jDistsim.core.modules;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 12:16
 */
public abstract class Module {

    private IModuleView view;

    public Module(IModuleView view) {
        this.view = view;
    }



}
