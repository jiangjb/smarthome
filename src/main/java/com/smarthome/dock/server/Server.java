package com.smarthome.dock.server;

import java.net.InetSocketAddress;

public abstract interface Server
{
  public abstract void startServer()
    throws Exception;

  public abstract void startServer(int paramInt)
    throws Exception;

  public abstract void startServer(InetSocketAddress paramInetSocketAddress)
    throws Exception;

  public abstract void stopServer()
    throws Exception;

  public abstract InetSocketAddress getSocketAddress();
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.Server
 * JD-Core Version:    0.6.2
 */