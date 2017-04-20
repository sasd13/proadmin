package com.sasd13.proadmin.aaa.filter;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.proadmin.aaa.dao.DAO;
import com.sasd13.proadmin.aaa.dao.DAOManager;
import com.sasd13.proadmin.aaa.util.Constants;

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

		req.setAttribute(Constants.REQ_ATTR_DAO, dao);

		try {
			dao.open();
			chain.doFilter(req, resp);
		} catch (DAOException e) {
			LOGGER.error(e);
			((HttpServletResponse) resp).setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}
	}
}
