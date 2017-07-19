package com.zjlolife.zubbo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.msgpack.MsgPackDecoder;
import io.netty.handler.codec.msgpack.MsgPackEncoder;

import java.net.InetSocketAddress;

public class NettyServer {

    private int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public void open() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup(); //3
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)                                //4
                    .channel(NioServerSocketChannel.class)        //5
                    .localAddress(new InetSocketAddress(port))    //6
                    .childHandler(new ChannelInitializer<SocketChannel>() { //7
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(
                                    "frameDecoder",
                                    new LengthFieldBasedFrameDecoder(65535, 0, 2, 0 ,2)
                            );
                            ch.pipeline().addLast("msgDecoder", new MsgPackDecoder());
                            ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
                            ch.pipeline().addLast("msgEncoder", new MsgPackEncoder());
                        }
                    });

            ChannelFuture f = b.bind().sync();            //8
            f.channel().closeFuture().sync();            //9
        }
        finally {
            group.shutdownGracefully().sync();            //10
        }
    }
}
