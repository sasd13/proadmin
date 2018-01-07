package com.sasd13.proadmin.android.util;

import com.sasd13.proadmin.android.model.AcademicLevel;
import com.sasd13.proadmin.android.model.Project;
import com.sasd13.proadmin.android.model.Running;
import com.sasd13.proadmin.android.model.Student;
import com.sasd13.proadmin.android.model.Team;

import java.util.List;

/**
 * Created by ssaidali2 on 06/11/2016.
 */
public class IndexFinder {

    public static int indexOf(String pattern, String[] values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(pattern)) {
                return i;
            }
        }

        return -1;
    }

    public static int indexOf(float pattern, String[] values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(String.valueOf((int) pattern))) {
                return i;
            }
        }

        return -1;
    }

    public static int indexOfProject(Project project, List<Project> projects) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).equals(project)) {
                return i;
            }
        }

        return -1;
    }

    public static int indexOfRunning(Running running, List<Running> runnings) {
        for (int i = 0; i < runnings.size(); i++) {
            if (runnings.get(i).equals(running)) {
                return i;
            }
        }

        return -1;
    }

    public static int indexOfAcademicLevel(AcademicLevel academicLevel, List<AcademicLevel> academicLevels) {
        for (int i = 0; i < academicLevels.size(); i++) {
            if (academicLevels.get(i).equals(academicLevel)) {
                return i;
            }
        }

        return -1;
    }

    public static int indexOfTeam(Team team, List<Team> teams) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).equals(team)) {
                return i;
            }
        }

        return -1;
    }

    public static int indexOfStudent(Student student, List<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).equals(student)) {
                return i;
            }
        }

        return -1;
    }

    public static int indexOfMark(float mark, List<Float> marks) {
        for (int i = 0; i < marks.size(); i++) {
            if (Float.compare(marks.get(i), mark) == 0) {
                return i;
            }
        }

        return -1;
    }
}
