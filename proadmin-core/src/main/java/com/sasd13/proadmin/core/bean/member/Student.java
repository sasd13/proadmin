/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.core.bean.member;

import com.sasd13.proadmin.core.bean.AcademicLevel;

/**
 *
 * @author Samir
 */
public class Student extends AcademicMember {
    
    private AcademicLevel academicLevel;

    public AcademicLevel getAcademicLevel() {
        return this.academicLevel;
    }

    public void setAcademicLevel(AcademicLevel academicLevel) {
        this.academicLevel = academicLevel;
    }
}
