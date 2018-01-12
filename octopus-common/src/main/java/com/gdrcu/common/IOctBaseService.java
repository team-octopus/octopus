package com.gdrcu.common;

import com.gdrcu.OctContext;
import com.gdrcu.exception.OctBaseException;

public interface IOctBaseService {

	
	
	public void invoke(IMessageObject msgObj,OctContext ctx) throws OctBaseException;
	
	public void setBindService(IOctBaseService service);
}
