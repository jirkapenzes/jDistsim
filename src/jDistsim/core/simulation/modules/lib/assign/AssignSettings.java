package jDistsim.core.simulation.modules.lib.assign;

import jDistsim.core.simulation.modules.ModuleSettings;
import jDistsim.core.simulation.simulator.entity.AttributeCollection;

/**
 * Author: Jirka Pénzeš
 * Date: 24.3.13
 * Time: 12:57
 */
public class AssignSettings extends ModuleSettings {

    private AttributeCollection attributes;

    public AssignSettings(String baseIdentifier) {
        super(baseIdentifier);
        attributes = new AttributeCollection();
    }

    public AttributeCollection getAttributes() {
        return attributes;
    }

    public int size() {
        return attributes.size();
    }
}
