package com.xbeer.common;

import com.xbeer.OctContext;
import com.xbeer.exception.OctBaseException;

public interface IOctBaseService {

	
	
	public void invoke(IMessageObject msgObj,OctContext ctx) throws OctBaseException;
	
	public void setBindService(IOctBaseService service);
}
