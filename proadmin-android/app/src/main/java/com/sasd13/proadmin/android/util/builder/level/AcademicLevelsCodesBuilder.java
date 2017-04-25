package com.sasd13.proadmin.android.util.builder.level;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.level.AcademicLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 15/08/2016.
 */
public class AcademicLevelsCodesBuilder implements IBuilder<List<String>> {

    private List<AcademicLevel> academicLevels;

    public AcademicLevelsCodesBuilder(List<AcademicLevel> academicLevels) {
        this.academicLevels = academicLevels;
    }

    @Override
    public List<String> build() {
        List<String> list = new ArrayList<>();

        for (AcademicLevel academicLevel : academicLevels) {
            list.add(academicLevel.getCode());
        }

        return list;
    }
}
