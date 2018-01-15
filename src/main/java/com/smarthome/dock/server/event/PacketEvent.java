 package com.smarthome.dock.server.event;
 
 import java.util.EventObject;
 
 public class PacketEvent extends EventObject
 {
   private static final long serialVersionUID = -8746662947115974374L;
   public int type;
 
   public PacketEvent(Object source)
   {
     super(source);
   }
 }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.event.PacketEvent
 * JD-Core Version:    0.6.2
 */