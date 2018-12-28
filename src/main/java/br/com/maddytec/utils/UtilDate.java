package br.com.maddytec.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilDate {

	private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getDate(Date date) {
		dateFormat.format(date);
		return dateFormat.format(date);
	}

	public static Date getDate(String date) {

		try {
			if (date == null) {
				return new Date();
			}

			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}
}
