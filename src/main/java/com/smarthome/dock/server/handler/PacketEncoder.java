package com.smarthome.dock.server.handler;

import com.smarthome.dock.server.packets.OutPacket;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

public class PacketEncoder extends OneToOneEncoder
{
  protected Object encode(ChannelHandlerContext ctx, Channel channel, Object obj)
    throws Exception
  {
    ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();

    OutPacket packet = (OutPacket)obj;
    packet.fill(buffer);

    return buffer;
  }
}