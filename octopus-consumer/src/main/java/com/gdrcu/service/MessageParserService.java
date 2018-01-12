package com.gdrcu.service;

import org.springframework.stereotype.Service;

import com.gdrcu.OctContext;
import com.gdrcu.common.AbstractOctBaseService;
import com.gdrcu.common.IMessageObject;
import com.gdrcu.common.IOctBaseService;
import com.gdrcu.exception.OctBaseException;

@Service
public class MessageParserService extends AbstractOctBaseService{

	private String parserRule ;
	
	
	public MessageParserService(){
		
	}
	
	public void setRule(String rule){
		this.parserRule = rule;
	}
	 
	public void invoke(IMessageObject msgObj,OctContext ctx) throws OctBaseException {
		
		System.out.println("messageParserService invoke");
		
		if(null != this.service){
			
			this.service.invoke(msgObj, ctx);
			
		}
	}


	


	
}
