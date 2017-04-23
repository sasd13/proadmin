package com.sasd13.proadmin.bean.running;

import java.util.Date;

public interface IReport {

	IRunningTeam getRunningTeam();

	String getNumber();

	Date getDateMeeting();

	int getSession();

	String getComment();
}
