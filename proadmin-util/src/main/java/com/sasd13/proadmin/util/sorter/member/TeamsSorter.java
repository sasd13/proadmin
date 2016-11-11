package com.sasd13.proadmin.util.sorter.member;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.member.Team;

public class TeamsSorter {

	public static void byNumber(List<Team> teams) {
		byNumber(teams, true);
	}

	public static void byNumber(List<Team> teams, final boolean byAsc) {
		Collections.sort(teams, new Comparator<Team>() {

			@Override
			public int compare(Team team1, Team team2) {
				if (byAsc) {
					return team1.getNumber().compareTo(team2.getNumber());
				} else {
					return team2.getNumber().compareTo(team1.getNumber());
				}
			}
		});
	}
}
