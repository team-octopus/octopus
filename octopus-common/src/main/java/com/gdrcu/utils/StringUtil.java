package com.gdrcu.utils;

public class StringUtil {

	
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
	 * 145  转换成000145
	 * 
	 * */
	public static String len2FixStr(int input, int len){
		   
			
		   StringBuffer str = new StringBuffer();
		   String str1 = input +"";
		   int subLen = len - str1.length();
		   int i =0;
		   while (i < subLen) {
			   str.append("0");
		       i++;
		     }
		   return str.append(str1).toString();
		}
}
