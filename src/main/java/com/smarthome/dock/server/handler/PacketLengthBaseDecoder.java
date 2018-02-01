package com.smarthome.dock.server.handler;

import com.smarthome.dock.server.packets.InPacket;
//import com.smarthome.dock.server.packets.PacketHelper;
import com.smarthome.dock.server.support.PacketProcessor;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;

public class PacketLengthBaseDecoder extends LengthFieldBasedFrameDecoder
{
  private PacketProcessor packetProcessor;

  public PacketLengthBaseDecoder(PacketProcessor packetProcessor, int maxFrameLength, int lengthFieldOffset, int lengthFieldLength)
  {
    super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    this.packetProcessor = packetProcessor;
  }

  protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buf)
    throws Exception
  {
    Object buffer = super.decode(ctx, channel, buf);
    if (buffer != null) {
      InPacket packet = this.packetProcessor.getPacketHelper().processIn((ChannelBuffer)buffer);

      return packet;
    }
    return null;
  }
}