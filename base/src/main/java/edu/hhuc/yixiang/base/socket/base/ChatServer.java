package edu.hhuc.yixiang.base.socket.base;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/12/13 15:26:58
 */
public interface ChatServer {
    Integer DEFAULT_PORT = 8888;

    void start(int port);
}
