package edu.hhuc.yixiang.base.socket;

import edu.hhuc.yixiang.common.constant.SocketConstants;
import edu.hhuc.yixiang.common.constant.StringConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/12/11 16:47:56
 */
public class Client {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    private final String clientName;

    // 处理粘性发送消息
    private String recentReceiver;

    public Client(String clientName) {
        this.clientName = clientName;
    }

    public void connect() {
        try {
            Socket socket = new Socket(SocketConstants.SERVER_ADDRESS, SocketConstants.PORT);
            Message message = Message.of(this.clientName, SocketConstants.SERVER, this.clientName);
            MessageHandler.write(socket.getOutputStream(), message);
            logger.info("{}：已经成功连接到服务器", this.clientName);
            new Thread(() -> reading(socket)).start();
            chatting(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void chatting(Socket socket) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (SocketConstants.EXIT.equals(line)) {
                return;
            }
            String[] data = line.split(StringConstants.COLON);
            if (StringUtils.isBlank(recentReceiver) && data.length != 2) {
                logger.error("消息格式错误，请重新输入，例如：【小明:你好】，错误消息：{}", line);
                continue;
            }
            String content = line;
            if (data.length == 2) {
                recentReceiver = data[0];
                content = data[1];
            }
            try {
                Message message = Message.of(this.clientName, recentReceiver, content);
                MessageHandler.write(socket.getOutputStream(), message);
            } catch (IOException e) {
                logger.error("消息发送错误，请重试", e);
            }
        }
    }

    private void reading(Socket socket) {
        try {
            while (true) {
                Message message = MessageHandler.read(socket.getInputStream());
                logger.info("【from {}】：{}", message.getSender(), message.getContent());
                if (!SocketConstants.SERVER.equals(message.getSender())) {
                    this.recentReceiver = message.getSender();
                }
            }
        } catch (IOException e) {
            logger.error("程序错误，客户端已退出", e);
        }
    }
}
