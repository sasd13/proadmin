package proadmin.db.accessor;

import proadmin.content.Id;
import proadmin.content.ListProjects;
import proadmin.content.Project;
import proadmin.content.Year;

/**
 * Created by Samir on 11/06/2015.
 */
public interface ProjectAccessor {

    boolean insertProject(Project project, Year year);

    void updateProject(Project project);

    void deleteProject(Id projectId);

    void deleteProjectFromYear(Id projectId, Year year);

    Project selectProject(Id projectId);

    ListProjects selectProjectsOfYear(Year year);
}
