package proadmin.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import proadmin.content.id.Id;

/**
 * Created by Samir on 09/06/2015.
 */
public class ListIds implements Iterable, Viewable {

    private List<Id> listIds;

    public ListIds() {
        this.listIds = new ArrayList<>();
    }

    public void add(Id id) {
        this.listIds.add(id);
    }

    public void remove(Id id) {
        this.listIds.remove(id);
    }

    public Id get(int index) {
        return this.listIds.get(index);
    }

    public boolean contains(Id id) {
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
        List<String> list = new ArrayList<>();

        for (Id id : this.listIds) {
            list.add(id.toString());
        }

        return list;
    }
}
