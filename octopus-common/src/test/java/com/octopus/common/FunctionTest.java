package com.octopus.common;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

import org.dom4j.DocumentException;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import com.xbeer.function.FunctionParser;

public class FunctionTest {

	
	@Test
	public void testFunParser(){
		
		File file = new File("D:\\workspace_octopus\\octopus-parent\\octopus-common\\src\\test\\resources\\functions.xml");
		try {
			Map<String ,Object > re = FunctionParser.parser(file);

			re.forEach((x,y) ->{
				
				Method method = ReflectionUtils.findMethod(y.getClass(), "test");
				//获取返回数据
				Object resp = ReflectionUtils.invokeMethod(method, y);
				
				
				
				
				System.out.println(resp);
				
			});
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
