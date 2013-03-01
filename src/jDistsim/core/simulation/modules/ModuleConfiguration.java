package jDistsim.core.simulation.modules;

import jDistsim.ui.module.ColorScheme;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 21:05
 */
public class ModuleConfiguration {

    private String baseIdentifier;
    private ColorScheme colorScheme;

    public ModuleConfiguration(String baseIdentifier, ColorScheme defaultColorScheme) {
        this.baseIdentifier = baseIdentifier;
        this.colorScheme = defaultColorScheme;
    }

    public String getBaseIdentifier() {
        return baseIdentifier;
    }

    public ColorScheme getColorScheme() {
        return colorScheme;
    }
}
