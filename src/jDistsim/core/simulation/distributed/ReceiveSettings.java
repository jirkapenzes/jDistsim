package jDistsim.core.simulation.distributed;

/**
 * Author: Jirka Pénzeš
 * Date: 24.3.13
 * Time: 13:10
 */
public class ReceiveSettings extends DistributedModuleSettings {

    private String authorizedEntityName;

    public ReceiveSettings(String baseIdentifier) {
        super(baseIdentifier);
    }

    public String getAuthorizedEntityName() {
        return authorizedEntityName;
    }

    public void setAuthorizedEntityName(String authorizedEntityName) {
        this.authorizedEntityName = authorizedEntityName;
    }
}
