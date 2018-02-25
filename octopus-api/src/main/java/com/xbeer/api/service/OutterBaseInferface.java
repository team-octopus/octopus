package com.xbeer.api.service;

import com.xbeer.exception.OctBaseException;

/**
 * 用于外部直接注册的服务接口调用
 * 
 * 使用场景：Dubbo的应用直接注册，
 * 供ESB调用或者第三方系统直接通过Socket或者Http方式调用 
 * 
 * */
public interface OutterBaseInferface {
	/**
	 * @param msg 发送的报文
	 * @return 返回的报文
	 * */
	public String doSend(String msg)  throws OctBaseException;
}
