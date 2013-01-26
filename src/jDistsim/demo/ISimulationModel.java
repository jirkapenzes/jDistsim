package jDistsim.demo;

/**
 * Author: Jirka Pénzeš
 * Date: 23.12.12
 * Time: 20:36
 */
public interface ISimulationModel {

    public void addRootModule(RootModule rootModule);

    public Iterable<RootModule> getRootModules();


}
