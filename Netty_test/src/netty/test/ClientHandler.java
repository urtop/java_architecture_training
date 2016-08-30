package netty.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			//do something msg
			ByteBuf buf = (ByteBuf)msg;
			byte[] data = new byte[buf.readableBytes()];
			buf.readBytes(data);
			String request = new String(data, "utf-8");
			System.out.println("Client: " + request);


            //send data to server
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String result = br.readLine();
			ctx.channel().writeAndFlush(Unpooled.copiedBuffer(result.getBytes()));

			
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
