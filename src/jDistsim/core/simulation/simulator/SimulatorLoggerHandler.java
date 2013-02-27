package jDistsim.core.simulation.simulator;

import jDistsim.utils.logging.Logger;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 21:44
 */
public class SimulatorLoggerHandler implements Writer {

    private final String simulatorPrefix = "Simulator -> ";

    @Override
    public void write(String text) {
        if (text.contains("----"))
            Logger.log(Logger.Level.Simulator, makeLine());
        else {
            Logger.log(Logger.Level.Simulator, text);
        }
    }

    @Override
    public void clear() {
        Logger.log(Logger.Level.Simulator, "clear output");
    }

    private String makeLine() {
        String line = "";
        for (int i = 0; i < 50; i++)
            line += "-";
        return line;
    }
}
