package com.sasd13.proadmin.pattern.adapter;

import com.sasd13.javaex.pattern.adapter.IAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 05/11/2016.
 */

public class IntegersToStringsAdapter implements IAdapter<List<Integer>, List<String>> {

    @Override
    public List<String> adapt(List<Integer> list) {
        List<String> results = new ArrayList<>();

        for (Integer year : list) {
            results.add(String.valueOf(year));
        }

        return results;
    }

    @Override
    public void adapt(List<Integer> integers, List<String> strings) {

    }
}
