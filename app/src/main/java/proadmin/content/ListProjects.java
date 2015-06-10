package proadmin.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Samir on 05/06/2015.
 */
public class ListProjects implements Iterable {

    private List<Project> listProjects;

    public ListProjects() {
        this.listProjects = new ArrayList<>();
    }

    public boolean add(Project project) {
        return this.listProjects.add(project);
    }

    public boolean remove(Project project) {
        return this.listProjects.remove(project);
    }

    public Project get(int index) {
        return this.listProjects.get(index);
    }

    public Project get(String projectId) {
        for (Project project : this.listProjects) {
            if (project.getId().compareTo(projectId) == 0) {
                return project;
            }
        }

        return null;
    }

    public int size() {
        return this.listProjects.size();
    }

    @Override
    public Iterator iterator() {
        return this.listProjects.iterator();
    }
}
