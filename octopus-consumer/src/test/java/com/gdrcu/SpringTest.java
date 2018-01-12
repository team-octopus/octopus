package com.gdrcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import com.gdrcu.exception.OctBaseException;
import com.gdrcu.factory.ApplicationContextAdapter;
import com.gdrcu.server.OctTcpInServer;


@ImportResource({  "classpath:spring.xml" })
@SpringBootApplication(scanBasePackages = "com")  
	
public class SpringTest {

	
	
	public static void main(String [] args){
		
		
		SpringApplication.run(SpringTest.class, args);
		
		
        
        
        
        OctTcpInServer server = (OctTcpInServer) ApplicationContextAdapter.getBean("octinserver");
        try {
			server.start();
		} catch (OctBaseException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
        // press any key to
	}
}
