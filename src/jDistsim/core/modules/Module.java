package jDistsim.core.modules;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 12:16
 */
public class Module {

    private String identifier;
    private ModuleUI moduleUI;

    public Module(ModuleUI moduleUI, ModuleConfiguration moduleConfiguration) {
        this.moduleUI = moduleUI;
        this.identifier = identifier;
    }

    public ModuleUI getUI() {
        return moduleUI;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
        moduleUI.setIdentifier(identifier);
    }

    public String getIdentifier() {
        return identifier;
    }
}
