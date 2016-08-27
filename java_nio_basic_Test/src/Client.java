import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by Mark Tao on 2016/8/27 14:31.
 */
public class Client implements Runnable {

    private Selector selector;

    private ByteBuffer readBuf = ByteBuffer.allocate(1024);

    private ByteBuffer writeBuf = ByteBuffer.allocate(1024);

    public Client(int port) {
        try {
            this.selector = Selector.open();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", port));
//            socketChannel.register(this.selector, SelectionKey.OP_READ);
//            socketChannel.register(this.selector, SelectionKey.OP_WRITE);
            socketChannel.register(this.selector, SelectionKey.OP_CONNECT);
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

                        if (key.isConnectable()) {
                            this.write(key);
                        }

                        if (key.isReadable()) {
                            this.read(key);
                        }

                        if (key.isWritable()) {
                            this.write(key);
                        }
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


    private void write(SelectionKey key) {

        SocketChannel socketChannel = (SocketChannel) key.channel();
        try {
            writeTotheChannel(socketChannel,key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeTotheChannel(SocketChannel socketChannel,SelectionKey key) throws IOException {

          //判断是否连接成功（三次握手OK），注意，这里和连接是否就绪（OP_Connect）不是一个概念
        if (socketChannel.finishConnect()) {

            this.writeBuf.clear();
            byte[] bytes;

            bytes = "Hi from Client".getBytes();

            this.writeBuf.put(bytes);

            this.writeBuf.flip();

            socketChannel.write(this.writeBuf);
            //数据写完以后立即注销写事件，不然会一直被出发
            key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);

            socketChannel.register(this.selector, SelectionKey.OP_READ);
            this.writeBuf.clear();
        }

    }


    private void closeChannel(SelectionKey key) {
        try {
            key.channel().close();
            key.cancel();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        new Thread(new Client(1119)).start();
    }

}
