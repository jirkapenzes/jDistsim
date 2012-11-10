package jDistsim;

import jDistsim.utils.ioc.IObjectContainer;
import jDistsim.utils.ioc.ObjectContainer;
import jDistsim.utils.pattern.singleton.SingletonFactory;

/**
 * Author: Jirka Pénzeš
 * Date: 10.11.12
 * Time: 19:16
 */
public class ServiceLocator {

    private IObjectContainer objectContainer;

    private ServiceLocator() {
        objectContainer = new ObjectContainer();
    }

    public static synchronized void create() {
        SingletonFactory.register(new ServiceLocator());
    }

    public static ServiceLocator getInstance() {
        return SingletonFactory.getInstance(ServiceLocator.class);
    }

    public <Type> void bind(Class<Type> classType, Type object) {
        objectContainer.bind(classType, object);
    }

    public <Type> Type get(Class<Type> classType) {
        return (Type) objectContainer.get(classType);
    }
}
