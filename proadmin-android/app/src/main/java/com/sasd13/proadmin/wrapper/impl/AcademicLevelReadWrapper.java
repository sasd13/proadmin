package com.sasd13.proadmin.wrapper.impl;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.wrapper.IAcademicLevelReadWrapper;

import java.util.List;

/**
 * Created by ssaidali2 on 10/11/2016.
 */

public class AcademicLevelReadWrapper implements IAcademicLevelReadWrapper {

    private List<AcademicLevel> academicLevels;

    public AcademicLevelReadWrapper(List<AcademicLevel> academicLevels) {
        this.academicLevels = academicLevels;
    }

    @Override
    public List<AcademicLevel> getAcademicLevels() {
        return academicLevels;
    }
}
