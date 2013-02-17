package jDistsim.application.designer.common;

/**
 * Author: Jirka Pénzeš
 * Date: 10.2.13
 * Time: 15:23
 */
public class Application {

    private static Application instance;
    private static Object lock = new Object();

    private String modelName = "model1.jdsim";

    private Application() {
    }

    public static Application global() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new Application();
                }
            }
        }
        return instance;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
