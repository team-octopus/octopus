package com.gdrcu.connector;

import com.gdrcu.exception.OctBaseException;

public interface IConnector {

	public void connect() throws OctBaseException;
	public void send(String msg) throws OctBaseException;
	
}
