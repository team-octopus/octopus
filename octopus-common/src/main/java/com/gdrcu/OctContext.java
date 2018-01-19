package com.gdrcu;
import java.util.Date;

public class OctContext {
	String channalName;//渠道名称
	String ip;//接入IP
	String innerUUID;//内部UUID
	Date receiveDate;//接收时间
	
	String  receiveMsg;//转换成应用内部编码字符串
	
	String tranCode ;//报文上送的交易码
	
	
	

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public String getReceiveMsg() {
		return receiveMsg;
	}

	public void setReceiveMsg(String receiveMsg) {
		this.receiveMsg = receiveMsg;
	}

	public String getChannalName() {
		return channalName;
	}

	public void setChannalName(String channalName) {
		this.channalName = channalName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getInnerUUID() {
		return innerUUID;
	}

	public void setInnerUUID(String innerUUID) {
		this.innerUUID = innerUUID;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	
	
	
	
}
