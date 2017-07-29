package com.zjlolife.zubbo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.msgpack.MsgPackDecoder;
import io.netty.handler.codec.msgpack.MsgPackEncoder;

import java.net.InetSocketAddress;

public class NettyClient {

    private final String host;

    private final int port;

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void open() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();				//1
            b.group(group)								//2
                    .channel(NioSocketChannel.class)			//3
                    .remoteAddress(new InetSocketAddress(host, port))	//4
                    .handler(new ChannelInitializer<SocketChannel>() {	//5
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
                            ch.pipeline().addLast("clientHandle", new ClientHandler());
                        }
                    });

            ChannelFuture f = b.connect().sync();		//6

            f.channel().closeFuture().sync();			//7
        } finally {
            group.shutdownGracefully().sync();			//8
        }
    }
}
