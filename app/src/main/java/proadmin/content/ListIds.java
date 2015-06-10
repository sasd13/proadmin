package proadmin.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Samir on 09/06/2015.
 */
public class ListIds implements Iterable, Viewable {

    private List<String> listIds;

    public ListIds() {
        this.listIds = new ArrayList<>();
    }

    public void add(String id) {
        this.listIds.add(id);
    }

    public void remove(String id) {
        this.listIds.remove(id);
    }

    public String get(int index) {
        return this.listIds.get(index);
    }

    public boolean contains(String id) {
        return this.listIds.contains(id);
    }

    public int size() {
        return this.listIds.size();
    }

    @Override
    public Iterator iterator() {
        return this.listIds.iterator();
    }

    @Override
    public List<String> getListSrings() {
        return this.listIds;
    }
}
