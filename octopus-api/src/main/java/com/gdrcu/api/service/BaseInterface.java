package com.gdrcu.api.service;

import com.gdrcu.OctContext;
import com.gdrcu.api.InvokeResult;
import com.gdrcu.exception.OctBaseException;

public interface BaseInterface {
	public InvokeResult doSend(String msg,OctContext ctx) throws OctBaseException;
}