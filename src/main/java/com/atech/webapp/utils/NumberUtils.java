package com.atech.webapp.utils;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.commons.lang.StringUtils;

public class NumberUtils {
	
	public static Double formatToDouble(String numString) {
		DecimalFormat decFormat = new DecimalFormat("###,###.##");
		try {
			return decFormat.parse(numString).doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
			return new Double(0);
		}
	}
	
	public static String clear(String string) {
		
		string = StringUtils.remove(string, "/");
		string = StringUtils.remove(string, "-");
		
		return StringUtils.remove(string, ".");
	}
	
	public static int toInt(Double i) {
		if (i != null) {
			return i.intValue();
		}
		
		return 0;
	}
	
}
