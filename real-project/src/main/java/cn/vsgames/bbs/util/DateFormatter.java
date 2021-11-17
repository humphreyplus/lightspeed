package cn.vsgames.bbs.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	
	public static String formatDate(Date date) {
		if(date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		} else {
			return null;
		}
	}
	
	public static String formatShortDate(Date date) {
		if(date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
			return sdf.format(date);
		} else {
			return null;
		}
	}

}
