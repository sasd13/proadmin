package com.sasd13.proadmin.aaa.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.proadmin.aaa.service.IUserService;
import com.sasd13.proadmin.aaa.util.adapter.itf2bean.UserAdapterI2B;
import com.sasd13.proadmin.itf.ResponseBean;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.user.UserBean;
import com.sasd13.proadmin.itf.bean.user.UserResponseBean;
import com.sasd13.proadmin.itf.bean.user.create.UserCreateRequestBean;

@RestController
@RequestMapping("/users")
public class UserController extends Controller {

	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody UserRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] User : create");

		try {
			userService.create(new UserAdapterI2B().adapt(requestBean.getData()));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public ResponseEntity<Integer> update(@RequestBody UserRequestBean requestBean) {
		LOGGER.info("[Proadmin-Backend] User : update");

		try {
			userService.update(new UserAdapterI2M().adapt(requestBean.getData().get(0)));

			return new ResponseEntity<Integer>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<Integer>(HttpStatus.EXPECTATION_FAILED);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseEntity<ResponseBean> search(@RequestBody SearchBean searchBean) {
		LOGGER.info("[Proadmin-Backend] User : search");

		try {
			List<User> results = userService.read(searchBean.getCriterias());
			UserResponseBean responseBean = new UserResponseBean();
			List<UserBean> list = new ArrayList<>();
			UserAdapterM2I adapter = new UserAdapterM2I();

			for (User result : results) {
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
