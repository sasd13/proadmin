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

import com.sasd13.proadmin.backend.model.Running;
import com.sasd13.proadmin.backend.service.IRunningService;
import com.sasd13.proadmin.backend.util.adapter.itf2model.RunningAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.model2itf.RunningAdapterM2I;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.running.RunningBean;
import com.sasd13.proadmin.itf.bean.running.RunningRequestBean;
import com.sasd13.proadmin.itf.bean.running.RunningResponseBean;

@RestController
@RequestMapping("/runnings")
public class RunningController extends Controller {

	private static final Logger LOGGER = Logger.getLogger(RunningController.class);

	@Autowired
	private IRunningService runningService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody RunningRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] Running : create");

		try {
			runningService.create(new RunningAdapterI2M().adapt(requestBean.getData().get(0)));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResponseEntity<Integer> update(@RequestBody RunningRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] Running : update");

		try {
			runningService.update(new RunningAdapterI2M().adapt(requestBean.getData().get(0)));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Integer> delete(@RequestBody RunningRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] Running : delete");

		try {
			runningService.delete(new RunningAdapterI2M().adapt(requestBean.getData().get(0)));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> search(@RequestBody SearchBean searchBean) {
		LOGGER.info("[Proadmin-Backend] Running : search");

		try {
			List<Running> results = runningService.read(searchBean.getCriterias());
			RunningResponseBean responseBean = new RunningResponseBean();
			List<RunningBean> list = new ArrayList<>();
			RunningAdapterM2I adapter = new RunningAdapterM2I();

			for (Running result : results) {
				list.add(adapter.adapt(result));
			}

			responseBean.setData(list);
			addHeaders(responseBean, list.size(), searchBean);

			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean>(HttpStatus.EXPECTATION_FAILED);
	}
}
