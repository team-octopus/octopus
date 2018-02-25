package com.xbeer.utils;

/**
 * TransUtil 作为处理交易码的工具类
 * @author Administrator
 *
 */
public class TransUtil {
	/**
	 * 创建服务码 S+transCode
	 * @param transCode 交易码
	 * @return  "S"+transCode; 返回服务码
	 */
	public static String buildServiceName(String transCode){
		return "S"+transCode;
	}
	/**
	 * 取得原始服务码
	 * @param transCode 交易码
	 * @return  transCode; 返回服务码
	 */
	public static String unBuildServiceName(String transCode){
		return transCode.substring(1);
	}
}
