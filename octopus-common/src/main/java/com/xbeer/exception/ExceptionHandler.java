package com.xbeer.exception;

import com.xbeer.OctContext;
import com.xbeer.common.IMessageObject;

/**
 * 
 * 搜集错误信息后，统一处理给前端返回
 * 
 * 
 * */
public class ExceptionHandler {

	
	

	
	public static String buildExceptionMsg(IMessageObject obj,OctContext ctx,OctBaseException e) throws OctBaseException{
		
		
		//取得头部
		StringBuilder returnSb = new StringBuilder("<SYS_HEAD>");
		returnSb.append(obj.getValue("/service/SYS_HEAD"));
		returnSb.append("</SYS_HEAD>");
		 
		//根据错误拼装body
		
		
		
		
		
		
		return returnSb.toString();
	}
	
}
