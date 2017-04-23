package com.sasd13.proadmin.util.sorter.member;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.member.ITeam;

public class TeamsSorter {

	public static void byNumber(List<ITeam> iTeams) {
		byNumber(iTeams, true);
	}

	public static void byNumber(List<ITeam> iTeams, final boolean byAsc) {
		if (!iTeams.isEmpty()) {
			Collections.sort(iTeams, new Comparator<ITeam>() {

				@Override
				public int compare(ITeam team1, ITeam team2) {
					if (byAsc) {
						return team1.getNumber().compareTo(team2.getNumber());
					} else {
						return team2.getNumber().compareTo(team1.getNumber());
					}
				}
			});
		}
	}
}
