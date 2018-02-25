package com.xbeer.tran;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.xbeer.connector.ConnectorFactory;
import com.xbeer.connector.IConnector;

/**
 * 
 * 配置连接器与交易码之间的关系，启动时，需加载
 * resources/configs/connector_trancode.xml
 * key:trancode;
 * value:connector_name
 * 
 * */
public class TranHelper {
	
	
	//trancode ,connector name
	private static Map<String ,String> tranConnMap = new ConcurrentHashMap();
	
	public static void put(String tranCode,String connectorName){
		
		tranConnMap.put(tranCode, connectorName);
	}
	
	
	
	
	public static IConnector getTranConnector(String trancode){
		
		return ConnectorFactory.getConnector("BancsConnector");
	
		//return ConnectorFactory.getConnector(tranConnMap.get(trancode));
	}
	
}
