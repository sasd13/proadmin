package proadmin.bean.member;

import proadmin.bean.AcademicLevel;

public class Student extends AcademicMember {

    private AcademicLevel academicLevel;

    public AcademicLevel getAcademicLevel() {
        return this.academicLevel;
    }

    public void setAcademicLevel(AcademicLevel academicLevel) {
        this.academicLevel = academicLevel;
    }
}
