package proadmin.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Samir on 05/06/2015.
 */
public class ListProjects implements Iterable {

    private List<Project> list;

    public ListProjects() {
        this.list = new ArrayList<>();
    }

    public boolean add(Project project) {
        return this.list.add(project);
    }

    public boolean remove(Project project) {
        return this.list.remove(project);
    }

    public Project get(int index) {
        return this.list.get(index);
    }

    public Project get(String projectId) {
        for (Project project : this.list) {
            if (project.getId().equals(projectId)) {
                return project;
            }
        }

        return null;
    }

    public boolean contains(String projectId) {
        return this.list.contains(projectId);
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
