package com.sasd13.proadmin.bean.member;

import com.sasd13.proadmin.bean.AcademicLevel;

public class Student extends AcademicMember {

    private AcademicLevel academicLevel;

    public AcademicLevel getAcademicLevel() {
        return this.academicLevel;
    }

    public void setAcademicLevel(AcademicLevel academicLevel) {
        this.academicLevel = academicLevel;
    }
}
