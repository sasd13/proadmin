package com.sasd13.proadmin.ws.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.DAOManager;
import com.sasd13.proadmin.ws.WSConstants;

public class DAOFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(DAOFilter.class);

	@Override
	public void init(FilterConfig config) throws ServletException {
		// Do nothing
	}

	@Override
	public void destroy() {
		// Do nothing
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		DAO dao = DAOManager.create();

		req.setAttribute(WSConstants.REQ_ATTR_DAO, dao);

		try {
			dao.open();
			chain.doFilter(req, resp);
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}
	}
}
