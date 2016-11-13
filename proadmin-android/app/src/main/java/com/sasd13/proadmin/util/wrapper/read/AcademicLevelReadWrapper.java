package com.sasd13.proadmin.util.wrapper.read;

import com.sasd13.proadmin.bean.AcademicLevel;

import java.util.List;

/**
 * Created by ssaidali2 on 10/11/2016.
 */

public class AcademicLevelReadWrapper implements IReadWrapper<AcademicLevel> {

    private List<AcademicLevel> academicLevels;

    public AcademicLevelReadWrapper(List<AcademicLevel> academicLevels) {
        this.academicLevels = academicLevels;
    }

    @Override
    public List<AcademicLevel> getWrapped() {
        return academicLevels;
    }
}
