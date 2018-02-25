package com.xbeer.server.in.netty;

import java.util.concurrent.TimeUnit;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.LastHttpContent;

/**
 * GET / HTTP/1.1
Accept: 
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: httpie.org
User-Agent: HTTPie/0.9.2


HTTP/1.1 302 Found
Connection: close
Content-Length: 292
Content-Type: text/html; charset=iso-8859-1
Date: Wed, 20 Apr 2016 07:10:53 GMT
Location: https://github.com/jkbrzt/httpie
Server: Apache/2.2.15 (CentOS)
X-Awesome: Thanks for trying HTTPie :)

作者：whthomas
链接：https://www.jianshu.com/p/23c612e08f8c
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 
 * */
public class HttpHandler extends SimpleChannelInboundHandler<Object> {

    private static final byte[] CONTENT = {'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd'};
    private boolean keepAlive;
    
    public HttpHandler() {
        super();
       
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
       
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {/*
        if (msg instanceof HttpRequest) {

            HttpRequest request = (HttpRequest) msg;

            if (request.getMethod() != HttpMethod.GET) {
                throw new IllegalStateException("请求不是GET请求.");
            }

            if (HttpHeaders.is100ContinueExpected(request)) {
                ctx.write(new DefaultFullHttpResponse(HTTP_1_1, CONTINUE));
            }

            keepAlive = HttpHeaders.isKeepAlive(request);
        }

        if(msg instanceof LastHttpContent){

            // 模拟事务处理
            TimeUnit.SECONDS.sleep(1);

            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(CONTENT));
            response.headers().set(CONTENT_TYPE, "text/plain");
            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());

            if (!keepAlive) {
                ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
                ctx.writeAndFlush(response);
            }
        }
    */}
}

