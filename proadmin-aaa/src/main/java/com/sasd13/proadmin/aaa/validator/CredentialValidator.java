package com.sasd13.proadmin.aaa.validator;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.validator.IValidator;
import com.sasd13.javaex.validator.ValidatorException;

public class CredentialValidator implements IValidator<Credential> {

	@Override
	public void validate(Credential credential) throws ValidatorException {
		if (credential == null) {
			throw new ValidatorException("Credential: null");
		}

		if (StringUtils.isBlank(credential.getUsername())) {
			throw new ValidatorException("Credential: username is not valid");
		}

		if (StringUtils.isBlank(credential.getPassword())) {
			throw new ValidatorException("Credential: password is not valid");
		}
	}
}
