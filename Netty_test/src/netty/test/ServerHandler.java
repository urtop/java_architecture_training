package netty.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by Mark Tao on 2016/8/28 15:47.
 */


public class ServerHandler extends ChannelHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ((ByteBuf)msg).release();

        try{
            ByteBuf buf = (ByteBuf) msg;

            //初始化data需要有长度信息
            byte[] data = new byte[buf.readableBytes()];

            //数据写入data
            buf.readBytes(data);

            String request = new String(data,"utf-8");
            System.out.println("Server:"+request);

        }finally {
            ReferenceCountUtil.release(msg);

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
