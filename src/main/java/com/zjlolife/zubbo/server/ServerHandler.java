package com.zjlolife.zubbo.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Executor;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    private Executor executor;

    public ServerHandler(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,
                            Object msg) {
        //解析消息 ->  找到对应的export invoker -> 调用完，channel.write() -> 通知服务消费者
        //解析消息 ->  找到对应的export invoker -> 调用自己的逻辑 -> refer invoker -> 调用完，channel.write() -> 通知服务消费者
//        Object testData = (List<TestData>) msg;
//        System.out.println(testData.toString());
//        ctx.writeAndFlush(testData);
        //接受到消息提交给服务端线程去处理, 服务端线程获取请求id，保存请求id和channel的关系
        executor.execute(() -> {
            System.out.println(msg);
            //解析，获取对应的代理bean,去调用,调用完还有个channel.write()
            //解析msg，调用对应的service.method()
        });
    }


}
