package com.sasd13.proadmin.aaa;

public enum EnumStatus {
	UNKNOWN(-2),
	INACTIVE(-1),
	INITIAL(0),
	ACTIVE(1)
	;
	
	private int code;
	
	private EnumStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static EnumStatus find(int code) {
		for (EnumStatus status : values()) {
			if (code == status.code) {
				return status;
			}
		}
		
		return EnumStatus.UNKNOWN;
	}
}
