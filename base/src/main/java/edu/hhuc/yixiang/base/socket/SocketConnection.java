package edu.hhuc.yixiang.base.socket;

import lombok.Getter;

import java.net.Socket;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/12/11 20:27:20
 */
@Getter
public class SocketConnection {
    private final String clientName;
    private final Socket socket;

    public SocketConnection(String clientName, Socket socket) {
        this.clientName = clientName;
        this.socket = socket;
    }
}
