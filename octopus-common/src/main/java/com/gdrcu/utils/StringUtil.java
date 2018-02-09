package com.gdrcu.utils;

import java.io.UnsupportedEncodingException;

public class StringUtil {

	
	public static byte[] changeEncode(String msg,String encode) throws UnsupportedEncodingException{
		
		return new String(msg.getBytes(), encode).getBytes();
	}
	
	public static boolean isEmpty(String in) {

		return in == null || in.equals("") ? true : false;
	}

	public static String addZeroForNum(String str, int strLength) {
		int strLen = str.length();
		StringBuffer sb = null;
		while (strLen < strLength) {
			sb = new StringBuffer();
			sb.append("0").append(str);// 左补0
			// sb.append(str).append("0");//右补0
			str = sb.toString();
			strLen = str.length();
		}
		return str;
	}

	/**
	 * 145 转换成000145
	 * 
	 */
	public static String len2FixStr(int input, int len) {

		StringBuffer str = new StringBuffer();
		String str1 = input + "";
		int subLen = len - str1.length();
		int i = 0;
		while (i < subLen) {
			str.append("0");
			i++;
		}
		return str.append(str1).toString();
	}
}
