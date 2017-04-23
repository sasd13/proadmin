package com.sasd13.proadmin.util.wrapper.update.member;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.ITeam;

public class TeamUpdateWrapper implements IUpdateWrapper<ITeam> {

	private ITeam iTeam;
	private String number;

	@Override
	public ITeam getWrapped() {
		return iTeam;
	}

	@Override
	public void setWrapped(ITeam iTeam) {
		this.iTeam = iTeam;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
