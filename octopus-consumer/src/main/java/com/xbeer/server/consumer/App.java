package com.xbeer.server.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xbeer.server.consumer.demo.Demo;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {	
    	ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/spring-octopus-consumer.xml");
    	Demo bean = (Demo)context.getBean("demo");
    	System.out.println(bean.getStr());
    	System.out.println(bean.demo("opi"));
    	System.out.println( "Hello World!" +context);
    	
    }
}
