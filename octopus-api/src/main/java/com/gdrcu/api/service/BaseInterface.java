package com.gdrcu.api.service;

import com.gdrcu.OctContext;
import com.gdrcu.api.SendResult;
import com.gdrcu.exception.OctBaseException;

public interface BaseInterface {
	public SendResult doSend(String msg,OctContext ctx) throws OctBaseException;
}