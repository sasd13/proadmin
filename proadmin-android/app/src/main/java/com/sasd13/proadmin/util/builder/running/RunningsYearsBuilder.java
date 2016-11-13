package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.running.Running;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 15/08/2016.
 */
public class RunningsYearsBuilder implements IBuilder<List<Integer>> {

    private List<Running> runnings;

    public RunningsYearsBuilder(List<Running> runnings) {
        this.runnings = runnings;
    }

    @Override
    public List<Integer> build() {
        List<Integer> list = new ArrayList<>();

        for (Running running : runnings) {
            if (!list.contains(running.getYear())) {
                list.add(running.getYear());
            }
        }

        return list;
    }
}