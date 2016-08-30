package netty.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

/**
 * Created by Mark Tao on 2016/8/28 16:53.
 */


public class Client {
    public static void main(String[] args) throws Exception {

        EventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(workGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String result = br.readLine();

        //buf
        cf1.channel().writeAndFlush(Unpooled.copiedBuffer(result.getBytes()));
//        cf1.channel().flush();

        cf1.channel().closeFuture().sync();
        workGroup.shutdownGracefully();

    }
}
