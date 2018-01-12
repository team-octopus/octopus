package com.gdrcu.in.netty;

import com.gdrcu.common.IOctBaseService;

import io.netty.buffer.ByteBuf;
import io.netty.channel.SimpleChannelInboundHandler;

public abstract class AbstractNettyHandler extends  SimpleChannelInboundHandler<ByteBuf> {
	public abstract void setRootService(IOctBaseService service);
}
