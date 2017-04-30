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

import com.sasd13.proadmin.backend.model.Report;
import com.sasd13.proadmin.backend.service.IReportService;
import com.sasd13.proadmin.backend.util.adapter.itf2model.ReportAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.model2itf.ReportAdapterM2I;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.report.ReportBean;
import com.sasd13.proadmin.itf.bean.report.ReportRequestBean;
import com.sasd13.proadmin.itf.bean.report.ReportResponseBean;

@RestController
@RequestMapping("/reports")
public class ReportController extends Controller {

	private static final Logger LOGGER = Logger.getLogger(ReportController.class);

	@Autowired
	private IReportService reportService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody ReportRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] Report : create");

		try {
			reportService.create(new ReportAdapterI2M().adapt(requestBean.getData().get(0)));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResponseEntity<Integer> update(@RequestBody ReportRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] Report : update");

		try {
			reportService.update(new ReportAdapterI2M().adapt(requestBean.getData().get(0)));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Integer> delete(@RequestBody ReportRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] Report : delete");

		try {
			reportService.delete(new ReportAdapterI2M().adapt(requestBean.getData().get(0)));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> search(@RequestBody SearchBean searchBean) {
		LOGGER.info("[Proadmin-Backend] Report : search");

		try {
			List<Report> results = reportService.read(searchBean.getCriterias());
			ReportResponseBean responseBean = new ReportResponseBean();
			List<ReportBean> list = new ArrayList<>();
			ReportAdapterM2I adapter = new ReportAdapterM2I();

			for (Report result : results) {
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
