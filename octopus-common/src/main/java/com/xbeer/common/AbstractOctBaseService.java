package com.xbeer.common;

public abstract class AbstractOctBaseService implements IOctBaseService {

	protected IOctBaseService service;
	
	public void setBindService(IOctBaseService service){
		this.service = service;
	}
}
