package jDistsim.core.simulation.simulator.entity;

import jDistsim.core.simulation.exception.ReservedAttributeNameException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Author: Jirka Pénzeš
 * Date: 22.2.13
 * Time: 16:43
 */
public class AttributeCollection implements Iterable<Attribute>, Serializable {

    private HashMap<String, Attribute> attributes;
    private String[] reservedWords = {"identifier"};

    public AttributeCollection() {
        attributes = new HashMap<>();
    }

    public void set(Attribute attribute) {
        if (attributes.containsKey(attribute.getName())) {
            Attribute att = attributes.get(attribute.getName());
            att.setName(attribute.getName());
            att.setValue(attribute.getValue());
        } else {
            put(attribute);
        }
    }

    public void put(String name, String value) {
        put(new Attribute(name, value));
    }

    public void put(Attribute attribute) {
        check(attribute);

        if (attributes.containsKey(attribute.name))
            attributes.get(attribute.name).setValue(attribute.value);
        attributes.put(attribute.name, attribute);
    }

    public Attribute get(String attributeName) {
        return attributes.get(attributeName);
    }

    public void remove(Attribute attribute) {
        if (attributes.containsKey(attribute.name))
            attributes.remove(attribute.name);
    }

    private void check(Attribute attribute) {
        for (int index = 0; index < reservedWords.length; index++) {
            if (reservedWords[index].toUpperCase().equals(attribute.name.toUpperCase()))
                throw new ReservedAttributeNameException(attribute.name);
        }
    }

    @Override
    public Iterator<Attribute> iterator() {
        return attributes.values().iterator();
    }

    public boolean contains(Attribute attribute) {
        return attributes.containsKey(attribute.getName());
    }

    public int size() {
        return attributes.size();
    }

    public boolean isEmpty() {
        return attributes.isEmpty();
    }

    public void clear() {
        attributes.clear();
    }
}
