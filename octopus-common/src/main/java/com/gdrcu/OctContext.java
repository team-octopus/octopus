package com.gdrcu;
import java.util.Date;

public class OctContext  {
	String channelName;//渠道名称
	String channelIp;//接入IP
	
	String innerUUID;//内部UUID
	
	
	Date receiveDate;//接收时间
	
	String  receiveMsg;//转换成应用内部编码字符串
	
	String tranCode ;//报文上送的交易码
	
	String providerName;//提供者名称
	Date returnDate;//服务提供方返回时间
	String providerIp;
	
	String returnMsg;//服务方返回内容
	
	
	String errorCode;//错误码
	String errorMsg;//错误信息
	
	
	
	
	
	

	public String getChannelIp() {
		return channelIp;
	}

	public void setChannelIp(String channelIp) {
		this.channelIp = channelIp;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getProviderIp() {
		return providerIp;
	}

	public void setProviderIp(String providerIp) {
		this.providerIp = providerIp;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

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

	

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}


	public String getInnerUUID() {
		return innerUUID;
	}

	public void setInnerUUID(String innerUUID) {
		this.innerUUID = innerUUID;
	}
	
	
	
	
	
	
	
}
