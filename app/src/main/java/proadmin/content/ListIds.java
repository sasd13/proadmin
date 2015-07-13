package proadmin.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Samir on 09/06/2015.
 */
public class ListIds implements Iterable, Viewable {

    private List<String> list;

    public ListIds() {
        this.list = new ArrayList<>();
    }

    public void add(String id) {
        this.list.add(id);
    }

    public void remove(String id) {
        this.list.remove(id);
    }

    public String get(int index) {
        return this.list.get(index);
    }

    public boolean contains(String id) {
        for (String mString : this.list) {
            if (mString.equals(id)) {
                return true;
            }
        }

        return false;
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

        for(String id : this.list) {
            listStrings.add(id.toString());
        }

        return listStrings;
    }
}
