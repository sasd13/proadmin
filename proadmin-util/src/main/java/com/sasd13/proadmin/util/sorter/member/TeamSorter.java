package com.sasd13.proadmin.util.sorter.member;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.member.ITeam;

public class TeamSorter {

	public static void byNumber(List<ITeam> list) {
		byNumber(list, true);
	}

	public static void byNumber(List<ITeam> list, final boolean byAsc) {
		if (!list.isEmpty()) {
			Collections.sort(list, new Comparator<ITeam>() {

				@Override
				public int compare(ITeam item1, ITeam item2) {
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
