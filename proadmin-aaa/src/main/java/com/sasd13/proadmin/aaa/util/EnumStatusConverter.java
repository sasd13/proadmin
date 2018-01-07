package com.sasd13.proadmin.aaa.util;

import javax.persistence.AttributeConverter;

import com.sasd13.proadmin.aaa.bean.EnumStatus;

public class EnumStatusConverter implements AttributeConverter<EnumStatus, Integer> {

	@Override
	public Integer convertToDatabaseColumn(EnumStatus status) {
		switch (status) {
			case INACTIVE:
			case INITIAL:
			case ACTIVE:
				return status.getCode();
			case UNKNOWN:
				throw new IllegalArgumentException("Unknown status not permitted");
			default:
				throw new IllegalArgumentException("Error status : " + status);
		}
	}

	@Override
	public EnumStatus convertToEntityAttribute(Integer dbData) {
		return EnumStatus.find(dbData);
	}
}
