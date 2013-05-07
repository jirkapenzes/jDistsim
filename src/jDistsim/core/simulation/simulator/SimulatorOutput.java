package jDistsim.core.simulation.simulator;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 18.2.13
 * Time: 21:41
 */
public class SimulatorOutput {

    public enum MessageType {Core, Standard, Warning, Error}

    private List<SimulatorWriter> simulatorWriters;

    public SimulatorOutput() {
        simulatorWriters = new ArrayList<>();
    }

    public List<SimulatorWriter> getSimulatorWriters() {
        return simulatorWriters;
    }

    public void sendToOutput(String message) {
        sendToOutput(MessageType.Standard, message);
    }

    public void sendToOutput(MessageType messageType, String message) {
        for (SimulatorWriter simulatorWriter : simulatorWriters) {
            simulatorWriter.write(message);
        }
    }

    public void clearOutput() {
        for (SimulatorWriter simulatorWriter : simulatorWriters) {
            simulatorWriter.clear();
        }
    }

    public void drawSeparateLine() {
        String line = "";
        for (int i = 0; i < 100; i++) line += "-";
        sendToOutput(MessageType.Standard, line);
    }
}
