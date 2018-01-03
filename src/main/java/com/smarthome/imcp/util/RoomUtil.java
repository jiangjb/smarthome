/*    */ package com.smarthome.imcp.util;
		 import com.smarthome.imcp.dao.model.bo.BoRoom;

/*    */ public class RoomUtil
/*    */ {
/*    */   public static BoRoom save(String userCode,String floorName,String floorCode,String roomName)
/*    */   {
/*  8 */     BoRoom room = new BoRoom();
/*  9 */     room.setUserCode(userCode);
/* 10 */     room.setFloorName(floorName);
/* 11 */     room.setFloorCode(floorCode);
/* 12 */     room.setRoomCode(UuidUtil.get32UUID());
/* 13 */     room.setRoomName(roomName);
/* 38 */     return room;
/*    */   }
/*    */ }

/* Location:           C:\rooms\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.roomUtil
 * JD-Core Version:    0.6.2
 */