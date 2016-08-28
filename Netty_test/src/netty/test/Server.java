package netty.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Mark Tao on 2016/8/28 15:59.
 */


/**
 * 详细的说明参考 http://ifeve.com/netty5-user-guide/
 */
public class Server {
    public static void main(String[] args) throws InterruptedException {

        //1 这个线程组用来处理客户端连接的工作
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)

        //2 这个线程组用来处理具体的业务工作，就是干活的
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //ServerBootstrap 是一个启动NIO服务的辅助启动类。你可以在这个服务中直接使用Channel，但是这会是一个复杂的处理过程，在很多情况下你并不需要这样做。
        ServerBootstrap b = new ServerBootstrap(); // (2)

        //关联2个工作组
        b.group(bossGroup, workerGroup)

                //指定使用NioServerSocketChannel这种类型的通道
                .channel(NioServerSocketChannel.class)

                //绑定具体的事件处理器
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ServerHandler());
                    }
                })

                //下面的配置都是针对NioServerSocketChannel的
                .option(ChannelOption.SO_BACKLOG,128)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.SO_REUSEADDR,true);


        ChannelFuture f = b.bind(8765).sync();

        //阻塞主函数，不然主函数执行完毕，当前程序就结束了
        f.channel().closeFuture().sync();

        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();


    }
}
