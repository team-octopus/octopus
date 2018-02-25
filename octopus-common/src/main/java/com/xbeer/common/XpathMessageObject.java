package com.xbeer.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;

import com.xbeer.exception.OctBaseException;
import com.xbeer.utils.StringUtil;

public class XpathMessageObject implements IMessageObject {

	private Document doc;

	public IMessageObject with(String in) throws DocumentException {

		this.doc = DocumentHelper.parseText(in);

		return with(doc);

	}

	/**
	 * 从dom4j Document 生成
	 */
	public IMessageObject with(Document doc) {

		//全部变成了没有命名空间的方式，妥当？
		
		
		this.doc = doc;
		return this;

	}

	/***
	 * 
	 * 若没有找到对应的路径，则返回空 null
	 * 
	 */
	@Override
	public String getValue(String xpath) throws OctBaseException {

		String xmlns = doc.getRootElement().getNamespaceURI();
		Node node = null;
		if (!StringUtil.isEmpty(xmlns)) {// 如果有命名空间，则需要加上命名空间

			String df = "df:";
			xpath = xpath.replace("//", "/");// 不允许用//
			xpath = xpath.replace("/", "/" + df);

			Map<String, String> nsMap = new HashMap<String, String>();
			nsMap.put("df", doc.getRootElement().getNamespaceURI());

			XPath x = doc.createXPath(xpath);
			x.setNamespaceURIs(nsMap);

			node = x.selectSingleNode(doc);

		} else {// 没有命名空间
			node = doc.selectSingleNode(xpath);

		}
		if (null == node) {
			return null;
		} else {
			
			List<Element> childs = ((Element) node).elements();
			int childsSize = childs.size();
			if (childsSize > 0) {
				StringBuilder sb = new StringBuilder();

				for (Element e : childs) {

					sb.append(copyNoXmlns(e).asXML());
				}

				return sb.toString();
			} else
				return node.getText();

		}

	}

	private Element copyNoXmlns(Element e) {

		Element newE = DocumentHelper.createElement(e.getName());

		for (Iterator<Attribute> it = e.attributeIterator(); it.hasNext();) {

			Attribute attr = it.next();

			newE.addAttribute(attr.getName(), attr.getValue());
		}
		newE.setText(e.getText());
		

		return newE;

	}

	@Override
	public void setValue(String xpath, String value) throws OctBaseException {

	}

}
