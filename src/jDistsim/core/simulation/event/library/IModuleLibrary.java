package jDistsim.core.simulation.event.library;

import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 10.11.12
 * Time: 18:47
 */
public interface IModuleLibrary {

    ModuleContainer get(Class moduleClass);

    List<ModuleContainer> containersList();
}
