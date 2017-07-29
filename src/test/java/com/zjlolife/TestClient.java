package com.zjlolife;

import com.zjlolife.zubbo.client.NettyClient;

public class TestClient {

    public static void main(String [] args) throws Exception {
        String remoteHost = "127.0.0.1";
        int port = 9999;
        NettyClient nettyClient = new NettyClient(remoteHost, port);
        nettyClient.open();
    }
}
