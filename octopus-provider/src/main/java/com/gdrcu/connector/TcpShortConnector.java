package com.gdrcu.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gdrcu.OctContext;
import com.gdrcu.common.IMessageObject;
import com.gdrcu.common.IOctBaseService;
import com.gdrcu.common.XpathMessageObject;
import com.gdrcu.exception.OctBaseException;
import com.gdrcu.exception.OctBaseException.Level;
import com.gdrcu.exception.OctErrorCode;
import com.gdrcu.netty.NettyClient;
import com.gdrcu.netty.NettyClientHandler;
import com.gdrcu.utils.DateUtil;
import com.gdrcu.utils.SpringContextUtil;

public class TcpShortConnector implements IConnector {

	Logger logger = LoggerFactory.getLogger(TcpShortConnector.class);
	
	private String ip;
	private int port;
	private String encode;

	private String name;// 名称。每个交易可以根据名字绑定连接器

	NettyClient client;
	NettyClientHandler handler;

	IOctBaseService service;

	
	
	public TcpShortConnector(String name, String ip, int port, String encode) {
		this.name = name;
		this.ip = ip;
		this.port = port;
		this.encode = encode;

	}

	@Override
	public String send(String msg,OctContext ctx) throws OctBaseException {

		// 创建与OUT端的连接
		Socket socket = null;
		OutputStream os = null;
		PrintWriter pw = null;
		InputStream in = null;
		BufferedReader bf = null;
		try {
			socket = new Socket(ip, port);
			// 写出
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.write(msg);
			pw.flush();
			socket.shutdownOutput();

			// 读取
			in = socket.getInputStream();
			bf = new BufferedReader(new InputStreamReader(in,encode));
			String resp = null;
			StringBuffer sbf = new StringBuffer();
			;
			while ((resp = bf.readLine()) != null) {
				sbf.append(resp);
			}

			if (null != this.service) {
				IMessageObject obj = new XpathMessageObject();

				
				ctx.setReturnDate(DateUtil.getCurDate());
				ctx.setProviderIp(this.ip);
				ctx.setReturnMsg(sbf.toString());
				

				this.service.invoke(obj, ctx);

			}
			return sbf.toString();
		} catch (UnknownHostException e) {
			logger.error("error",e);
			throw new OctBaseException(e, Level.IV, OctErrorCode.INNER_ERROR_CODE, ip, port + "");
		} catch (IOException e) {
			logger.error("error",e);
			throw new OctBaseException(e, Level.IV, OctErrorCode.INNER_ERROR_CODE, ip, port + "");
			
		} finally {
			if (null != bf) {
				try {
					bf.close();
				} catch (IOException e) {
					
					logger.error("error",e);
				}
			}
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					
					logger.error("error",e);
				}
			}
			if (null != pw) {
				pw.close();
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					
					logger.error("error",e);
				}
			}
			if (null != socket) {
				try {
					socket.close();
				} catch (IOException e) {
					
					logger.error("error",e);
				}
			}

		}

	}

	@Override
	public void connect() throws OctBaseException {
		//暂不使用netty client
		//client = new NettyClient(handler);

	}

	@Override
	public String getName() {

		return this.name;
	}

	@Override
	public void register() {
		
	}
	public void setFactory(ConnectorFactory factory){
		factory.connectorRegister(this);
		
	}

	

}
