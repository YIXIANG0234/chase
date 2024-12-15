package edu.hhuc.yixiang.base.socket.demo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * channel：通道
 * buffer：缓冲区
 * selector：选择器
 *
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/12/13 16:55:12
 */
public class FileChannelDemo {
    /**
     * main方法，测试一下filechannel的功能
     *
     * @param args
     */
    public static void main(String[] args) {
        String path = "/Users/yixiang/gitwork/yixiang/chase/base/src/main/java/edu/hhuc/yixiang/base/socket/NioChatServer.java";
        ByteBuffer byteBuffer = ByteBuffer.allocate(50);
        CharBuffer charBuffer = CharBuffer.allocate(50);
        CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
        StringBuilder sb = new StringBuilder();
        try (FileChannel channel = FileChannel.open(Path.of(path), StandardOpenOption.READ)) {
            while (channel.read(byteBuffer) != -1) {
                // 切换为读模式
                byteBuffer.flip();
                decoder.decode(byteBuffer, charBuffer, false);

                // 切换为读模式
                charBuffer.flip();
                sb.append(charBuffer.toString());

                // 清空缓冲区
                charBuffer.clear();
                byteBuffer.clear();
            }
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
