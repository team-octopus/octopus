package com.xbeer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import com.xbeer.exception.OctBaseException;
import com.xbeer.server.OctTcpInServer;
import com.xbeer.utils.SpringContextUtil;


@ImportResource({  "classpath:spring.xml" })
@SpringBootApplication(scanBasePackages = "com")  
	
public class SpringTest {

	
	
	public static void main(String [] args){
		
		
		SpringApplication.run(SpringTest.class, args);
		
		
        
        
        
        OctTcpInServer server = (OctTcpInServer) SpringContextUtil.getBean("octinserver");
        try {
			server.start();
		} catch (OctBaseException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
        // press any key to
	}
}
