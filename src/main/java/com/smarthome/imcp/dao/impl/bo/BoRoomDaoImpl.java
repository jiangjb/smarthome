/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoRoomDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoRoom;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boRoomDao")
/*    */ public class BoRoomDaoImpl extends CommonsDaoImpl<BoRoom, Serializable>
/*    */   implements BoRoomDaoIface<BoRoom, Serializable>
/*    */ {
/*    */   public BoRoomDaoImpl()
/*    */   {
/* 22 */     super(BoRoom.class);
/*    */   }
/*    */ 
/*    */   public List<BoRoom> getAllListByUserCode(String userCode)
/*    */   {
/* 27 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoRoom.class);
/* 28 */     criteria.add(Restrictions.eq("userCode", userCode));
/* 29 */     return findByCriteria(criteria);
/*    */   }
/*    */ 
/*    */   public List<BoRoom> getAllListByRommCode(String rommCode)
/*    */   {
/* 35 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoRoom.class);
/* 36 */     criteria.add(Restrictions.eq("roomCode", rommCode));
/* 37 */     return findByCriteria(criteria);
/*    */   }
/*    */ 
/*    */   public List<BoRoom> getAllListByFloorCode(String floorCode)
/*    */   {
/* 43 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoRoom.class);
/* 44 */     criteria.add(Restrictions.eq("floorCode", floorCode));
/* 45 */     return findByCriteria(criteria);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoRoomDaoImpl
 * JD-Core Version:    0.6.2
 */