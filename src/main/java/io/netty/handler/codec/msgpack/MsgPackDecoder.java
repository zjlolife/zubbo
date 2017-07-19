package io.netty.handler.codec.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

public class MsgPackDecoder extends MessageToMessageDecoder<ByteBuf> {

    int IP_PORT_LENGTH = 4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        final byte [] array;
        final int length = msg.readableBytes();
        byte [] ipPort = new byte[IP_PORT_LENGTH];
        msg.readBytes(ipPort);
        array = new byte[length - IP_PORT_LENGTH];
        msg.getBytes(msg.readerIndex(), array, 0, length);
        MessagePack messagePack = new MessagePack();
        out.add(ipPort);
        out.add(messagePack.read(array));
    }
}
