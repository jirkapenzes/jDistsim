package jDistsim.core.common;

/**
 * Author: Jirka Pénzeš
 * Date: 25.3.13
 * Time: 16:36
 */
public class SaveConnect {

    private String dependency;
    private int sourcePointIndex;
    private int targetPointIndex;

    public SaveConnect(String dependency, int sourcePointIndex, int targetPointIndex) {
        this.dependency = dependency;
        this.sourcePointIndex = sourcePointIndex;
        this.targetPointIndex = targetPointIndex;
    }

    public String getDependency() {
        return dependency;
    }

    public int getSourcePointIndex() {
        return sourcePointIndex;
    }

    public int getTargetPointIndex() {
        return targetPointIndex;
    }
}
