package com.sasd13.proadmin.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.proadmin.backend.model.StudentTeam;
import com.sasd13.proadmin.backend.service.IStudentTeamService;
import com.sasd13.proadmin.backend.util.adapter.itf2model.StudentTeamAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.model2itf.StudentTeamAdapterM2I;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamBean;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamRequestBean;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamResponseBean;

@RestController
@RequestMapping("/studentTeams")
public class StudentTeamController extends Controller {

	private static final Logger LOGGER = Logger.getLogger(StudentTeamController.class);

	@Autowired
	private IStudentTeamService studentTeamService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody StudentTeamRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] StudentTeam : create");

		try {
			List<StudentTeam> studentTeams = new ArrayList<>();
			StudentTeamAdapterI2M adapter = new StudentTeamAdapterI2M();

			for (StudentTeamBean studentTeamBean : requestBean.getData()) {
				studentTeams.add(adapter.adapt(studentTeamBean));
			}

			studentTeamService.create(studentTeams);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Integer> delete(@RequestBody StudentTeamRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] StudentTeam : delete");

		try {
			List<StudentTeam> studentTeams = new ArrayList<>();
			StudentTeamAdapterI2M adapter = new StudentTeamAdapterI2M();

			for (StudentTeamBean studentTeamBean : requestBean.getData()) {
				studentTeams.add(adapter.adapt(studentTeamBean));
			}

			studentTeamService.delete(studentTeams);

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> search(@RequestBody SearchBean searchBean) {
		LOGGER.info("[Proadmin-Backend] StudentTeam : search");

		try {
			List<StudentTeam> results = studentTeamService.read(searchBean.getCriterias());
			StudentTeamResponseBean responseBean = new StudentTeamResponseBean();
			List<StudentTeamBean> list = new ArrayList<>();
			StudentTeamAdapterM2I adapter = new StudentTeamAdapterM2I();

			for (StudentTeam result : results) {
				list.add(adapter.adapt(result));
			}

			responseBean.setData(list);
			addHeaders(searchBean, responseBean, list.size());

			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean>(HttpStatus.EXPECTATION_FAILED);
	}
}
