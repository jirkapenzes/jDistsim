package jDistsim.core.simulation.modules;

/**
 * Author: Jirka Pénzeš
 * Date: 24.3.13
 * Time: 13:01
 */
public class RootSettings extends ModuleSettings {

    public enum TimeBetweenArrivalsType {
        Constant, Random_Expo
    }

    protected double firsCreation;
    protected String baseEntityName;
    protected TimeBetweenArrivalsType arrivalsType;
    protected double arrivalsTypeValue;
    protected int entityPerInterval;
    protected double maxArrivals;
    protected String iconName;

    public RootSettings(String baseIdentifier) {
        super(baseIdentifier);
    }

    public double getFirsCreation() {
        return firsCreation;
    }

    public void setFirsCreation(double firsCreation) {
        this.firsCreation = firsCreation;
    }

    public String getBaseEntityName() {
        return baseEntityName;
    }

    public void setBaseEntityName(String baseEntityName) {
        this.baseEntityName = baseEntityName;
    }

    public TimeBetweenArrivalsType getArrivalsType() {
        return arrivalsType;
    }

    public void setArrivalsType(TimeBetweenArrivalsType arrivalsType) {
        this.arrivalsType = arrivalsType;
    }

    public double getArrivalsTypeValue() {
        return arrivalsTypeValue;
    }

    public void setArrivalsTypeValue(double arrivalsTypeValue) {
        this.arrivalsTypeValue = arrivalsTypeValue;
    }

    public int getEntityPerInterval() {
        return entityPerInterval;
    }

    public void setEntityPerInterval(int entityPerInterval) {
        this.entityPerInterval = entityPerInterval;
    }

    public double getMaxArrivals() {
        return maxArrivals;
    }

    public void setMaxArrivals(double maxArrivals) {
        this.maxArrivals = maxArrivals;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
}
