package com.gdrcu.test;

import org.junit.Test;

import com.gdrcu.exception.OctBaseException;
import com.gdrcu.server.OctTcpInServer;

public class NettyTest {
	
	public static void main(String [] args){
		
		testNetty();
	}
	
	@Test
	public static void testNetty(){
		
		OctTcpInServer server  = new OctTcpInServer("tst", 6090, "utf-8");
		try {
			server.start();
		} catch (OctBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
