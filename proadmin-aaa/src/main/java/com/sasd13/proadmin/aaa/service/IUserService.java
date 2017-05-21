package com.sasd13.proadmin.aaa.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.itf.bean.user.UserBean;
import com.sasd13.proadmin.itf.bean.user.create.UserCreateBean;
import com.sasd13.proadmin.itf.bean.user.update.UserUpdateBean;

public interface IUserService {

	UserBean create(UserCreateBean userCreateBean);

	void update(UserUpdateBean userUpdateBean);

	UserBean find(String userID);

	List<UserBean> read(Map<String, Object> criterias);
}
