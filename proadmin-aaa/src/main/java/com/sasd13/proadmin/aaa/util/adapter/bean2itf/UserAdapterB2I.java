package com.sasd13.proadmin.aaa.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.aaa.bean.User;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.user.CoreInfo;
import com.sasd13.proadmin.itf.bean.user.UserBean;

public class UserAdapterB2I implements IAdapter<User, UserBean> {

	@Override
	public UserBean adapt(User s) {
		UserBean t = new UserBean();

		Id id = new Id();
		id.setId(s.getUserID());
		t.setId(id);

		CoreInfo coreInfo = new CoreInfo();
		coreInfo.setUserID(s.getUserID());
		coreInfo.setStatus(s.getStatus().getCode());
		coreInfo.setRoles(s.getRoles());
		coreInfo.setIntermediary(s.getIntermediary());
		coreInfo.setEmail(s.getEmail());

		return t;
	}
}
