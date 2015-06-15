package proadmin.gui.widget.recycler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Samir on 14/06/2015.
 */
public class ListAbstractRecyclerItems implements Iterable {

    private List<AbstractRecyclerItem> list;

    public ListAbstractRecyclerItems() {
        this.list = new ArrayList<>();
    }

    public boolean add(AbstractRecyclerItem abstractRecyclerItem) {
        return this.list.add(abstractRecyclerItem);
    }

    public boolean remove(AbstractRecyclerItem abstractRecyclerItem) {
        return this.list.remove(abstractRecyclerItem);
    }

    public AbstractRecyclerItem remove(int index) {
        return this.list.remove(index);
    }

    public AbstractRecyclerItem get(int index) {
        return this.list.get(index);
    }

    public int size() {
        return this.list.size();
    }

    public void clear() {
        this.list.clear();
    }

    @Override
    public Iterator iterator() {
        return this.list.iterator();
    }
}
