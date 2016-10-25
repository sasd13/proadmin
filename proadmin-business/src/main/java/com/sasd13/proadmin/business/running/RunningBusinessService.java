package com.sasd13.proadmin.business.running;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.dao.ILayeredDAO;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.business.BusinessException;
import com.sasd13.proadmin.business.BusinessService;
import com.sasd13.proadmin.util.EnumParameter;

/**
 * Created by Samir on 12/03/2016.
 */
public class RunningBusinessService extends BusinessService<Running> {

	public Running createRunning(Teacher teacher, Project project) throws BusinessException {
		int year = Calendar.getInstance().get(Calendar.YEAR);

		Map<String, String[]> parameters = new HashMap<>();
		parameters.put(EnumParameter.YEAR.getName(), new String[] { String.valueOf(year) });
		parameters.put(EnumParameter.TEACHER.getName(), new String[] { String.valueOf(teacher.getId()) });
		parameters.put(EnumParameter.PROJECT.getName(), new String[] { String.valueOf(project.getId()) });

		/*List<Running> runnings = persistor.read(parameters, Running.class);
		if (!runnings.isEmpty()) {
			throw new BusinessException("Running error", "Cannot have two runnings for project '" + project.getCode() + "' at year '" + year + "'");
		}*/

		Running runningToCreate = new Running();
		runningToCreate.setYear(year);
		runningToCreate.setTeacher(teacher);
		runningToCreate.setProject(project);

		return runningToCreate;
	}
}
