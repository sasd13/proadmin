package com.sasd13.proadmin.backend.util.adapter.itf2entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.entity.Project;
import com.sasd13.proadmin.backend.util.Constants;
import com.sasd13.proadmin.itf.bean.project.ProjectBean;

public class ProjectAdapterI2M implements IAdapter<ProjectBean, Project> {

	private static final Logger LOGGER = Logger.getLogger(ProjectAdapterI2M.class);

	@Override
	public Project adapt(ProjectBean s) {
		Project t = new Project();

		if (s.getId() != null) {
			t.setId(Long.valueOf(s.getId().getId()));
		}

		try {
			t.setDateCreation(new SimpleDateFormat(Constants.PATTERN_DATETIME_DEFAULT).parse(s.getCoreInfo().getDateCreation()));
		} catch (ParseException e) {
			LOGGER.error(e);
			throw new RuntimeException("ProjectAdapterI2M : error parsing date " + s.getCoreInfo().getDateCreation());
		}

		t.setCode(s.getCoreInfo().getCode());
		t.setTitle(s.getCoreInfo().getTitle());
		t.setDescription(s.getCoreInfo().getDescription());

		return t;
	}
}
