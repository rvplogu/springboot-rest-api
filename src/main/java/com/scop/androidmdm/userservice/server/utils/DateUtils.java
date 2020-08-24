package com.scop.androidmdm.userservice.server.utils;

import java.sql.Timestamp;
import java.util.Date;

public class DateUtils {

	public static Timestamp getCurrentTime() {
		Date date = new Date();
		return new Timestamp(date.getTime());
	}

}
