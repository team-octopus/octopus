package com.gdrcu.common;

import com.gdrcu.exception.OctBaseException;

public interface IMessageObject {

	public String getValue(String xpath) throws OctBaseException;
	
	public void setValue(String xpath,String value) throws OctBaseException;
	
	
	
	
}
