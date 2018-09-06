/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoRoomDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoRoom;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoRoomServiceIface;
/*     */ import com.smarthome.imcp.util.SendMsgUtil;
/*     */ import com.smarthome.imcp.util.UuidUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boRoomService")
/*     */ public class BoRoomServiceImpl extends AbstractBasicService<BoRoom, Serializable>
/*     */   implements BoRoomServiceIface<BoRoom, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoRoomDaoIface<BoRoom, Serializable> boRoomDao;
/*     */ 
/*     */   public List<BoRoom> get(Integer roomId)
/*     */   {
/*  32 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoRoom.class);
/*     */ 
/*  34 */     criteria.add(Restrictions.eq("roomId", roomId));
/*     */ 
/*  36 */     return this.boRoomDao.findByCriteria(criteria);
/*     */   }
/*     */ 
			public BoRoom findByKey(Serializable id){
				BoRoom model = (BoRoom)this.boRoomDao.findById(id);
				return model;
			}

/*     */   public BoRoom findByCode(String roomCode)
/*     */   {
/*  41 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoRoom.class);
/*  42 */     criteria.add(Restrictions.eq("roomCode", roomCode));
/*  43 */     List list = this.boRoomDao.findByCriteria(criteria);
/*  44 */     if ((list == null) || (list.isEmpty())) {
/*  45 */       return null;
/*     */     }
/*  47 */     return (BoRoom)list.get(0);
/*     */   }
/*     */ 
/*     */   public BoRoom findByRommCode(String roomCode)
/*     */   {
/*  52 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoRoom.class);
/*  53 */     criteria.add(Restrictions.eq("roomCode", roomCode));
/*  54 */     List list = this.boRoomDao.findByCriteria(criteria);
/*  55 */     if ((list == null) || (list.isEmpty())) {
/*  56 */       return null;
/*     */     }
/*  58 */     return (BoRoom)list.get(0);
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoRoom model)
/*     */   {
/*  64 */     return true;
/*     */   }
/*     */ 
/*     */   public BoRoom save(BoRoom model)
/*     */   {
/*  69 */     SendMsgUtil s = new SendMsgUtil();
/*     */ 
/*  71 */     if (chkSaveValid(model)) {
/*  72 */       model.setRoomCode(UuidUtil.get32UUID());
/*  73 */       this.boRoomDao.save(model);
/*     */     }
/*  75 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoRoom model)
/*     */   {
/*  81 */     return true;
/*     */   }
/*     */ 
/*     */   public BoRoom update(BoRoom model)
/*     */   {
/*  86 */     if (chkUpdateValid(model)) {
/*  87 */       this.boRoomDao.update(model);
/*     */     }
/*  89 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoRoom model)
/*     */   {
/*  95 */     return true;
/*     */   }
/*     */ 
/*     */   public BoRoom delete(BoRoom model)
/*     */   {
/* 100 */     if (chkUpdateValid(model)) {
/* 101 */       this.boRoomDao.delete(model);
/*     */     }
/* 103 */     return model;
/*     */   }
/*     */ 
/*     */   public List<BoRoom> getAllListByUserCode(String userCode)
/*     */   {
/* 109 */     return this.boRoomDao.getAllListByUserCode(userCode);
/*     */   }
/*     */ 
/*     */   public BoRoom findByRommName(String roomName)
/*     */   {
/* 115 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoRoom.class);
/* 116 */     criteria.add(Restrictions.eq("roomName", roomName));
/* 117 */     List list = this.boRoomDao.findByCriteria(criteria);
/* 118 */     if ((list == null) || (list.isEmpty())) {
/* 119 */       return null;
/*     */     }
/* 121 */     return (BoRoom)list.get(0);
/*     */   }
/*     */ 
/*     */   public BoRoom findByUserCode(String userCode)
/*     */   {
/* 127 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoRoom.class);
/* 128 */     criteria.add(Restrictions.eq("userCode", userCode));
/* 129 */     List list = this.boRoomDao.findByCriteria(criteria);
/* 130 */     if ((list == null) || (list.isEmpty())) {
/* 131 */       return null;
/*     */     }
/* 133 */     return (BoRoom)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoRoom> getUserCode(String userCode)
/*     */   {
/* 139 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoRoom.class);
/* 140 */     criteria.add(Restrictions.eq("userCode", userCode));
/*     */ 
/* 143 */     return this.boRoomDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoRoom> getAllListByRommCode(String rommCode)
/*     */   {
/* 149 */     return this.boRoomDao.getAllListByRommCode(rommCode);
/*     */   }
/*     */ 
/*     */   public List<BoRoom> getAllListByFloorCode(String floorCode)
/*     */   {
/* 155 */     return this.boRoomDao.getAllListByFloorCode(floorCode);
/*     */   }
/*     */ 
/*     */   public List<BoRoom> get(String roomCode)
/*     */   {
/* 161 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoRoom.class);
/* 162 */     criteria.add(Restrictions.eq("roomCode", roomCode));
/*     */ 
/* 165 */     return this.boRoomDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoRoom> getAllListByUserCode()
/*     */   {
/* 171 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoRoom.class);
/* 172 */     return this.boRoomDao.findByCriteria(criteria);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoRoomServiceImpl
 * JD-Core Version:    0.6.2
 */