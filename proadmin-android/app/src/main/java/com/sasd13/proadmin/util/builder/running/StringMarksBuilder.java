package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 13/11/2016.
 */

public class StringMarksBuilder implements IBuilder<List<String>> {

    private static final String DEFAULT_PATTERN = "#.#";

    private List<Float> marks;
    private DecimalFormat decimalFormat;

    public StringMarksBuilder(List<Float> marks) {
        this(marks, DEFAULT_PATTERN);
    }

    public StringMarksBuilder(List<Float> marks, String pattern) {
        this.marks = marks;
        decimalFormat = new DecimalFormat(pattern);
    }

    @Override
    public List<String> build() {
        List<String> list = new ArrayList<>();

        for (Float mark : marks) {
            list.add(decimalFormat.format(mark));
        }

        return list;
    }
}
