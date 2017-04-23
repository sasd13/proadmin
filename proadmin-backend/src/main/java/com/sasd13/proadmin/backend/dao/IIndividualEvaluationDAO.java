package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.bean.IndividualEvaluation;
import com.sasd13.proadmin.backend.dao.dto.IndividualEvaluationDTO;

public interface IIndividualEvaluationDAO {

	List<IndividualEvaluationDTO> create(List<IndividualEvaluation> individualEvaluations);

	void update(List<IndividualEvaluation> individualEvaluations);

	void delete(List<IndividualEvaluation> individualEvaluations);

	List<IndividualEvaluationDTO> read(Map<String, String[]> parameters);
}
