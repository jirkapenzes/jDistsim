package jDistsim.utils.ioc;

import java.util.Map;
import java.util.Set;

/**
 * Author: Jirka Pénzeš
 * Date: 26.10.12
 * Time: 23:45
 */
public interface IObjectContainer<Identifier> {

    <TClass> TClass bind(Identifier identifier, TClass object);

    <TClass> TClass get(Identifier identifier);

    Set<Map.Entry<Identifier, Object>> entrySet();
}
