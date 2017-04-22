package com.sasd13.proadmin.aaa.util.adapter;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.aaa.util.adapter.bean2model.CredentialUpdateAdapterB2M;
import com.sasd13.proadmin.aaa.util.adapter.bean2model.UserAdapterB2M;
import com.sasd13.proadmin.aaa.util.adapter.bean2model.UserCreateAdapterB2M;
import com.sasd13.proadmin.aaa.util.adapter.bean2model.UserUpdateAdapterB2M;

public class AdapterFactory {

	@SuppressWarnings("unchecked")
	public static <S, T> IAdapter<S, T> make(Class<S> mSource, Class<T> mTarget) {
		if (CredentialUpdateAdapterB2M.class.isAssignableFrom(mSource)) {
			return (IAdapter<S, T>) new CredentialUpdateAdapterB2M();
		} else if (UserAdapterB2M.class.isAssignableFrom(mSource)) {
			return (IAdapter<S, T>) new UserAdapterB2M();
		} else if (UserCreateAdapterB2M.class.isAssignableFrom(mSource)) {
			return (IAdapter<S, T>) new UserCreateAdapterB2M();
		} else if (UserUpdateAdapterB2M.class.isAssignableFrom(mSource)) {
			return (IAdapter<S, T>) new UserUpdateAdapterB2M();
		} else {
			return null;
		}
	}
}
