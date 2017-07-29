package com.zjlolife.zubbo.client;

import com.zjlolife.zubbo.model.TestData;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.List;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        TestData testData = new TestData();
        testData.setId(1L);
        testData.setUserName(ctx.channel().localAddress().toString());
        testData.setEmail("www1.zjlolife.com");
        ctx.writeAndFlush(testData);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,
                            Object msg) {
        //服务refer invoker -> channel.write() -> 创建future等待服务提供者返回 -> 唤醒正在等待的线程
        try {
            List<TestData> testData = (List<TestData>) msg;
            System.out.println(testData.toString());
        }
        finally {
            ReferenceCountUtil.release(msg);
        }
    }


}
