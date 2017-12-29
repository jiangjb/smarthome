package com.smarthome.dock.server.packets;

import org.jboss.netty.buffer.ChannelBuffer;

public abstract interface IParser
{
  public abstract boolean accept(ChannelBuffer paramChannelBuffer);

  public abstract InPacket parseIncoming(ChannelBuffer paramChannelBuffer)
    throws PacketParseException;

  public abstract OutPacket parseOutcoming(ChannelBuffer paramChannelBuffer)
    throws PacketParseException;

  public abstract OutPacket parseOutcoming(ChannelBuffer paramChannelBuffer, int paramInt1, int paramInt2)
    throws PacketParseException;

  public abstract InPacket parseIncoming(ChannelBuffer paramChannelBuffer, int paramInt1, int paramInt2)
    throws PacketParseException;

  public abstract boolean isIncoming(ChannelBuffer paramChannelBuffer);

  public abstract boolean isDuplicated(ChannelBuffer paramChannelBuffer);

  public abstract boolean isDuplicatedNeedReply();
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.IParser
 * JD-Core Version:    0.6.2
 */