package jDistsim.core.simulation.modules.lib.outputer;

import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Attribute;
import jDistsim.core.simulation.simulator.entity.Entity;
import jDistsim.utils.common.FileManager;
import jDistsim.utils.resource.Resources;

import java.util.Date;

/**
 * Author: Jirka Pénzeš
 * Date: 13.3.13
 * Time: 23:29
 */
public class Outputer extends Module<OutputerSettings> {

    private FileManager fileManager;

    public Outputer(OutputerSettings outputerSettings, boolean defaultInitialize) {
        super(outputerSettings, defaultInitialize);
    }

    @Override
    protected void preInitialization() {
    }

    @Override
    protected void initializeDefaultValues() {
        getSettings().setFilePath(Resources.getResourceRootDirectory());
    }

    @Override
    protected void resetStates(ISimulator simulator) {
        fileManager = new FileManager(getSettings().getFilePath());
        fileManager.clearFileContent();
    }

    @Override
    protected void logic(ISimulator simulator, Entity entity) {
        String output = getIdentifier() + ": " + new Date();
        fileManager.writeLine(output);
        output = "LVT: " + simulator.getLocalTime() + "; Entity: " + entity.getIdentifier();
        fileManager.writeLine(output);
        output = "Attributes: ";
        for (Attribute attribute : entity.getAttributes())
            output += "[" + attribute.toString() + "]; ";

        fileManager.writeLine(output);
        output = "-----------------------------------------------------------------";
        fileManager.writeLine(output);

        for (Module module : getAllOutputDependencies())
            simulator.plan( simulator.getLocalTime(), module, entity);
    }

    @Override
    protected void setProperty() {
    }
}
