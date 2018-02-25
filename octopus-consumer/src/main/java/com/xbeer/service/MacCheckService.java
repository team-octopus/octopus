package com.xbeer.service;

import org.springframework.stereotype.Service;

import com.xbeer.OctContext;
import com.xbeer.common.AbstractOctBaseService;
import com.xbeer.common.IMessageObject;
import com.xbeer.exception.OctBaseException;

@Service
public class MacCheckService extends AbstractOctBaseService{

	
	public MacCheckService(){
		
	}
	
	 
	public void invoke(IMessageObject msgObj,OctContext ctx) throws OctBaseException {
		
		System.out.println("macCheckService invoke");
	}


	
}
