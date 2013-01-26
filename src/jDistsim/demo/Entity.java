package jDistsim.demo;

import java.util.HashMap;

/**
 * Author: Jirka Pénzeš
 * Date: 26.12.12
 * Time: 21:16
 */
public class Entity {

    private String identifier;
    private HashMap<String, Object> attributes;

    public Entity(String identifier) {
        this.identifier = identifier;
        attributes = new HashMap<>();
    }

    public void addAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    public String getIdentifier() {
        return identifier;
    }
}
