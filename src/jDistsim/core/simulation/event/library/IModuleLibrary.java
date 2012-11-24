package jDistsim.core.simulation.event.library;

import java.util.Map;
import java.util.Set;

/**
 * Author: Jirka Pénzeš
 * Date: 10.11.12
 * Time: 18:47
 */
public interface IModuleLibrary {

    public Set<Map.Entry<String, EventContainer>> entrySet();
}
