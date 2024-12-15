package edu.hhuc.yixiang.base.socket;

import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/12/11 20:20:14
 */
public class ConnectionHolder {

    public static final Map<String, SocketConnection> CLIENTS = new ConcurrentHashMap<>();

    public static void addClient(String clientName, Socket socket) {
        CLIENTS.put(clientName, new SocketConnection(clientName, socket));
    }

    public static SocketConnection removeClient(String clientName) {
        return CLIENTS.remove(clientName);
    }

    public static SocketConnection getClient(String clientName) {
        return CLIENTS.get(clientName);
    }

    public static boolean exists(String clientName) {
        return CLIENTS.containsKey(clientName);
    }
}
