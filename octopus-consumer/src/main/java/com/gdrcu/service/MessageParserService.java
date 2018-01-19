package com.gdrcu.service;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.XPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gdrcu.OctContext;
import com.gdrcu.common.AbstractOctBaseService;
import com.gdrcu.common.IMessageObject;
import com.gdrcu.common.XpathMessageObject;
import com.gdrcu.exception.OctBaseException;
import com.gdrcu.exception.OctBaseException.Level;
import com.gdrcu.exception.OctErrorCode;

@Service
public class MessageParserService extends AbstractOctBaseService{

	private Logger logger =  LoggerFactory.getLogger(MessageParserService.class);
	
	private String parserRule ;
	
	
	public MessageParserService(){
		
	}
	
	public void setRule(String rule){
		this.parserRule = rule;
	}
	 
	public void invoke(IMessageObject msgObj,OctContext ctx) throws OctBaseException {
		
		
		String data = ctx.getReceiveMsg();
		
		//假设报文前5位就是长度
		String strLen = data.substring(0, 5);
		
		
		//检查长度是否合理
		int len = Integer.parseInt(strLen);
		
		
		if(len > 0){
			
			
			
		}
		
		
		String body = data.substring(5,data.length());
		
		logger.info("receive body:{}",body);
		 try {
			Document doc =  DocumentHelper.parseText(body);
			
			msgObj  =  new XpathMessageObject().with(doc); 
			
			StringBuilder sb = new StringBuilder();
			
			//xmlns处理
			
			String xmlns = doc.getRootElement().getNamespaceURI();
			
			String codeBefore = null;
			String codeAfter = null;
			if(null == xmlns)
			{
				
				codeBefore = doc.selectSingleNode("//service/SYS_HEAD/SvcCd").getText();
				codeAfter = doc.selectSingleNode("//service/SYS_HEAD/SvcScn").getText();
				
			}else{
				String df = "df:";
				
				Map<String, String>nsMap=new HashMap<String, String>();  
				nsMap.put("df",doc.getRootElement().getNamespaceURI());  
				XPath x = doc.createXPath("//"+df+"service/"+df+"SYS_HEAD/"+df+"SvcCd");  
			    x.setNamespaceURIs(nsMap);  
			    
			    XPath x1 = doc.createXPath("//"+df+"service/"+df+"SYS_HEAD/"+df+"SvcScn");  
			    x1.setNamespaceURIs(nsMap);  
				
			
				codeBefore = x.selectSingleNode(doc).getText();
				codeAfter = x1.selectSingleNode(doc).getText();
				
			}
			
			
			
			
			sb.append(codeBefore);
			sb.append(codeAfter);
			
			ctx.setTranCode(sb.toString());
			logger.info("receive trancode :{}",sb.toString());
			
			
		} catch (DocumentException e) {
			
			logger.error("error",e);
			
			
			throw new OctBaseException(e,Level.II,OctErrorCode.INNER_ERROR_CODE);
		}
		 
		
		
		
		if(null != this.service){
			
			this.service.invoke(msgObj, ctx);
			
		}
	}


	


	
}
