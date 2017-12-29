/*    */ package com.smarthome.imcp.service.impl.system;
/*    */ 
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoIface;
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ import com.smarthome.imcp.dao.model.system.SysUpdateInfo;
/*    */ import com.smarthome.imcp.dao.system.SysUpdateInfoServiceIface;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.StringTokenizer;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ 
/*    */ public class SysUpdateInfoServiceImpl extends AbstractBasicService<SysUpdateInfo, Serializable>
/*    */   implements SysUpdateInfoServiceIface<SysUpdateInfo, Serializable>
/*    */ {
/*    */   private CommonsDaoIface<SysUpdateInfo, Serializable> sysUpdateInfoDao;
/*    */ 
/*    */   public List<SysUpdateInfo> getList(SearchCriteria searchCriteria, Page page)
/*    */   {
/* 25 */     DetachedCriteria criteria = 
/* 26 */       DetachedCriteria.forClass(SysUpdateInfo.class);
/* 27 */     criteria.addOrder(Order.asc("sysUpdateInfoId"));
/* 28 */     return this.sysUpdateInfoDao.findByCriteria(criteria, page);
/*    */   }
/*    */ 
/*    */   public SysUpdateInfo save(SysUpdateInfo sysUpdateInfo)
/*    */   {
/* 34 */     if (chkSaveValid(sysUpdateInfo)) {
/* 35 */       this.sysUpdateInfoDao.save(sysUpdateInfo);
/*    */     }
/* 37 */     return sysUpdateInfo;
/*    */   }
/*    */ 
/*    */   public SysUpdateInfo findByKey(Serializable id)
/*    */   {
/* 42 */     return (SysUpdateInfo)this.sysUpdateInfoDao.findById(id);
/*    */   }
/*    */ 
/*    */   public SysUpdateInfo update(SysUpdateInfo sysUpdateInfo)
/*    */   {
/* 48 */     if (chkUpdateValid(sysUpdateInfo)) {
/* 49 */       this.sysUpdateInfoDao.update(sysUpdateInfo);
/*    */     }
/* 51 */     return sysUpdateInfo;
/*    */   }
/*    */ 
/*    */   public void deleteByKey(String sysUpdateInfoId)
/*    */   {
/* 56 */     if (chkDeleteValid(sysUpdateInfoId))
/* 57 */       this.sysUpdateInfoDao.deleteByKey(Long.valueOf(Long.parseLong(sysUpdateInfoId)));
/*    */   }
/*    */ 
/*    */   public void deleteByKeys(String sysUpdateInfoId)
/*    */   {
/* 63 */     StringTokenizer st = new StringTokenizer(sysUpdateInfoId, ",");
/* 64 */     while (st.hasMoreElements()) {
/* 65 */       String sysId = st.nextToken();
/* 66 */       deleteByKey(sysId);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void setSysUpdateInfoDao(CommonsDaoIface<SysUpdateInfo, Serializable> sysUpdateInfoDao)
/*    */   {
/* 73 */     this.sysUpdateInfoDao = sysUpdateInfoDao;
/*    */   }
/*    */ 
/*    */   public List<SysUpdateInfo> getTheFirstThreeList()
/*    */   {
/* 78 */     StringBuffer sb = new StringBuffer();
/* 79 */     sb.append("from SysUpdateInfo sysUpdateInfo");
/* 80 */     sb.append(" where rownum < 3 ");
/* 81 */     sb.append(" order by sysUpdateInfo.sysUpdateInfoId desc");
/* 82 */     return this.sysUpdateInfoDao.findByHQL(sb.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.system.SysUpdateInfoServiceImpl
 * JD-Core Version:    0.6.2
 */