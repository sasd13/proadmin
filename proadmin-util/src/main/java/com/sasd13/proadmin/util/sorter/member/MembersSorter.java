package com.sasd13.proadmin.util.sorter.member;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.member.Member;

public class MembersSorter {

	public static void byName(List<? extends Member> members) {
		byName(members, true);
	}

	public static void byName(List<? extends Member> members, final boolean byAsc) {
		if (!members.isEmpty()) {
			Collections.sort(members, new Comparator<Member>() {

				@Override
				public int compare(Member member1, Member member2) {
					if (byAsc) {
						return member1.getFullName().compareTo(member2.getFullName());
					} else {
						return member2.getFullName().compareTo(member1.getFullName());
					}
				}
			});
		}
	}

	public static void byNumber(List<? extends Member> members) {
		byNumber(members, true);
	}

	public static void byNumber(List<? extends Member> members, final boolean byAsc) {
		if (!members.isEmpty()) {
			Collections.sort(members, new Comparator<Member>() {

				@Override
				public int compare(Member member1, Member member2) {
					if (byAsc) {
						return member1.getIntermediary().compareTo(member2.getIntermediary());
					} else {
						return member2.getIntermediary().compareTo(member1.getIntermediary());
					}
				}
			});
		}
	}
}
