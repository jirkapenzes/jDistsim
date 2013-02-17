package jDistsim.utils.collection;

import java.util.Iterator;

/**
 * Author: Jirka Pénzeš
 * Date: 13.2.13
 * Time: 22:15
 */
public interface ReadOnlyList<Item> extends Iterable<Item> {

    public Item get(int index);

    public int size();

    public Iterator<Item> iterator();
}
