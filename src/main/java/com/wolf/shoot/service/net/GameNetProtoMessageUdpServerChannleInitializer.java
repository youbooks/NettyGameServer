package com.wolf.shoot.service.net;

import com.wolf.shoot.net.message.decoder.NetProtoBufMessageUDPDecoder;
import com.wolf.shoot.net.message.encoder.NetProtoBufMessageUDPEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * Created by jwp on 2017/2/17.
 */
public class GameNetProtoMessageUdpServerChannleInitializer  extends ChannelInitializer<NioDatagramChannel> {

    @Override
    protected void initChannel(NioDatagramChannel ch) throws Exception {
        ChannelPipeline channelPipLine = ch.pipeline();
        int maxLength = Integer.MAX_VALUE;
        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(maxLength, 2, 4, 0, 0));
        ch.pipeline().addLast(new NetProtoBufMessageUDPEncoder());
        ch.pipeline().addLast(new NetProtoBufMessageUDPDecoder());
        channelPipLine.addLast(new GameNetMessageUdpServerHandler());
    }
}