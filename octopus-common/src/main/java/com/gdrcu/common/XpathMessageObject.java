package com.gdrcu.common;

import org.dom4j.Document;
import org.dom4j.Node;

import com.gdrcu.exception.OctBaseException;

public class XpathMessageObject implements IMessageObject{
		
	private Document doc;
	
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

		Node node = doc.selectSingleNode(xpath);
		
		return null == node ? null :  node.getText();
	}
	@Override
	public void setValue(String xpath, String value) throws OctBaseException {
		
		
	}
	@Override
	public byte[] toByte() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
