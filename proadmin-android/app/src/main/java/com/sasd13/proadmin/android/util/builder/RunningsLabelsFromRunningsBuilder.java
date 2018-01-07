package com.sasd13.proadmin.android.util.builder;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.android.model.Running;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 15/08/2016.
 */
public class RunningsLabelsFromRunningsBuilder implements IBuilder<List<String>> {

    private List<Running> runnings;

    public RunningsLabelsFromRunningsBuilder(List<Running> runnings) {
        this.runnings = runnings;
    }

    @Override
    public List<String> build() {
        List<String> list = new ArrayList<>();

        for (Running running : runnings) {
            list.add(running.getYear() + " / " + running.getProject().getCode());
        }

        return list;
    }
}
