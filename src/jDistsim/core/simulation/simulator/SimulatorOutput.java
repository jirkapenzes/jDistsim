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

    private List<Writer> writers;

    public SimulatorOutput() {
        writers = new ArrayList<>();
    }

    public List<Writer> getWriters() {
        return writers;
    }

    public void sendToOutput(MessageType messageType, String message) {
        for (Writer writer : writers) {
            writer.write(message);
        }
    }

    public void clearOutput() {
        for (Writer writer : writers) {
            writer.clear();
        }
    }

    public void drawSeparateLine() {
        String line = "";
        for (int i = 0; i < 100; i++) line += "-";
        sendToOutput(MessageType.Standard, line);
    }
}
