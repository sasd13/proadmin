package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.running.Running;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 15/08/2016.
 */
public class RunningsProjectsCodesBuilder implements IBuilder<List<String>> {

    private List<Running> runnings;

    public RunningsProjectsCodesBuilder(List<Running> runnings) {
        this.runnings = runnings;
    }

    @Override
    public List<String> build() {
        List<String> list = new ArrayList<>();

        for (Running running : runnings) {
            if (!list.contains(running.getProject().getCode())) {
                list.add(running.getProject().getCode());
            }
        }

        return list;
    }
}
