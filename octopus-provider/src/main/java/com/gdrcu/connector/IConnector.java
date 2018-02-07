package com.gdrcu.connector;

import com.gdrcu.exception.OctBaseException;

public interface IConnector {

	public void send(String msg) throws OctBaseException;
	
}
