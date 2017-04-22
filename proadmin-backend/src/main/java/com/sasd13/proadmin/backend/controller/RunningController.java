package com.sasd13.proadmin.backend.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.proadmin.backend.bean.Running;
import com.sasd13.proadmin.backend.service.IRunningService;
import com.sasd13.proadmin.backend.util.adapter.bean2itf.RunningAdapterB2I;
import com.sasd13.proadmin.backend.util.adapter.itf2bean.RunningAdapterI2B;
import com.sasd13.proadmin.itf.RequestBean;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.bean.running.RunningBean;

@RestController
@RequestMapping("/running")
public class RunningController {

	private static final Logger LOGGER = Logger.getLogger(RunningController.class);

	@Autowired
	private IRunningService runningService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody RunningBean runningBean) {
		LOGGER.info("[Proadmin-Backend] Running : create");

		try {
			runningService.create(new RunningAdapterI2B().adapt(runningBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResponseEntity<Integer> update(@RequestBody RunningBean runningBean) {
		LOGGER.info("[Proadmin-Backend] Running : update");

		try {
			runningService.update(new RunningAdapterI2B().adapt(runningBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Integer> delete(@RequestBody RunningBean runningBean) {
		LOGGER.info("[Proadmin-Backend] Running : delete");

		try {
			runningService.delete(new RunningAdapterI2B().adapt(runningBean));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean<RunningBean>> search(@RequestBody RequestBean request) {
		LOGGER.info("[Proadmin-Backend] Running : search");

		try {
			List<Running> results = runningService.read(request.getCriteria());
			ResponseBean<RunningBean> response = new ResponseBean<RunningBean>();
			RunningAdapterB2I adapter = new RunningAdapterB2I();

			for (Running result : results) {
				response.getData().add(adapter.adapt(result));
			}

			return new ResponseEntity<ResponseBean<RunningBean>>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<ResponseBean<RunningBean>>(HttpStatus.EXPECTATION_FAILED);
	}
}
