package com.zjlolife;

import com.zjlolife.zubbo.server.NettyServer;


public class TestServer {

    public static void main(String [] args) throws InterruptedException {
        NettyServer nettyServer = new NettyServer(9999);
        try {
            nettyServer.open();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
