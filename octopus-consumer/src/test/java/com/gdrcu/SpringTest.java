package com.gdrcu;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gdrcu.exception.OctBaseException;
import com.gdrcu.server.OctTcpInServer;

public class SpringTest {

	
	
	public static void main(String [] args){
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"spring.xml"});
        context.start();
        OctTcpInServer server = (OctTcpInServer) context.getBean("octinserver");
        try {
			server.start();
		} catch (OctBaseException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
        // press any key to
	}
}
