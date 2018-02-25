package com.xbeer.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xbeer.common.IOctBaseService;
import com.xbeer.exception.OctBaseException;
import com.xbeer.exception.OctErrorCode;
import com.xbeer.server.in.netty.AbstractNettyHandler;
import com.xbeer.server.in.netty.NettyServer;
import com.xbeer.server.in.netty.TCPInHandler;

public class OctTcpInServer 
{
	private Logger logger = LoggerFactory.getLogger(OctTcpInServer.class);
	/**
	 * 允许一个端口可以是TCPIP
	 * 或者HTTP
	 * */
	public enum Model{
		TCP_IP,HTTP
	}
	
	private String channel;//渠道
	private int port;//端口
	private String encode;//编码
	
	
	private NettyServer nettyServer;
	
	
	private AbstractNettyHandler handler;
	
	private IOctBaseService service;
	
	
	
	public OctTcpInServer(String channel,int port,String encode){
		
		this.channel  = channel;
		this.port = port;
		this.encode = encode;
				
		
	}
	
	public void setBaseService(IOctBaseService service){
		this.service = service;
		
	}
	public void start() throws OctBaseException{
		
		handler = new TCPInHandler(this.channel,encode);
		handler.setRootService(this.service);
		nettyServer = new NettyServer(port,OctTcpInServer.Model.TCP_IP);
		try {
			nettyServer.start(handler);
			logger.info("start netty {channel:{},port:{},encode:{}} success",this.channel,this.port,this.encode);
		} catch (Exception e) {
			logger.error("error",e);
			try {
				nettyServer.stop();
			} catch (InterruptedException e1) {
				
			}
			throw new OctBaseException(e,OctBaseException.Level.III,OctErrorCode.INNER_ERROR_CODE);
		}
		
	}
	
	
	
	
}
