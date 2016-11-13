package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 13/11/2016.
 */

public class DefaultMarksBuilder implements IBuilder<List<Float>> {

    private static class DefaultMarksBuilderHolder {
        private static final DefaultMarksBuilder INSTANCE = new DefaultMarksBuilder();
    }

    private List<Float> marks;

    private DefaultMarksBuilder() {
        marks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            marks.add((float) i);
        }
    }

    public static DefaultMarksBuilder getInstance() {
        return DefaultMarksBuilderHolder.INSTANCE;
    }

    @Override
    public List<Float> build() {
        return marks;
    }
}
