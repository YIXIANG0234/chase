package edu.hhuc.yixiang.base.socket.demo;

import edu.hhuc.yixiang.base.socket.Client;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/12/11 22:19:27
 */
public class Zhang {
    public static void main(String[] args) {
        Client client = new Client("Zhang");
        client.connect();
    }
}
