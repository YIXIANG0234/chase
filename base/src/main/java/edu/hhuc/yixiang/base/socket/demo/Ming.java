package edu.hhuc.yixiang.base.socket.demo;

import edu.hhuc.yixiang.base.socket.Client;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/12/11 20:43:45
 */
public class Ming {
    public static void main(String[] args) {
        Client client = new Client("Ming");
        client.connect();
    }
}
