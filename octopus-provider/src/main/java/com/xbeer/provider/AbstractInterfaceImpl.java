package com.xbeer.provider;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xbeer.OctContext;
import com.xbeer.api.SendResult;
import com.xbeer.api.service.InnerBaseInterface;
import com.xbeer.connector.IConnector;
import com.xbeer.exception.OctBaseException;
import com.xbeer.exception.OctErrorCode;
import com.xbeer.tran.TranHelper;


public class AbstractInterfaceImpl implements InnerBaseInterface {

	Logger logger = LoggerFactory.getLogger(AbstractInterfaceImpl.class);
	
	
	//绑定哪个connector，通过配置文件绑定
	
	

	@Override
	public SendResult doSend(String msg, OctContext ctx) throws OctBaseException {
		
		SendResult result = new SendResult();
		IConnector connector = TranHelper.getTranConnector(ctx.getTranCode());
		
		logger.info("trancode:{} ' connector is {}",ctx.getTranCode(),connector);
		
		ctx.setProviderName("tst");
		
		if( null == connector){
			result.setSuccess(false);
			result.setCode(OctErrorCode.TRANCODE_NOT_EXIST);
			
			result.setContext(ctx);
			return result;
		}
		ctx.setProviderName(connector.getName());
		//connector.connect();
		connector.send(ctx.getReceiveMsg(),ctx);
		
		result.setContext(ctx);
		result.setSuccess(true);
		
		return result;
	}
	
	
	
}
