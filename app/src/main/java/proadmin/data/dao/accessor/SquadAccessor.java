package proadmin.data.dao.accessor;

import proadmin.beans.running.Team;
import proadmin.content.Year;

/**
 * Created by Samir on 11/06/2015.
 */
public interface SquadAccessor {

    void insertSquad(Team team);

    void updateSquad(Team team);

    void deleteSquad(String squadId);

    Team selectSquad(String squadId);

    ListSquads selectSquadsOfTeacher(String teacherId);

    ListSquads selectSquadsOfYear(Year year);

    ListSquads selectSquadsOfProject(String projectId);

    ListSquads selectSquadsOfTeacherAndYear(String teacherId, Year year);

    ListSquads selectSquadsOfTeacherAndProject(String teacherId, String projectId);

    ListSquads selectSquadsOfYearAndProject(Year year, String projectId);

    ListSquads selectSquadsOfTeacherAndYearAndProjectAndYear(String teacherId, Year year, String projectId);
}
