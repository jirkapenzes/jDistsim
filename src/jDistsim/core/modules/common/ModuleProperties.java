package jDistsim.core.modules.common;

import jDistsim.core.modules.Module;
import jDistsim.utils.collection.observable.ObservableHashMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 12.2.13
 * Time: 23:51
 */
public class ModuleProperties {

    private ObservableHashMap<String, ModuleProperty> observableHashMap;
    private Module module;

    public ModuleProperties(Module module) {
        this.module = module;
        observableHashMap = new ObservableHashMap<>();
    }

    public void set(ModuleProperty moduleProperty) {
        observableHashMap.put(moduleProperty.getKey(), moduleProperty);
    }

    public ModuleProperty get(String propertyName) {
        if (observableHashMap.containsKey(propertyName))
            return observableHashMap.get(propertyName);

        throw new RuntimeException("Property not found");
    }

    public List<ModuleProperty> getAll() {
        module.refreshProperties();
        List<ModuleProperty> properties = new ArrayList<>(observableHashMap.values());
        Collections.sort(properties);
        return properties;
    }

}
