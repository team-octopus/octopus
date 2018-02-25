package com.xbeer.exception;

public class OctErrorCode {

	
	public static int INNER_ERROR_CODE = -99;
	public static int TRANCODE_NOT_EXIST = 10000;//交易码不存在
	public static int XML_FORMAT_ERROR = 10001;//xml格式错误
	public static int CONNECTOR_ERROR = 10002;//连接后台IP，PORT错误
	public static int TRAN_CONNECTOR_IS_EMPTY = 10003;//交易对应connector为空，请检查配置是否正确
	
	public static int MESSAGE_FORMAT_ERROR = 10004;//上送报文格式错误
	
	public static int TARGET_TRANCODE_NOT_EXIST=10005;//目标交易码不存在
	
	public static int TARGET_SERVER_ERROR =10006;//目标系统错误
	
	
	
}
