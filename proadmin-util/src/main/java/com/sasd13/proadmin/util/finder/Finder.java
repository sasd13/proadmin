package com.sasd13.proadmin.util.finder;

import java.util.List;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;

public class Finder {

	public static Project findProjectByCode(String code, List<Project> projects) {
		for (Project project : projects) {
			if (project.getCode().equals(code)) {
				return project;
			}
		}

		return null;
	}

	public static IndividualEvaluation findIndividualEvaluationById(long id, List<IndividualEvaluation> individualEvaluations) {
		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			if (individualEvaluation.getId() == id) {
				return individualEvaluation;
			}
		}

		return null;
	}
}
