package com.sasd13.proadmin.android.util.sorter;

import com.sasd13.proadmin.android.bean.Team;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TeamSorter {

    public static void byNumber(List<Team> list) {
        byNumber(list, true);
    }

    public static void byNumber(List<Team> list, final boolean byAsc) {
        if (!list.isEmpty()) {
            Collections.sort(list, new Comparator<Team>() {

                @Override
                public int compare(Team item1, Team item2) {
                    if (byAsc) {
                        return item1.getNumber().compareTo(item2.getNumber());
                    } else {
                        return item2.getNumber().compareTo(item1.getNumber());
                    }
                }
            });
        }
    }
}
