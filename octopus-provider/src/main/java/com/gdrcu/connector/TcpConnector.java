package com.gdrcu.connector;

import com.gdrcu.exception.OctBaseException;

public class TcpConnector implements IConnector{

	
	private String ip;
	private int port;
	private String encode;
	
	
	public TcpConnector(String ip,int port,String encode){
		
		this.ip = ip;
		this.port = port;
		this.encode = encode;
		
	}
	
	
	
	@Override
	public void send(String msg) throws OctBaseException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void connect() throws OctBaseException {
		
		
	}
	
	
	
	
}
