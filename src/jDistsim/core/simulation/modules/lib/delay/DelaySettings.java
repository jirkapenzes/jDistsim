package jDistsim.core.simulation.modules.lib.delay;

import jDistsim.core.simulation.modules.ModuleSettings;

/**
 * Author: Jirka Pénzeš
 * Date: 24.3.13
 * Time: 12:50
 */
public class DelaySettings extends ModuleSettings {

    private int delayTime;

    public DelaySettings(String baseIdentifier) {
        super(baseIdentifier);
    }

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }
}
