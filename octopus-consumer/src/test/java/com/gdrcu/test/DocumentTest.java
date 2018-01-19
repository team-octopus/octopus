package com.gdrcu.test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.junit.Test;

public class DocumentTest {

	@Test
	public void testPaser(){
		
		try {
			Document doc = DocumentHelper.parseText("<Dependency>			<groupId>org.springframework.boot</groupId>			<artifactId>spring-boot-starter-aop</artifactId></Dependency>");
			
			doc.selectSingleNode("//dependency/groupId").getText();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
