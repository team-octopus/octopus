package com.xbeer.connector;

import com.xbeer.OctContext;
import com.xbeer.exception.OctBaseException;

public interface IConnector {

	public void connect() throws OctBaseException;
	public String send(String msg,OctContext ctx) throws OctBaseException;
	public String getName() ;
	public void register();
}
