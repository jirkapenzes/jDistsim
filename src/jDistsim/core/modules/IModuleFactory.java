package jDistsim.core.modules;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 12:15
 */
public interface IModuleFactory {

    public Module create();

    public String createIdentifier();

}
