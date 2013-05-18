package jDistsim.core.simulation.modules.lib.outputer;

import jDistsim.core.simulation.modules.ModuleSettings;

/**
 * Author: Jirka Pénzeš
 * Date: 24.3.13
 * Time: 12:56
 */
public class OutputerSettings extends ModuleSettings {

    private String filePath;

    public OutputerSettings(String baseIdentifier) {
        super(baseIdentifier);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
