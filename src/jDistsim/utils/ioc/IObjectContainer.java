package jDistsim.utils.ioc;

import java.util.Iterator;

/**
 * Author: Jirka Pénzeš
 * Date: 26.10.12
 * Time: 23:45
 */
public interface IObjectContainer<TObject> {

    <Type extends TObject> void bind(Class<Type> classType, TObject object);

    <Type extends TObject> Type get(Class<Type> classType);

    Iterator<TObject> listObjects();
}
