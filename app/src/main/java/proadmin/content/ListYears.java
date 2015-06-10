package proadmin.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Samir on 09/06/2015.
 */
public class ListYears implements Iterable, Viewable {

    private List<Long> listYears;

    public ListYears() {
        this.listYears = new ArrayList<>();
    }

    public void add(Long year) {
        this.listYears.add(year);
    }

    public void remove(Long year) {
        this.listYears.remove(year);
    }

    public Long get(int index) {
        return this.listYears.get(index);
    }

    public boolean contains(Long year) {
        return this.listYears.contains(year);
    }

    public int size() {
        return this.listYears.size();
    }

    @Override
    public Iterator iterator() {
        return this.listYears.iterator();
    }

    @Override
    public List<String> getListSrings() {
        List<String> listStrings = new ArrayList<>();

        for(Long year : this.listYears) {
            listStrings.add(year.toString());
        }

        return listStrings;
    }
}
