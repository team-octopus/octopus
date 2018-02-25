package com.xbeer.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

import com.xbeer.common.IMessageObject;
import com.xbeer.common.XpathMessageObject;
import com.xbeer.exception.OctBaseException;
import com.xbeer.exception.OctErrorCode;
import com.xbeer.exception.OctBaseException.Level;
import com.xbeer.utils.DateUtil;
import com.xbeer.utils.StringUtil;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class SocketClient {

	
	public String readMsgFromFile(){
		File f = new File("D:/workspace_octopus/octopus-parent/octopus-consumer/src/test/resources/303300001606.txt");
		StringBuffer sb = new StringBuffer();
		 BufferedReader reader = null;  
	        try {  
	           
	            reader = new BufferedReader(new FileReader(f));  
	            String tempString = null;  
	            int line = 1;  
	            // 一次读入一行，直到读入null为文件结束  
	            while ((tempString = reader.readLine()) != null) {  
	                // 显示行号  
	               sb.append(tempString);
	                line++;  
	            }  
	            reader.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (reader != null) {  
	                try {  
	                    reader.close();  
	                } catch (IOException e1) {  
	                }  
	            }  
	        }  
	        
	        return sb.toString();
		
	}
	@Test
	public void test2(){
		// 创建与OUT端的连接
		String msg = readMsgFromFile();
		msg = StringUtil.len2FixStr(msg.length(), 5)+msg;
		String ip = "localhost";
		int port = 7989;
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
			bf = new BufferedReader(new InputStreamReader(in,"utf-8"));
			String resp = null;
			StringBuffer sbf = new StringBuffer();
			;
			while ((resp = bf.readLine()) != null) {
				sbf.append(resp);
			}
			System.out.println(sbf.toString());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != bf) {
				try {
					bf.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			if (null != pw) {
				pw.close();
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			if (null != socket) {
				try {
					socket.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}

		}

	}
	
	@Test
	public void tst1(){
		TcpClient client  = new TcpClient();
		
		String msg = readMsgFromFile();
		
	
		
		msg = StringUtil.len2FixStr(msg.length(), 5)+msg;
		try {
			System.out.println(msg);
			ByteBuf bbuffer = Unpooled.copiedBuffer(msg.getBytes("utf-8"));
			/*
			byte[] bytes = new byte[bbuffer.readableBytes()];
			bbuffer.readBytes(bytes);
			
			System.out.println(new String(bytes));*/
			client.sendMsg(client.getChannel("localhost", 7989), bbuffer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testclient() {
		Socket client = null;
		BufferedReader is = null;
		Writer writer = null;
		try {
			client = new Socket("localhost", 7989);

			writer = new OutputStreamWriter(client.getOutputStream());
			String msg = readMsgFromFile();
			
			msg = StringUtil.len2FixStr(msg.length(), 5)+msg;
			
			
			
			writer.write(msg);
			
			writer.flush();

			is = new BufferedReader(new InputStreamReader(client.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String temp;
			
			while ((temp = is.readLine()) != null) {
				
				sb.append(temp);
			}
			// logger.info(sb.toString());
			System.out.println(sb.toString());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (writer != null) {
					writer.close();
				}
				if (client != null) {
					client.close();
				}
			} catch (IOException e) {
				
				e.printStackTrace();
				//logger.error("Close the IO session error: ", e);
			}
		}
	}
}
