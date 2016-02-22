package com.sasd13.proadmin.core.db.util;

import java.sql.SQLException;

public class WhereClauseException extends SQLException {
	
	public WhereClauseException(String message) {
		super(message);
	}
}
