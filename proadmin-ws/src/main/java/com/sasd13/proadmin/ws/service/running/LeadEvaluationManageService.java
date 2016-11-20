package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.wrapper.update.running.ILeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class LeadEvaluationManageService implements IManageService<LeadEvaluation> {

	private static final Logger LOGGER = Logger.getLogger(LeadEvaluationManageService.class);

	private DAO dao;

	public LeadEvaluationManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(List<LeadEvaluation> leadEvaluations) throws ServiceException {
		LOGGER.info("create unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public void update(List<IUpdateWrapper<LeadEvaluation>> updateWrappers) throws ServiceException {
		try {
			dao.open();

			ISession<LeadEvaluation> session = dao.getSession(LeadEvaluation.class);
			ILeadEvaluationUpdateWrapper leadEvaluationUpdateWrapper;

			for (IUpdateWrapper<LeadEvaluation> updateWrapper : updateWrappers) {
				leadEvaluationUpdateWrapper = (ILeadEvaluationUpdateWrapper) updateWrapper;

				LOGGER.info("update : reportNumber=" + leadEvaluationUpdateWrapper.getReportNumber() + ", studentNumber=" + leadEvaluationUpdateWrapper.getStudentNumber());
				session.update(leadEvaluationUpdateWrapper);
			}
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}
	}

	@Override
	public void delete(List<LeadEvaluation> leadEvaluations) throws ServiceException {
		LOGGER.info("delete unavailable");
		throw new ServiceException("Service unavailable");
	}
}
