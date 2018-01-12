package com.gdrcu.function;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 解析config文件下的function.xml 读入缓存Map
 */
public class FunctionParser {

	//ef:getValue(${/sdo/root/xpath/name},'00000')
	public class Expression{
		String function;//函数名字
		Args [] args;//输入参数
		
		
		
	}
	/**
	 * 参数类型，可以是
	 * XPathValue 需要根据XPath的路径找到对应的值
	 * ActualValue 表示实际的值
	 * */
	public enum ArgsType{
		XPathValue,ActualValue
	}
	public class Args{
		ArgsType type;
		String input;
		
		
		
		
		
	}
	
	public static Object expressionRun(Expression expr ,Object obj){
		
		return null;
	}
	
	public static Map<String, Object> parser(File file)
			throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException {

		SAXReader reader = new SAXReader();
		Document document = reader.read(file);

		Element root = document.getRootElement();

		List<Element> funList = root.elements("function");

		Map<String, Object> map = new ConcurrentHashMap();

		for (Element e : funList) {
			String name = e.attributeValue("name");
			String clazz = e.attributeValue("class");

			Class classType = Class.forName(clazz);
			Object obj = classType.newInstance();
			
			map.put(name, obj);
		}
		

		return map;
	}

}
