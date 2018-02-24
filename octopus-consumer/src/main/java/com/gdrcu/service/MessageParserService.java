package com.gdrcu.service;

import java.io.IOException;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gdrcu.OctContext;
import com.gdrcu.common.AbstractOctBaseService;
import com.gdrcu.common.IMessageObject;
import com.gdrcu.exception.OctBaseException;
import com.gdrcu.exception.OctBaseException.Level;
import com.gdrcu.exception.OctErrorCode;
import com.gdrcu.utils.StringUtil;

@Service
public class MessageParserService extends AbstractOctBaseService {

	private Logger logger = LoggerFactory.getLogger(MessageParserService.class);

	private String parserRule;

	public MessageParserService() {

	}

	public void setRule(String rule) {
		this.parserRule = rule;
	}

	public void invoke(IMessageObject msgObj, OctContext ctx) throws OctBaseException {

		String data = ctx.getReceiveMsg();

		// 假设报文前5位就是长度
		String strLen = data.substring(0, 5);

		try {
			// 检查长度是否合理
			int len = Integer.parseInt(strLen);

			if (len > 0) {

			}

			String body = data.substring(5, data.length());

			logger.info("receive body:{}", body);

			Document doc = DocumentHelper.parseText(body);
			

			msgObj = msgObj.with(doc);
			
			//logger.info("transfer:{}",StringUtil.replaceBlank(doc.asXML()));

			StringBuilder sb = new StringBuilder();

			// xmlns处理

			String xmlns = doc.getRootElement().getNamespaceURI();
			
			//如果出现命名空间，则返回错误信息
			
			

			String codeBefore = null;
			String codeAfter = null;

			codeBefore = msgObj.getValue("/service/SYS_HEAD/SvcCd");
			codeAfter = msgObj.getValue("/service/SYS_HEAD/SvcScn");
			/*
			 * if(null == xmlns) {
			 * 
			 * codeBefore =
			 * doc.selectSingleNode("//service/SYS_HEAD/SvcCd").getText();
			 * codeAfter =
			 * doc.selectSingleNode("//service/SYS_HEAD/SvcScn").getText();
			 * 
			 * }else{ String df = "df:";
			 * 
			 * Map<String, String>nsMap=new HashMap<String, String>();
			 * nsMap.put("df",doc.getRootElement().getNamespaceURI()); XPath x =
			 * doc.createXPath("//"+df+"service/"+df+"SYS_HEAD/"+df+"SvcCd");
			 * x.setNamespaceURIs(nsMap);
			 * 
			 * XPath x1 =
			 * doc.createXPath("//"+df+"service/"+df+"SYS_HEAD/"+df+"SvcScn");
			 * x1.setNamespaceURIs(nsMap);
			 * 
			 * 
			 * codeBefore = x.selectSingleNode(doc).getText(); codeAfter =
			 * x1.selectSingleNode(doc).getText();
			 * 
			 * }
			 */

			sb.append(codeBefore);
			sb.append(codeAfter);

			ctx.setTranCode(sb.toString());
			logger.info("receive trancode :{}", sb.toString());

		} catch (DocumentException e) {

			logger.error("error", e);

			throw new OctBaseException(e, Level.II, OctErrorCode.INNER_ERROR_CODE);
		} catch (NumberFormatException e) {
			logger.error("error", e);

			throw new OctBaseException(e, Level.II, OctErrorCode.MESSAGE_FORMAT_ERROR);
		}

		if (null != this.service) {

			this.service.invoke(msgObj, ctx);

		}
	}
	private String asXml(Document document){
	    OutputFormat format = new OutputFormat();
	    
	    
	    // 设置换行 为false时输出的xml不分行
	    format.setNewlines(false); 
        // 生成缩进 
	   // format.setIndent(true); 
        // 指定使用tab键缩进
	   // format.setIndent("  "); 
        // 不在文件头生成  XML 声明 (<?xml version="1.0" encoding="UTF-8"?>) 
	   // format.setSuppressDeclaration(true);
        // 不在文件头生成  XML 声明 (<?xml version="1.0" encoding="UTF-8"?>)中加入encoding 属性
	   // format.setOmitEncoding(true);
	    
	    format.setExpandEmptyElements(true);
	    StringWriter out = new StringWriter();
	    XMLWriter writer = new XMLWriter(out, format);
	    try {
	        writer.write(document);
	        writer.flush();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }finally{
	    	try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    return out.toString();
	}

}
