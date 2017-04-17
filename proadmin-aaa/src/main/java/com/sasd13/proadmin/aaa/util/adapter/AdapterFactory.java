package com.sasd13.proadmin.aaa.util.adapter;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.aaa.util.adapter.b2m.CredentialUpdateBeanToCredentialUpdateAdapter;
import com.sasd13.proadmin.aaa.util.adapter.b2m.UserBeanToUserAdapter;
import com.sasd13.proadmin.aaa.util.adapter.b2m.UserCreateBeanToUserCreateAdapter;
import com.sasd13.proadmin.aaa.util.adapter.b2m.UserUpdateBeanToUserUpdateAdapter;

public class AdapterFactory {

	@SuppressWarnings("unchecked")
	public static <S, T> IAdapter<S, T> make(Class<S> mSource, Class<T> mTarget) {
		if (CredentialUpdateBeanToCredentialUpdateAdapter.class.isAssignableFrom(mSource)) {
			return (IAdapter<S, T>) new CredentialUpdateBeanToCredentialUpdateAdapter();
		} else if (UserBeanToUserAdapter.class.isAssignableFrom(mSource)) {
			return (IAdapter<S, T>) new UserBeanToUserAdapter();
		} else if (UserCreateBeanToUserCreateAdapter.class.isAssignableFrom(mSource)) {
			return (IAdapter<S, T>) new UserCreateBeanToUserCreateAdapter();
		} else if (UserUpdateBeanToUserUpdateAdapter.class.isAssignableFrom(mSource)) {
			return (IAdapter<S, T>) new UserUpdateBeanToUserUpdateAdapter();
		} else {
			return null;
		}
	}
}
