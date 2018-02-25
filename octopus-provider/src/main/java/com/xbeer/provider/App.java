package com.xbeer.provider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xbeer.provider.demo.ProviderDemo;

/**
 * 测试集成spring是否成功
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/spring-octopus-provider.xml");
    	ProviderDemo bean = (ProviderDemo)context.getBean("ProviderDemo");
    	System.out.println(bean.getPort());
    	bean.test();
    	System.out.println( "Hello World!" );
    }
}
