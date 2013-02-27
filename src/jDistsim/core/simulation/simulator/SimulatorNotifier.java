package jDistsim.core.simulation.simulator;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 22.2.13
 * Time: 21:42
 */
public class SimulatorNotifier {

    private List<SimulatorNotifyListener> listeners;

    public SimulatorNotifier() {
        listeners = new ArrayList<>();
    }

    public List<SimulatorNotifyListener> getListeners() {
        return listeners;
    }


}
