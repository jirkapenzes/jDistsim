package jDistsim.core.simulation.event.description;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 18:13
 */
public class EmptyDescription implements IEventDescription {
    @Override
    public String getTitle() {
        return "Information about process";
    }

    @Override
    public String getDescription() {
        return "Click on the component in the toolbox, and there appears a short information.";
    }
}
