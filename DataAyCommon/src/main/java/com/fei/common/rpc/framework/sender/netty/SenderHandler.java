package com.fei.common.rpc.framework.sender.netty;

import java.net.URL;

import com.fei.common.rpc.framework.RpcByteRequest;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;

public class SenderHandler extends ChannelHandlerAdapter{

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		if(msg instanceof RpcByteRequest){
			RpcByteRequest rpcRequest = (RpcByteRequest)msg ;
			URL url = rpcRequest.getUrl() ;  
			FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,HttpMethod.POST,url.getPath()) ; 
			request.headers().set(HttpHeaders.Names.CONTENT_TYPE,"application/json") ; 
			byte[] bytes = rpcRequest.packBytes() ; 
			request.headers().set(HttpHeaders.Names.CONTENT_LENGTH,bytes.length) ;
			request.headers().set(HttpHeaders.Names.HOST,"localhost:8080") ; 
			request.content().writeBytes(bytes) ; 
			ctx.write(request, promise);
			return ; 
		}
		super.write(ctx, msg, promise);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg instanceof DefaultFullHttpResponse){
			DefaultFullHttpResponse response = (DefaultFullHttpResponse)msg ; 
			System.err.println(response.getStatus() + " " + response.getProtocolVersion());
		}
		super.channelRead(ctx, msg);
	}
	
	
	
	
	
	
	
}
