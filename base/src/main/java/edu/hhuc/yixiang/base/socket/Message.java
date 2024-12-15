package edu.hhuc.yixiang.base.socket;

import lombok.Data;

import java.util.Date;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/12/11 21:44:49
 */
@Data
public class Message {
    private String sender;
    private String receiver;
    private String content;
    private Date timeStamp;

    public static Message of(String sender, String receiver, String content) {
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setTimeStamp(new Date());
        return message;
    }
}
