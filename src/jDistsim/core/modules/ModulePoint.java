package jDistsim.core.modules;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Author: Jirka Pénzeš
 * Date: 26.11.12
 * Time: 13:33
 */
public class ModulePoint implements Iterable<Module> {

    private double capacity;
    private HashMap<String, Module> modules;

    public ModulePoint(double capacity) {
        this.capacity = capacity;
        modules = new HashMap<>();
    }

    public void connect(Module module) throws ModuleConnectionException {
        if (modules.size() <= capacity)
            modules.put(module.getIdentifier(), module);

        throw new ModuleConnectionException("Too many connections");
    }

    public Module disconnect(Module module) {
        return disconnect(module.getIdentifier());
    }

    public Module disconnect(String identifier) {
        return modules.remove(identifier);
    }

    @Override
    public Iterator<Module> iterator() {
        return modules.values().iterator();
    }
}
