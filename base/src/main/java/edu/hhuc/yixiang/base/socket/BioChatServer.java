package edu.hhuc.yixiang.base.socket;

import edu.hhuc.yixiang.base.socket.base.ChatServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/12/11 16:47:48
 */
public class BioChatServer implements ChatServer {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            logger.info("服务已启动");
            while (true) {
                Socket socket = serverSocket.accept();
                Message message = MessageHandler.read(socket.getInputStream());
                String clientName = message.getContent();
                if (ConnectionHolder.exists(clientName)) {
                    logger.error("客户端已存在：{}", clientName);
                    socket.close();
                    continue;
                }
                logger.info("【{}】已登陆", clientName);
                ConnectionHolder.addClient(clientName, socket);
                new Thread(new MessageRouter(ConnectionHolder.getClient(clientName))).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
