package com.gdrcu.exception;

/**
 * code 对应错误码
 * msg 对应于格式化需要的内容，比如100002对应的是连接IP:{} PORT:{}错误，则后面跟随的两个参数为IP和PORT
 * 
 * */
public class OctBaseException extends Exception {

	//3级以上需要重点关注
	public enum Level{
		I,II,III,IV,V,VI,VII,VIII
	}
	
	public OctBaseException(Exception e,Level level,int code,String ...msg){
		
	}
	
	
}
