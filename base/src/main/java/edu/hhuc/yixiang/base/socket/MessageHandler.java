package edu.hhuc.yixiang.base.socket;

import edu.hhuc.yixiang.common.utils.JsonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/12/11 21:24:44
 */
public class MessageHandler {

    public static Message read(InputStream inputStream) throws IOException {
        int high = inputStream.read();
        int low = inputStream.read();
        int length = (high << 8) + low;
        byte[] data = new byte[length];
        inputStream.read(data);
        String json = new String(data, StandardCharsets.UTF_8);
        return JsonUtil.parse(json, Message.class);
    }

    public static void write(OutputStream outputStream, Message message) throws IOException {
        String json = JsonUtil.toJson(message);
        byte[] data = json.getBytes(StandardCharsets.UTF_8);
        int length = data.length;
        outputStream.write(length >> 8);
        outputStream.write(length);
        outputStream.write(data);
    }
}
