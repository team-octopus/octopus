package com.gdrcu.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

import com.gdrcu.utils.StringUtil;

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
