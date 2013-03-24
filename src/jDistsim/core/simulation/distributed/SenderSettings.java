package jDistsim.core.simulation.distributed;

/**
 * Author: Jirka Pénzeš
 * Date: 24.3.13
 * Time: 13:23
 */
public class SenderSettings extends DistributedModuleSettings {

    private String distributedEntityKeyName;

    public SenderSettings(String baseIdentifier) {
        super(baseIdentifier);
    }

    public String getDistributedEntityKeyName() {
        return distributedEntityKeyName;
    }

    public void setDistributedEntityKeyName(String distributedEntityKeyName) {
        this.distributedEntityKeyName = distributedEntityKeyName;
    }
}
