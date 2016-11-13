package com.sasd13.proadmin.util;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;

import java.util.List;

/**
 * Created by ssaidali2 on 06/11/2016.
 */
public class Finder {

    public static int indexOfProject(String projectCode, List<Project> projects) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getCode().equalsIgnoreCase(projectCode)) {
                return i;
            }
        }

        return -1;
    }

    public static int indexOfAcademicLevel(String academicLevelCode, List<AcademicLevel> academicLevels) {
        for (int i = 0; i < academicLevels.size(); i++) {
            if (academicLevels.get(i).getCode().equalsIgnoreCase(academicLevelCode)) {
                return i;
            }
        }

        return -1;
    }

    public static int indexOfTeam(String teamNumber, List<Team> teams) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getNumber().equalsIgnoreCase(teamNumber)) {
                return i;
            }
        }

        return -1;
    }

    public static int indexOfStudent(String studentNumber, List<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getNumber().equalsIgnoreCase(studentNumber)) {
                return i;
            }
        }

        return -1;
    }

    public static int indexOfMark(float mark, List<Float> marks) {
        for (int i = 0; i < marks.size(); i++) {
            if (Float.compare(marks.get(0), mark) == 0) {
                return i;
            }
        }

        return -1;
    }
}