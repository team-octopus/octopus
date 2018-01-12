package com.gdrcu.in.netty;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.gdrcu.OctContext;
import com.gdrcu.common.IMessageObject;
import com.gdrcu.common.IOctBaseService;
import com.gdrcu.common.XpathMessageObject;
import com.gdrcu.common.utils.TransUtil;
import com.gdrcu.exception.OctBaseException;
import com.gdrcu.exception.OctErrorCode;
import com.gdrcu.factory.ApplicationContextAdapter;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;

@Sharable
public class TCPInHandler extends AbstractNettyHandler {
	
	private Logger logger =  LoggerFactory.getLogger(TCPInHandler.class);
	
	// 定义编码字符
	private String encode;// io encode ,if not equals with Application encode
							// ,should convert .

	
	
	
	private IOctBaseService baseService;
	
	private String channel;
	
	public TCPInHandler(String channel,String encode) {
		this.encode = encode;
		this.channel = channel;
	}
	
	public void setRootService(IOctBaseService baseService){
		
		this.baseService = baseService;
	}

	
	@Override
	public void channelRead0(ChannelHandlerContext arg0, ByteBuf arg1) throws OctBaseException    {
		// TODO Auto-generated method stub
		byte[] bytes = new byte[arg1.readableBytes()];
		arg1.readBytes(bytes);
		String ar1Str;
		try {
			ar1Str = new String(bytes, encode);
		} catch (UnsupportedEncodingException e) {
			
			logger.error("error",e);
			throw new OctBaseException(e,OctBaseException.Level.I,OctErrorCode.INNER_ERROR_CODE);
			
		}
		
		OctContext ctx = new OctContext();
		ctx.setChannalName(this.channel);
		
		ctx.setReceiveDate(new Date());
		ctx.setReceiveMsg(bytes);
		
		
		IMessageObject obj = new XpathMessageObject();
		
		
		
		//截取长度，MAC等
		
		
		//invoke service interface
		
		try{
			if(null != baseService){
				baseService.invoke(obj,ctx);
			}
			
		}catch(OctBaseException e){
			//返回错误给前台
			
		}
		
		
		
		
		
		// 定义交易码，用于请求报文进行判断用，如果请求报文中包含这个交易码则说明注册了并可用，否则不可用
		// 在往后开发中会将交易码配置到配置文件中
		
		String tranCode = ar1Str.substring(0, 13);
		// 创建服务标识 S + tranCode形式，目前通过TransUtil builderServiceName来创建
		// 工具类统一放common了项目中
		String serviceCode = TransUtil.buildServiceName(tranCode);
		
		// 根据服务码获取对应的接口bean
		
			
			Object bean = ApplicationContextAdapter.getBean(serviceCode);
			if (bean != null) {
				// 创建参数数组，用于封闭请求数据
				Object[] param = new Object[1];
				param[0] = ar1Str;
				// 通过反射获取接口的通讯方法并调用
				Method method = ReflectionUtils.findMethod(bean.getClass(), "doSend", String.class);
				//获取返回数据
				Object resp = ReflectionUtils.invokeMethod(method, bean,param);
				//写出时不能用 new String(s.getBytes,"gbk");的形式转码，没效果
				try {
					arg0.channel().writeAndFlush(resp.toString().getBytes(encode));
				} catch (UnsupportedEncodingException e) {
					
					logger.error("error",e);
					throw new OctBaseException(e,OctBaseException.Level.I,OctErrorCode.INNER_ERROR_CODE);
				}
			}else{
				//如果为空，返回前端找不到交易码错误
				
				return;
			}
		

	}

	@Override
	public boolean acceptInboundMessage(Object msg) throws Exception {
		// TODO Auto-generated method stub
		return super.acceptInboundMessage(msg);
	}

	@Override
	public void channelRead(ChannelHandlerContext arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		super.channelRead(arg0, arg1);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelInactive(ctx);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelReadComplete(ctx);
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelWritabilityChanged(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		// TODO Auto-generated method stub
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.handlerRemoved(ctx);
	}

	@Override
	public boolean isSharable() {
		// TODO Auto-generated method stub
		return super.isSharable();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

}
