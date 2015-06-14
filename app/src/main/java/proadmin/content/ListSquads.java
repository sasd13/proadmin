package proadmin.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import proadmin.content.Squad;
import proadmin.content.id.SquadId;

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

    public Squad get(SquadId projectId) {
        for (Squad project : this.list) {
            if (project.getId().equals(projectId)) {
                return project;
            }
        }

        return null;
    }

    public int size() {
        return this.list.size();
    }

    @Override
    public Iterator iterator() {
        return this.list.iterator();
    }
}
