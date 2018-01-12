package com.gdrcu.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

public class SocketClient {

	@Test
	public void testclient() {
		Socket client = null;
		BufferedReader is = null;
		Writer writer = null;
		try {
			client = new Socket("localhost", 7989);

			writer = new OutputStreamWriter(client.getOutputStream());
			writer.write("Hello Server.");
			writer.write("eof\n");
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
