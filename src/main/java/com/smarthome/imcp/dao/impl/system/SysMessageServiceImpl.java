/*    */ package com.smarthome.imcp.dao.impl.system;
/*    */ 
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoIface;
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ import com.smarthome.imcp.dao.model.system.SysMessage;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
/*    */ import com.smarthome.imcp.service.system.SysMessageServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.StringTokenizer;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class SysMessageServiceImpl extends AbstractBasicService<SysMessage, Serializable>
/*    */   implements SysMessageServiceIface<SysMessage, Serializable>
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CommonsDaoIface<SysMessage, Serializable> sysMessageDao;
/*    */ 
/*    */   public List<SysMessage> getList(SearchCriteria searchCriteria, Page page)
/*    */   {
/* 26 */     DetachedCriteria criteria = DetachedCriteria.forClass(SysMessage.class);
/* 27 */     criteria.addOrder(Order.asc("messageId"));
/* 28 */     return this.sysMessageDao.findByCriteria(criteria, page);
/*    */   }
/*    */ 
/*    */   public List<SysMessage> getFirstList()
/*    */   {
/* 33 */     StringBuffer sb = new StringBuffer();
/* 34 */     sb.append("from SysMessage sysMessage");
/* 35 */     sb.append(" where rownum = 1 ");
/* 36 */     sb.append(" order by sysMessage.messageId desc");
/* 37 */     return this.sysMessageDao.findByHQL(sb.toString());
/*    */   }
/*    */ 
/*    */   public SysMessage save(SysMessage sysMessage)
/*    */   {
/* 42 */     if (chkSaveValid(sysMessage)) {
/* 43 */       this.sysMessageDao.save(sysMessage);
/*    */     }
/* 45 */     return sysMessage;
/*    */   }
/*    */ 
/*    */   public SysMessage findByKey(Serializable id)
/*    */   {
/* 50 */     return (SysMessage)this.sysMessageDao.findById(id);
/*    */   }
/*    */ 
/*    */   public SysMessage update(SysMessage sysMessage)
/*    */   {
/* 55 */     if (chkUpdateValid(sysMessage)) {
/* 56 */       this.sysMessageDao.update(sysMessage);
/*    */     }
/* 58 */     return sysMessage;
/*    */   }
/*    */ 
/*    */   public void deleteByKey(String sysmessid)
/*    */   {
/* 63 */     if (chkDeleteValid(sysmessid))
/* 64 */       this.sysMessageDao.deleteByKey(Long.valueOf(Long.parseLong(sysmessid)));
/*    */   }
/*    */ 
/*    */   public void deleteByKeys(String sysmessid)
/*    */   {
/* 70 */     StringTokenizer st = new StringTokenizer(sysmessid, ",");
/* 71 */     while (st.hasMoreElements()) {
/* 72 */       String sysId = st.nextToken();
/* 73 */       deleteByKey(sysId);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.system.SysMessageServiceImpl
 * JD-Core Version:    0.6.2
 */