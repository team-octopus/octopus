package com.xbeer.connector;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.xbeer.utils.StringUtil;

/**
 * 
 * 配置文件配置连接器与交易码之间的关系
 * 
 * 
 * */
public class ConnectorFactory {
	private static Map<String, IConnector> connectorMap = new ConcurrentHashMap();
	
	
	

	public static void connectorRegister(IConnector connector) {

		connectorMap.put(connector.getName(), connector);

	}

	public static IConnector getConnector(String name) {
		
		return ! StringUtil.isEmpty(name) ? connectorMap.get(name) : null;

	}

	public static void clear() {
		connectorMap.clear();
	}
	

}
