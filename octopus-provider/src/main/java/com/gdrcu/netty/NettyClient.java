package com.gdrcu.netty;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gdrcu.exception.OctBaseException;
import com.gdrcu.exception.OctBaseException.Level;
import com.gdrcu.utils.StringUtil;
import com.gdrcu.exception.OctErrorCode;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class NettyClient {

	private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);

	Bootstrap bootstrap;
	
	Channel channel;
	NettyClientHandler handler;

	public NettyClient(NettyClientHandler handler) {
		this.handler = handler;
		
	}

	private Bootstrap getBootstrap() {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class);
		b.handler(new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
				pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
				pipeline.addLast("handler", handler);
			}
		});
		// b.option(ChannelOption.SO_KEEPALIVE, true);
		return b;
	}

	public void connect(String ip, int port) throws OctBaseException {
		this.bootstrap = getBootstrap();

		try {
			channel = bootstrap.connect(ip, port).sync().channel();
		} catch (Exception e) {
			logger.error(String.format("连接Server(IP[%s],PORT[%s])失败", ip, port), e);
			throw new OctBaseException(e, Level.IV, OctErrorCode.INNER_ERROR_CODE, ip, port + "");
		}

	}

	public void send(ByteBuf msg) throws UnsupportedEncodingException, InterruptedException {
		

		channel.writeAndFlush(msg).sync();

	}

}
