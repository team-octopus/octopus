package com.gdrcu.provider;

import com.gdrcu.OctContext;
import com.gdrcu.api.InvokeResult;
import com.gdrcu.api.service.BaseInterface;
import com.gdrcu.connector.IConnector;
import com.gdrcu.exception.OctBaseException;
import com.gdrcu.exception.OctErrorCode;
import com.gdrcu.tran.TranHelper;

public class AbstractInterfaceImpl implements BaseInterface {

	//绑定哪个connector，通过配置文件绑定
	
	

	@Override
	public InvokeResult doSend(String msg, OctContext ctx) throws OctBaseException {
		
		InvokeResult result = new InvokeResult();
		IConnector connector = TranHelper.getTranConnector(ctx.getTranCode());
		
		ctx.setProviderName("tst");
		
		if( null == connector){
			result.setSuccess(false);
			result.setCode(OctErrorCode.TRANCODE_NOT_EXIST);
			
			result.setContext(ctx);
			return result;
		}
		
		//connector.connect();
		//connector.send(ctx.getReceiveMsg(),ctx);
		
		result.setContext(ctx);
		result.setSuccess(true);
		
		return result;
	}
	
	
	
}
