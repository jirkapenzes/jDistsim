package jDistsim.core.simulation.simulator.entity;

import java.io.Serializable;

/**
 * Author: Jirka Pénzeš
 * Date: 18.2.13
 * Time: 22:09
 */
public class Entity implements Serializable {

    private String entityIdentifier;
    private String creator;
    private String originalModel;
    private AttributeCollection attributes;

    public Entity(String entityIdentifier, String creator, String originalModel) {
        this.entityIdentifier = entityIdentifier;
        this.creator = creator;
        this.originalModel = originalModel;
        attributes = new AttributeCollection();
    }

    public String getIdentifier() {
        return originalModel + "." + creator + "." + entityIdentifier;
    }

    public String getShortIdentifier() {
        return entityIdentifier;
    }

    public String getCreator() {
        return creator;
    }

    public String getOriginalModel() {
        return originalModel;
    }

    public AttributeCollection getAttributes() {
        return attributes;
    }
}
