package proadmin.data.dao.accessor;

import proadmin.beans.Project;
import proadmin.content.Year;

/**
 * Created by Samir on 11/06/2015.
 */
public interface ProjectAccessor {

    void insertProject(Project project, Year year);

    void updateProject(Project project);

    void deleteProject(String projectId);

    void deleteProjectFromYear(String projectId, Year year);

    Project selectProject(String projectId);

    ListProjects selectProjectsOfYear(Year year);
}
