package proadmin.data.dao.accessor;

import proadmin.content.Squad;
import proadmin.content.Year;
import proadmin.content.ListSquads;

/**
 * Created by Samir on 11/06/2015.
 */
public interface SquadAccessor {

    void insertSquad(Squad squad);

    void updateSquad(Squad squad);

    void deleteSquad(String squadId);

    Squad selectSquad(String squadId);

    ListSquads selectSquadsOfTeacher(String teacherId);

    ListSquads selectSquadsOfYear(Year year);

    ListSquads selectSquadsOfProject(String projectId);

    ListSquads selectSquadsOfTeacherAndYear(String teacherId, Year year);

    ListSquads selectSquadsOfTeacherAndProject(String teacherId, String projectId);

    ListSquads selectSquadsOfYearAndProject(Year year, String projectId);

    ListSquads selectSquadsOfTeacherAndYearAndProjectAndYear(String teacherId, Year year, String projectId);
}
