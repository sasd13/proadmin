package proadmin.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Samir on 05/06/2015.
 */
public class ListSquads implements Iterable {

    private List<Squad> list;

    public ListSquads() {
        this.list = new ArrayList<>();
    }

    public boolean add(Squad project) {
        return this.list.add(project);
    }

    public boolean remove(Squad project) {
        return this.list.remove(project);
    }

    public Squad get(int index) {
        return this.list.get(index);
    }

    public Squad get(String projectId) {
        for (Squad project : this.list) {
            if (project.getId().equals(projectId)) {
                return project;
            }
        }

        return null;
    }

    public boolean contains(String squadId) {
        return this.list.contains(squadId);
    }

    public int size() {
        return this.list.size();
    }

    @Override
    public Iterator iterator() {
        return this.list.iterator();
    }
}
