package com.xbeer.test;

import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class TcpClientHandler implements ChannelHandler {

	
	private static final Logger logger = Logger.getLogger(TcpClientHandler.class);

	
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if(msg instanceof ByteBuf){
			ByteBuf buf = (ByteBuf)msg;
			byte[] dst = new byte[buf.capacity()];
			buf.readBytes(dst);
			logger.info("client接收到服务器返回的消息:"+new String(dst));
			ReferenceCountUtil.release(msg);
		}else{
			logger.warn("error object");
		}
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext arg0, Throwable arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void handlerAdded(ChannelHandlerContext arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void handlerRemoved(ChannelHandlerContext arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
