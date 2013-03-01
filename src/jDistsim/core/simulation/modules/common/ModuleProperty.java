package jDistsim.core.simulation.modules.common;

import jDistsim.utils.pattern.observer.Observable;

/**
 * Author: Jirka Pénzeš
 * Date: 12.2.13
 * Time: 23:40
 */
public class ModuleProperty extends Observable implements Comparable<ModuleProperty> {

    private String key;
    private String text;
    private Object value;

    public ModuleProperty(String key) {
        this.key = key;
    }

    public ModuleProperty(String key, Object value, String text) {
        this.key = key;
        this.value = value;
        this.text = text;
    }


    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }


    public void setValue(Object value) {
        this.value = value;
        notifyObservers("value");
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ModuleProperty{" + "key='" + getKey() + '\'' + ", value=" + getValue() + '}';
    }

    @Override
    public int compareTo(ModuleProperty moduleProperty) {
        return this.getKey().compareTo(moduleProperty.getKey());
    }
}
