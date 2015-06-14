package proadmin.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import proadmin.content.id.Id;

/**
 * Created by Samir on 09/06/2015.
 */
public class ListIds implements Iterable, Viewable {

    private List<Id> list;

    public ListIds() {
        this.list = new ArrayList<>();
    }

    public void add(Id id) {
        this.list.add(id);
    }

    public void remove(Id id) {
        this.list.remove(id);
    }

    public Id get(int index) {
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

    @Override
    public List<String> getListSrings() {
        List<String> listStrings = new ArrayList<>();

        for (Id id : this.list) {
            listStrings.add(id.toString());
        }

        return listStrings;
    }
}
