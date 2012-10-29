package jDistsim.utils.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 28.10.12
 * Time: 1:29
 */
public class ActionObject {

    private List<ActionObjectListener> listeners;

    public ActionObject() {
        listeners = new ArrayList<ActionObjectListener>();
    }

    public void addActionObjectListener(ActionObjectListener actionObjectListener) {
        if (actionObjectListener != null)
            listeners.add(actionObjectListener);
    }

    protected void doActionPerformed() {
        for (ActionObjectListener listener : listeners)
            listener.ActionPerformed(new ActionArgument(this));
    }
}
