package com.sasd13.proadmin.aaa.validator;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.validator.IValidator;
import com.sasd13.javaex.validator.ValidatorException;
import com.sasd13.proadmin.util.wrapper.update.credential.ICredentialUpdateWrapper;

public class CredentialUpdateWrapperValidator implements IValidator<IUpdateWrapper<Credential>> {

	private CredentialValidator credentialValidator;

	public CredentialUpdateWrapperValidator() {
		credentialValidator = new CredentialValidator();
	}

	@Override
	public void validate(IUpdateWrapper<Credential> updateWrapper) throws ValidatorException {
		ICredentialUpdateWrapper credentialUpdateWrapper = (ICredentialUpdateWrapper) updateWrapper;

		if (credentialUpdateWrapper == null) {
			throw new ValidatorException("CredentialUpdateWrapper is not valid");
		}

		if (StringUtils.isBlank(credentialUpdateWrapper.getUsername())) {
			throw new ValidatorException("CredentialUpdateWrapper : username is not valid");
		}

		credentialValidator.validate(credentialUpdateWrapper.getWrapped());
	}
}
