package com.sasd13.proadmin.ws2.service;

import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.proadmin.bean.member.Teacher;

public interface ITeacherService extends IManageService<Teacher>, IReadService<Teacher> {

	Teacher findByNumber(String number);
}
