package proadmin.pattern.dao.accessor;

import proadmin.content.Squad;
import proadmin.content.Year;
import proadmin.content.ListSquads;
import proadmin.content.id.ProjectId;
import proadmin.content.id.SquadId;
import proadmin.content.id.TeacherId;

/**
 * Created by Samir on 11/06/2015.
 */
public interface SquadAccessor {

    void insertSquad(Squad squad);

    void updateSquad(Squad squad);

    void deleteSquad(SquadId squadId);

    Squad selectSquad(SquadId squadId);

    ListSquads selectSquadsOfYear(Year year);

    ListSquads selectSquadsOfProject(ProjectId projectId);

    ListSquads selectSquadsOfTeacher(TeacherId teacherId);

    ListSquads selectSquadsOfYearAndProject(Year year, ProjectId projectId);

    ListSquads selectSquadsOfYearAndTeacher(Year year, TeacherId teacherId);

    ListSquads selectSquadsOfProjectAndTeacher(ProjectId projectId, TeacherId teacherId);

    ListSquads selectSquadsOfYearAndProjectAndTeacher(Year year, ProjectId projectId, TeacherId teacherId);
}
