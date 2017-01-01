package com.sasd13.proadmin.ws2.db.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.ws2.db.dao.IReportDAO;
import com.sasd13.proadmin.ws2.db.dao.IRunningTeamDAO;

@Entity
@Table(name = IRunningTeamDAO.TABLE)
public class RunningTeamDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4845628221764465271L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_runningteams_id")
	@SequenceGenerator(name = "seq_runningteams_id", sequenceName = "seq_runningteams_id")
	@Column(name = IRunningTeamDAO.COLUMN_ID)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = IRunningTeamDAO.COLUMN_RUNNING)
	private RunningDTO running;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = IRunningTeamDAO.COLUMN_TEAM)
	private TeamDTO team;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = IRunningTeamDAO.COLUMN_ACADEMICLEVEL)
	private AcademicLevelDTO academicLevel;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = IReportDAO.COLUMN_RUNNINGTEAM)
	private List<ReportDTO> reports;

	public RunningTeamDTO() {
	}

	public RunningTeamDTO(RunningTeam runningTeam) {
		running = new RunningDTO(runningTeam.getRunning());
		team = new TeamDTO(runningTeam.getTeam());
		academicLevel = new AcademicLevelDTO(runningTeam.getAcademicLevel());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RunningDTO getRunning() {
		return running;
	}

	public void setRunning(RunningDTO running) {
		this.running = running;
	}

	public TeamDTO getTeam() {
		return team;
	}

	public void setTeam(TeamDTO team) {
		this.team = team;
	}

	public AcademicLevelDTO getAcademicLevel() {
		return academicLevel;
	}

	public void setAcademicLevel(AcademicLevelDTO academicLevel) {
		this.academicLevel = academicLevel;
	}

	public List<ReportDTO> getReports() {
		return reports;
	}

	public void setReports(List<ReportDTO> reports) {
		this.reports = reports;
	}

	@Override
	public int hashCode() {
		int result = 1;

		final int prime = 31;

		result = prime * result + ((running == null) ? 0 : running.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		result = prime * result + ((academicLevel == null) ? 0 : academicLevel.hashCode());

		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		RunningTeamDTO other = (RunningTeamDTO) obj;

		if (running == null && other.running != null)
			return false;
		else if (!running.equals(other.running))
			return false;

		if (team == null && other.team != null)
			return false;
		else if (!team.equals(other.team))
			return false;

		if (academicLevel == null && other.academicLevel != null)
			return false;
		else if (!academicLevel.equals(other.academicLevel))
			return false;

		return true;
	}
}
