package com.gdrcu.tran;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.gdrcu.connector.ConnectorFactory;
import com.gdrcu.connector.IConnector;

public class TranHelper {
	
	
	//trancode ,connector name
	private static Map<String ,String> tranConnMap = new ConcurrentHashMap();
	
	public static void put(String tranCode,String connectorName){
		
		tranConnMap.put(tranCode, connectorName);
	}
	
	
	public static IConnector getTranConnector(String trancode){
		
	
		return ConnectorFactory.getConnector(tranConnMap.get(trancode));
	}
	
}
