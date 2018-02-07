package com.gdrcu.common;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;

import com.gdrcu.exception.OctBaseException;
import com.gdrcu.utils.StringUtil;

public class XpathMessageObject implements IMessageObject{
		
	private Document doc;
	
	
	public IMessageObject with(String in) throws DocumentException{
		
		
        this.doc = DocumentHelper.parseText(in);
        
        
		
		return with(doc);
		
		

	}
	
	/**
	 * 从dom4j Document 生成
	 * */
	public IMessageObject with(Document doc){
		
	
		
		
		this.doc = doc;
			
			
		return this;
		
		
		
	}
	/***
	 * 
	 * 若没有找到对应的路径，则返回空  null
	 * 
	 * */
	@Override
	public String getValue(String xpath) throws OctBaseException {

		
		
	
		String xmlns = doc.getRootElement().getNamespaceURI();
		if( ! StringUtil.isEmpty(xmlns)){//如果有命名空间，则需要加上命名空间
			
			String df = "df:";
			xpath = xpath.replace("//", "/");//不允许用//
			xpath = xpath.replace("/", "/"+df);
			
			
			Map<String, String>nsMap=new HashMap<String, String>();  
			nsMap.put("df",doc.getRootElement().getNamespaceURI());  
			
			XPath x = doc.createXPath(xpath);  
		    x.setNamespaceURIs(nsMap);  
		    
		   Node node =  x.selectSingleNode(doc)  ;
		   return null == node ? null :  node.getText();
			
		}else{//没有命名空间
			Node node = doc.selectSingleNode(xpath);
			
			
			
			
			return null == node ? null :  node.getText();
		}
		
		
	}
	@Override
	public void setValue(String xpath, String value) throws OctBaseException {
		
		
		
	}
	

	
}
