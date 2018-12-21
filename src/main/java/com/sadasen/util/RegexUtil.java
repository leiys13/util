package com.sadasen.util;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @date 2018年3月20日
 * @author lei.ys
 * @addr company
 * @desc
 */
public class RegexUtil implements Serializable {

	private static final long serialVersionUID = 67048401815646650L;
	
	public static boolean match(String rgx, String str) {
		Pattern p = Pattern.compile(rgx);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	private RegexUtil() {
	}
	
	public static void main(String[] args) {
		String rgx = "^[^\\u4e00-\\u9fa5]{6,15}";
		String str = null;
		System.out.println(match(rgx, str));
	}

}
