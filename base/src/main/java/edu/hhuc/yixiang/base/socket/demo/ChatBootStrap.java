package edu.hhuc.yixiang.base.socket.demo;

import edu.hhuc.yixiang.base.socket.NioChatServer;
import edu.hhuc.yixiang.common.constant.SocketConstants;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/12/11 20:42:34
 */
public class ChatBootStrap {
    public static void main(String[] args) {
        // BioChatServer server = new BioChatServer();
        NioChatServer server = new NioChatServer();
        server.start(SocketConstants.PORT);
    }
}
