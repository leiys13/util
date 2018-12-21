package com.sadasen.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @date 2018年3月23日
 * @author lei.ys
 * @addr company
 * @desc
 */
public class DateUtil {

	private static final long SECORD = 1000L;
	private static final long MINUTE = 60 * 1000L;
	private static final long HOUR = 60 * 60 * 1000L;
	private static final long DAY = 24 * 60 * 60 * 1000L;
	private static final long WEEK = 7 * 24 * 60 * 60 * 1000L;
	private static final long MONTH = 30 * 24 * 60 * 60 * 1000L;
	private static final long YEAR = 365 * 24 * 60 * 60 * 1000L;
	
	private static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	private static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

	private DateUtil() {
	}
	
	/**
	 * 返回当前时间
	 * @return
	 */
	public static Date now() {
		return new Date();
	}

	/**
	 * 字符串按格式转化为时间
	 * 
	 * @param string
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDateFormat(String string, String format) throws ParseException {
		DateFormat df = new SimpleDateFormat(format);
		Date dt = df.parse(string);
		return dt;
	}

	/**
	 * 时间转换为指定的字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToStringFormat(Date date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		String string = df.format(date);
		return string;
	}

	/**
	 * 获取yyyy-MM-dd格式的日期
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date) {
		return dateToStringFormat(date, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * 获取HH:mm:ss格式的时间
	 * @param date
	 * @return
	 */
	public static String getTimeString(Date date) {
		return dateToStringFormat(date, DEFAULT_TIME_FORMAT);
	}

	/**
	 * 获取yyyy-MM-dd HH:mm:ss格式的日期
	 * @param date
	 * @return
	 */
	public static String dateToStringFormat(Date date) {
		return dateToStringFormat(date, DEFAULT_DATETIME_FORMAT);
	}
	
	/**
	 * 将yyyy-MM-dd HH:mm:ss格式的字符串转换为时间
	 * @param string
	 * @return
	 */
	public static Date stringToDateFormat(String string) throws ParseException {
		return stringToDateFormat(string, DEFAULT_DATETIME_FORMAT);
	}
	
	/**
	 * 将long型时间格式化 yyyy-MM-dd HH:mm:ss
	 * @param time
	 * @return
	 */
	public static String longToStringFormat(long time) {
		Date date = new Date(time);
		return dateToStringFormat(date);
	}
	
	/**
	 * 将long型时间格式化
	 * @param time
	 * @param format
	 * @return
	 */
	public static String longToStringFormat(long time, String format) {
		Date date = new Date(time);
		return dateToStringFormat(date, format);
	}

	/**
	 * 判断d1和d2两个日期是否为同一天
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isSameDay(Date d1, Date d2) {
		String s1 = DateUtil.getDateString(d1);
		String s2 = DateUtil.getDateString(d2);
		if (s1.equals(s2)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 返回d1与d2时间差（以 分 | 时 | 天 | 周 | 月 | 年 为计算单位）
	 * @param date
	 * @return
	 */
	public static String diffToHumanSingle(Date d1, Date d2) {
		long t1 = d1.getTime();
		long t2 = d2.getTime();
		String ab = null;
		String shotString = null;
		if(t2 > t1) {
			ab = "前";
			shotString = "刚刚";
		} else {
			ab = "后";
			shotString = "即将";
		}
		long diff = Math.abs(t2 - t1);
		if(diff < MINUTE) {
			return shotString;
		}
		if(diff < HOUR) {
			return diff/MINUTE + "分钟" + ab;
		}
		if(diff < DAY) {
			return diff/HOUR + "小时" + ab;
		}
		if(diff < WEEK) {
			return diff/DAY + "天" + ab;
		}
		if(diff < MONTH) {
			return diff/WEEK + "周" + ab;
		}
		if(diff < YEAR) {
			return diff/MONTH + "个月" + ab;
		}
		return diff/YEAR + "年" + ab;
	}
	
	/**
	 * 返回时间与当前时间差（以 分 | 时 | 天 | 周 | 月 | 年 为计算单位）
	 * @param date
	 * @return
	 */
	public static String diffToHumanSingle(Date date) {
		return diffToHuman(date, now());
	}
	
	public static String diffToHuman(Date d1, Date d2) {
		StringBuilder builder = new StringBuilder("");
		long diff = Math.abs(d2.getTime() - d1.getTime());
		if(diff >= YEAR) {
			builder.append(diff/YEAR + "年");
			diff %= YEAR;
		}
		if(diff >= MONTH) {
			builder.append(diff/MONTH + "月");
			diff %= MONTH;
		}
		if(diff >= DAY) {
			builder.append(diff/DAY + "天");
			diff %= DAY;
		}
		if(diff >= HOUR) {
			builder.append(diff/HOUR + "时");
			diff %= HOUR;
		}
		if(diff >= MINUTE) {
			builder.append(diff/MINUTE + "分");
			diff %= MINUTE;
		}
		if(diff >= SECORD) {
			builder.append(diff/SECORD + "秒");
		}
		String result = builder.toString();
		if("".equals(result)) {
			return "同一时刻";
		}
		return builder.toString();
	}
	
	public static String diffToHuman(Date date) {
		return diffToHuman(date, now());
	}
	
	public static long diffDay(Date d1, Date d2) {
		long diff = d2.getTime() - d1.getTime();
		long diffDay = diff / DAY;
		return diffDay;
	}

	public static void main(String[] args) throws ParseException {
		Date d1 = stringToDateFormat("2018-03-02 00:00:00", DEFAULT_DATETIME_FORMAT);
		Date d2 = stringToDateFormat("2018-03-01 00:00:00", DEFAULT_DATETIME_FORMAT);
		boolean d = isSameDay(d1, d2);
		System.out.println(d);
		System.out.println(diffDay(d1, d2));
		
		System.out.println(diffToHuman(stringToDateFormat("19900208041020", "yyyyMMddHHmmss")));
	}

}
