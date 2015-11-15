package proadmin.bean.member;

import proadmin.bean.AcademicLevel;

public class Student extends AcademicMember {

    private String number;
    private AcademicLevel academicLevel;

    public Student() { super(); }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public AcademicLevel getAcademicLevel() {
        return this.academicLevel;
    }

    public void setAcademicLevel(AcademicLevel academicLevel) {
        this.academicLevel = academicLevel;
    }
}
