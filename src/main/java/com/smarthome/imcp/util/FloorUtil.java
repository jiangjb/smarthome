/*    */ package com.smarthome.imcp.util;
		 import com.smarthome.imcp.dao.model.bo.BoFloor;
/*    */ 
/*    */ public class FloorUtil
/*    */ {
/*    */   public static BoFloor save(String userCode)
/*    */   {
/*  8 */     BoFloor floor = new BoFloor();
			 floor.setUserCode(userCode);
			 floor.setHouseCode(UuidUtil.get32UUID());
			 floor.setFloorCode(UuidUtil.get32UUID());
			 floor.setFloorName("楼层1");    
			 return floor;
/*    */   }
/*    */ }

/* Location:           C:\floors\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.floorUtil
 * JD-Core Version:    0.6.2
 */