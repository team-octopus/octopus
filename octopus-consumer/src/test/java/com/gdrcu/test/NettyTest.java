package com.gdrcu.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.gdrcu.exception.OctBaseException;
import com.gdrcu.server.OctTcpInServer;

public class NettyTest {
	
	public static void main(String [] args){
		
		
	}
	
	
	
	@Test
	public  void testNetty(){
		
		OctTcpInServer server  = new OctTcpInServer("tst", 6090, "utf-8");
		try {
			server.start();
		} catch (OctBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
