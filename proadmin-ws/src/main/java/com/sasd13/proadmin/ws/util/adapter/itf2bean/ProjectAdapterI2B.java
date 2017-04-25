package com.sasd13.proadmin.ws.util.adapter.itf2bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.project.ProjectBean;
import com.sasd13.proadmin.ws.bean.Project;
import com.sasd13.proadmin.ws.util.Constants;

public class ProjectAdapterI2B implements IAdapter<ProjectBean, Project> {

	private static final Logger LOGGER = Logger.getLogger(ProjectAdapterI2B.class);

	@Override
	public Project adapt(ProjectBean s) {
		Project t = new Project();

		try {
			t.setDateCreation(new SimpleDateFormat(Constants.PATTERN_DATETIME_DEFAULT).parse(s.getCoreInfo().getDateCreation()));
		} catch (ParseException e) {
			LOGGER.error(e);
			throw new RuntimeException("ProjectAdapterI2B : error parsing date " + s.getCoreInfo().getDateCreation());
		}

		t.setCode(s.getCoreInfo().getCode());
		t.setTitle(s.getCoreInfo().getTitle());
		t.setDescription(s.getCoreInfo().getDescription());

		return t;
	}
}
