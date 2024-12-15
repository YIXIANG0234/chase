package edu.hhuc.yixiang.base.socket;

import edu.hhuc.yixiang.base.socket.base.ChatServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/12/13 15:32:17
 */
public class NioChatServer implements ChatServer {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    @Override
    public void start(int port) {
        // 1. 创建服务器
        try (ServerSocketChannel ssc = ServerSocketChannel.open()) {
            final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            final CharBuffer charBuffer = CharBuffer.allocate(1024);
            final CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
            // 2. 绑定监听端口
            ssc.bind(new InetSocketAddress(port));
            final SocketChannel channel = ssc.accept();
            System.out.println("建立连接完成...");
            while (channel.read(byteBuffer) != -1) {
                String message = ByteBufferReader.read(byteBuffer, charBuffer, decoder);
                System.out.println("数据读取完成:" + message);
            }
        } catch (IOException e) {
            System.out.println("出现异常...");
        }

    }
}
