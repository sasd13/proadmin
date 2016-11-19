package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.running.Running;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 15/08/2016.
 */
public class RunningsCodesBuilder implements IBuilder<List<String>> {

    private static final String SEPARATOR = " - ";

    private List<Running> runnings;

    public RunningsCodesBuilder(List<Running> runnings) {
        this.runnings = runnings;
    }

    @Override
    public List<String> build() {
        List<String> list = new ArrayList<>();

        String code;

        for (Running running : runnings) {
            code = running.getYear() + SEPARATOR + running.getProject().getCode();

            if (!list.contains(code)) {
                list.add(code);
            }
        }

        return list;
    }
}
