package com.sasd13.proadmin.ws2.db.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dto.IndividualEvaluationDTO;

public interface IIndividualEvaluationDAO {

	IndividualEvaluationDTO create(IndividualEvaluation individualEvaluation);

	void update(List<IndividualEvaluationUpdateWrapper> updateWrappers);

	void delete(List<IndividualEvaluation> individualEvaluations);

	List<IndividualEvaluationDTO> read(Map<String, String[]> parameters);
}
