package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.running.Report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 15/08/2016.
 */
public class ReportsTeamsNumbersBuilder implements IBuilder<List<String>> {

    private List<Report> reports;

    public ReportsTeamsNumbersBuilder(List<Report> reports) {
        this.reports = reports;
    }

    @Override
    public List<String> build() {
        List<String> list = new ArrayList<>();

        for (Report report : reports) {
            if (!list.contains(report.getRunningTeam().getTeam().getNumber())) {
                list.add(report.getRunningTeam().getTeam().getNumber());
            }
        }

        return list;
    }
}
