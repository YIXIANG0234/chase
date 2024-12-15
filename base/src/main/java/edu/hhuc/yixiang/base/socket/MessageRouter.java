package edu.hhuc.yixiang.base.socket;

import edu.hhuc.yixiang.common.constant.SocketConstants;
import edu.hhuc.yixiang.common.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/12/11 20:26:25
 */
public class MessageRouter implements Runnable {
    private Logger logger = LoggerFactory.getLogger(MessageRouter.class);
    private final SocketConnection from;

    public MessageRouter(SocketConnection from) {
        this.from = from;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message receivedMessage = MessageHandler.read(from.getSocket().getInputStream());
                SocketConnection to = ConnectionHolder.getClient(receivedMessage.getReceiver());
                if (Objects.isNull(to)) {
                    logger.error("【{}】目标客户端不存在：{}", from.getClientName(), receivedMessage.getReceiver());
                    String error = "用户" + receivedMessage.getReceiver() + "不存在";
                    Message response = Message.of(SocketConstants.SERVER, from.getClientName(), error);
                    MessageHandler.write(from.getSocket().getOutputStream(), response);
                    continue;
                }
                Message sendingMessage = Message.of(from.getClientName(), to.getClientName(), receivedMessage.getContent());
                MessageHandler.write(to.getSocket().getOutputStream(), sendingMessage);
                logger.info("【{}】{} -> {}：【{}】", DateUtil.formatNow(), from.getClientName(), to.getClientName(), receivedMessage.getContent());
            }
        } catch (IOException e) {
            logger.error("{}：发生错误，已退出", from.getClientName());
        }
    }
}
