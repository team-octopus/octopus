package com.gdrcu.in.netty;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.util.ReflectionUtils;

import com.gdrcu.OctContext;
import com.gdrcu.common.IMessageObject;
import com.gdrcu.common.IOctBaseService;
import com.gdrcu.common.XpathMessageObject;
import com.gdrcu.exception.ExceptionHandler;
import com.gdrcu.exception.OctBaseException;
import com.gdrcu.exception.OctErrorCode;
import com.gdrcu.utils.SpringContextUtil;
import com.gdrcu.utils.StringUtil;
import com.gdrcu.utils.TransUtil;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;

@Sharable
public class TCPInHandler extends AbstractNettyHandler {

	private Logger logger = LoggerFactory.getLogger(TCPInHandler.class);

	// 定义编码字符
	private String encode;// io encode ,if not equals with Application encode
							// ,should convert .

	private IOctBaseService baseService;

	private String channel;

	public TCPInHandler(String channel, String encode) {
		this.encode = encode;
		this.channel = channel;
	}

	public void setRootService(IOctBaseService baseService) {

		this.baseService = baseService;
	}

	private void send(ChannelHandlerContext arg0, String msg) {

		try {
		
			logger.info("send msg :{}",msg);
			ByteBuf bbuffer = Unpooled.copiedBuffer(StringUtil.changeEncode(msg, encode));
			arg0.channel().writeAndFlush(bbuffer).addListener(ChannelFutureListener.CLOSE);

		} catch (UnsupportedEncodingException e1) {

			logger.error("error", e1);
		}
	}

	@Override
	public void channelRead0(ChannelHandlerContext arg0, ByteBuf arg1) throws OctBaseException {
		// TODO Auto-generated method stub
		byte[] bytes = new byte[arg1.readableBytes()];
		arg1.readBytes(bytes);
		String msg;
		try {
			msg = new String(bytes, encode);
		} catch (UnsupportedEncodingException e) {

			logger.error("error", e);

			throw new OctBaseException(e, OctBaseException.Level.I, OctErrorCode.INNER_ERROR_CODE);

		}

		logger.info("received msg:{}", msg);

		OctContext ctx = new OctContext();
		ctx.setChannalName(this.channel);

		ctx.setReceiveDate(new Date());
		ctx.setReceiveMsg(msg);

		IMessageObject obj = new XpathMessageObject();

		// 截取长度，MAC等

		// invoke service interface

		try {
			if (null != baseService) {
				baseService.invoke(obj, ctx);
			}

		} catch (OctBaseException e) {
			logger.error("error", e);
			// 返回错误给前台
			String errorMsg = ExceptionHandler.buildExceptionMsg(obj, ctx, e);
			this.send(arg0, errorMsg);
			return;

		}

		logger.info("obj is null {}", obj == null);
		// 定义交易码，用于请求报文进行判断用，如果请求报文中包含这个交易码则说明注册了并可用，否则不可用
		// 在往后开发中会将交易码配置到配置文件中

		String tranCode = ctx.getTranCode();
		// 创建服务标识 S + tranCode形式，目前通过TransUtil builderServiceName来创建
		// 工具类统一放common了项目中
		String serviceCode = TransUtil.buildServiceName(tranCode);

		// 根据服务码获取对应的接口bean
		Object bean = null;
		try {
			bean = SpringContextUtil.getBean(serviceCode);
		} catch (NoSuchBeanDefinitionException e) {
			logger.error("error", e);
			// 返回错误给前台
			String errorMsg = ExceptionHandler.buildExceptionMsg(obj, ctx,
					new OctBaseException(e, OctBaseException.Level.I, OctErrorCode.TRANCODE_NOT_EXIST));
			this.send(arg0, errorMsg);

			return;

		}
		if (bean != null) {
			// 创建参数数组，用于封闭请求数据
			Object[] param = new Object[1];
			param[0] = msg;
			// 通过反射获取接口的通讯方法并调用
			Method method = ReflectionUtils.findMethod(bean.getClass(), "doSend", String.class);
			// 获取返回数据
			Object resp = ReflectionUtils.invokeMethod(method, bean, param);
			// 写出时不能用 new String(s.getBytes,"gbk");的形式转码，没效果
			try {
				arg0.channel().writeAndFlush(resp.toString().getBytes(encode));
			} catch (UnsupportedEncodingException e) {

				logger.error("error", e);

				String errorMsg = ExceptionHandler.buildExceptionMsg(obj, ctx,
						new OctBaseException(e, OctBaseException.Level.I, OctErrorCode.INNER_ERROR_CODE));
				this.send(arg0, errorMsg);
				return;
			}
		} else {
			// 如果为空，返回前端找不到交易码错误

			return;
		}

	}
	

	

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			logger.warn("Unexpected exception from downstream.", cause);
			ctx.close();
		
	}


}
