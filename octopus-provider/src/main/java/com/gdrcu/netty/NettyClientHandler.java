package com.gdrcu.netty;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gdrcu.exception.OctBaseException;
import com.gdrcu.exception.OctErrorCode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.util.ReferenceCountUtil;
import io.netty.channel.ChannelHandlerContext;

@Sharable
public class NettyClientHandler implements ChannelHandler {

	private Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);
	
	private String encode;
	
	public NettyClientHandler(String encode){
		this.encode = encode;
	}
	
	
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if(msg instanceof ByteBuf){
			ByteBuf buf = (ByteBuf)msg;
			byte[] bytes = new byte[buf.readableBytes()];
			buf.readBytes(bytes);
			String msgStr;
			try {
				msgStr = new String(bytes, encode);
			} catch (UnsupportedEncodingException e) {

				logger.error("error", e);
				ReferenceCountUtil.release(msg);
				
				throw new OctBaseException(e, OctBaseException.Level.I, OctErrorCode.INNER_ERROR_CODE);
				
			}
			logger.info("recieve msg from server :{}",msgStr);
			
			
			
			ReferenceCountUtil.release(msg);
		}else{
			logger.warn("error object");
		}
		
	}

	
	@Override
	public void exceptionCaught(ChannelHandlerContext arg0, Throwable arg1) throws Exception {

		logger.error("error",arg1);
		
		arg0.close();

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
