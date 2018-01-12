package com.gdrcu.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class OctBeanFactory {
	
	@Autowired
	static
	ApplicationContext context;
	
	
	
	public static Object getBean(String name){
		
		return context.getBean("name");
	}
	
	
}
