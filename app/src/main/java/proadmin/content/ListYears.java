package proadmin.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Samir on 09/06/2015.
 */
public class ListYears implements Iterable, Viewable {

    private List<Year> list;

    public ListYears() {
        this.list = new ArrayList<>();
    }

    public void add(Year year) {
        this.list.add(year);
    }

    public void remove(Year year) {
        this.list.remove(year);
    }

    public Year get(int index) {
        return this.list.get(index);
    }

    public boolean contains(Year year) {
        for (Year mYear : this.list) {
            if (mYear.equals(year)) {
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

        for(Year year : this.list) {
            listStrings.add(year.toString());
        }

        return listStrings;
    }
}
