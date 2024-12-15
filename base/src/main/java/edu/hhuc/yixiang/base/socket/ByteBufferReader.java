package edu.hhuc.yixiang.base.socket;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/12/13 16:59:28
 */
public class ByteBufferReader {
    public static String read(ByteBuffer byteBuffer, CharBuffer charBuffer, CharsetDecoder decoder) {
        byteBuffer.flip();
        decoder.decode(byteBuffer, charBuffer, false);

        charBuffer.flip();
        String result = charBuffer.toString();

        byteBuffer.clear();
        charBuffer.clear();
        return result;
    }
}
