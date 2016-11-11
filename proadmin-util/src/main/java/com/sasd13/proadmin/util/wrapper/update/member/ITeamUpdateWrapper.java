package com.sasd13.proadmin.util.wrapper.update.member;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Team;

public interface ITeamUpdateWrapper extends IUpdateWrapper<Team> {

	String getNumber();

	void setNumber(String number);
}
