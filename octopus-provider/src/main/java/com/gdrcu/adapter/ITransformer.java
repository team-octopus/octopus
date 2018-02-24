package com.gdrcu.adapter;

/**
 * 报文转换器
 * 搜索在resources/xxx/transform目录下的按服务码定义的文件
 * xxx为某个连接器的名字
 * 如2001000134.xml
 * 格式如下
 * <?xml  verson="1.0" encoding="utf-8">
 * <service pacakage_type="xml" store_model="utf-8">
 * <BODY>
 * 	<array>
 * 		<AcctInfo type="array" is_struct="false" metadataid="xxxx"
 * chinese_name="" expression="/sdo/xx" isSdoHeader="true"></ArrayInfo>
 * 		
 * 
 * */
public interface ITransformer {
	public String transfer(String in,ITransferRule rule) ;
	

}
