package com.gdrcu.service;

import org.springframework.stereotype.Service;

import com.gdrcu.OctContext;
import com.gdrcu.common.AbstractOctBaseService;
import com.gdrcu.common.IMessageObject;
import com.gdrcu.exception.OctBaseException;

@Service
public class MacCheckService extends AbstractOctBaseService{

	
	public MacCheckService(){
		
	}
	
	 
	public void invoke(IMessageObject msgObj,OctContext ctx) throws OctBaseException {
		
		System.out.println("macCheckService invoke");
	}


	
}
