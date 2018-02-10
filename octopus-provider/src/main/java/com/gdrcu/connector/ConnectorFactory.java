package com.gdrcu.connector;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.gdrcu.utils.StringUtil;

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
