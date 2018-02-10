package com.gdrcu.connector;

import com.gdrcu.OctContext;
import com.gdrcu.exception.OctBaseException;

public interface IConnector {

	public void connect() throws OctBaseException;
	public String send(String msg,OctContext ctx) throws OctBaseException;
	public String getName() ;
}
