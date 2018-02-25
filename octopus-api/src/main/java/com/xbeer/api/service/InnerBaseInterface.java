package com.xbeer.api.service;

import com.xbeer.OctContext;
import com.xbeer.api.SendResult;
import com.xbeer.exception.OctBaseException;

/**
 * 用于Octopus内部服务之间的通讯
 * */
public interface InnerBaseInterface {
	public SendResult doSend(String msg,OctContext ctx) throws OctBaseException;
}