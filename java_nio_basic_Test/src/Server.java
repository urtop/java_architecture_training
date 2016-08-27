import com.sun.org.apache.xpath.internal.SourceTree;
import sun.reflect.generics.tree.ByteSignature;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by Mark Tao on 2016/8/27 14:31 14:35 14:35.
 */
public class Server implements Runnable {

    private Selector selector;

    private ByteBuffer readBuf = ByteBuffer.allocate(1024);

    private ByteBuffer writeBuf = ByteBuffer.allocate(1024);

    public Server(int port) {
        try {
            this.selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.bind(new InetSocketAddress(port));

            serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server Start,port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }


    @Override
    public void run() {

        while (true) {
            try {
                this.selector.select();

                Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();

                while (keys.hasNext()) {

                    SelectionKey key = keys.next();

                    keys.remove();

                    if (key.isValid()) {
                        if (key.isAcceptable()) {
                            this.accept(key);
                        }
                    }

                    if (key.isReadable()) {
                        this.read(key);
                    }

                    if (key.isWritable()) {
                        this.write(key);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void read(SelectionKey key) {

        this.readBuf.clear();

        SocketChannel socketChannel = (SocketChannel) key.channel();

        try {
            int count = socketChannel.read(readBuf);

            if (count == -1) {
                key.channel().close();
                key.cancel();
                return;
            }

            this.readBuf.flip();

            byte[] bytes = new byte[this.readBuf.remaining()];

            //从读缓冲里，拿到数据
            this.readBuf.get(bytes);

            System.out.println("Server read: " + new String(bytes));

            //读取完毕以后 注册写事件,
            socketChannel.register(this.selector, SelectionKey.OP_WRITE);

        } catch (IOException e) {
            this.closeChannel(key);
            e.printStackTrace();
        }


    }

    private void accept(SelectionKey key) {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

        try {
            SocketChannel socketChannel = serverSocketChannel.accept();

            socketChannel.configureBlocking(false);

            System.out.println("New Client connected: "+socketChannel.getRemoteAddress());

            //accept以后，把拿到的对象注册为read事件
            socketChannel.register(this.selector, SelectionKey.OP_READ);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(SelectionKey key) {
        this.writeBuf.clear();
        SocketChannel socketChannel = (SocketChannel) key.channel();

        byte[] bytes = new byte[1024];

        bytes = "Hi from Sever".getBytes();

        this.writeBuf.put(bytes);

        this.writeBuf.flip();

        try {
            socketChannel.write(this.writeBuf);


            //数据写完以后立即注销写事件，不然会一直被出发
            key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);

            this.writeBuf.clear();

            //写完以后，我要继续读客户端来的请求吧。so....
            socketChannel.register(this.selector, SelectionKey.OP_READ);

        } catch (IOException e) {
            this.closeChannel(key);
            e.printStackTrace();
        }

    }


    private void closeChannel(SelectionKey key){
        try {
            key.channel().close();
            key.cancel();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        new Thread(new Server(1119)).start();
    }
}
