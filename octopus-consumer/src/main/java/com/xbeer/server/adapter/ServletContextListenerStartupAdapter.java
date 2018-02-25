package com.xbeer.server.adapter;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.xbeer.utils.SpringContextUtil;  

/**
 * 负责启动netty进行监听
 * 
 * */
public class ServletContextListenerStartupAdapter implements ServletContextListener {

	
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
			
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		
	}

}
