/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoFingerprintMembersDaoIface;
/*     */ import com.smarthome.imcp.dao.model.bo.BoFingerprintMembers;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoFingerprintMembersServiceIface;
/*     */ import com.smarthome.imcp.util.UuidUtil;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boFingerprintMembersService")
/*     */ public class BoFingerprintMembersServiceImpl extends AbstractBasicService<BoFingerprintMembers, Serializable>
/*     */   implements BoFingerprintMembersServiceIface<BoFingerprintMembers, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoFingerprintMembersDaoIface<BoFingerprintMembers, Serializable> boFingerprintMembersManageDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoFingerprintMembers model)
/*     */   {
/*  48 */     return true;
/*     */   }
/*     */ 
/*     */   public BoFingerprintMembers save(BoFingerprintMembers model)
/*     */   {
/*  55 */     if (chkSaveValid(model)) {
/*  56 */       model.setFingerprintMembersId(UuidUtil.get32UUID());
/*  57 */       this.boFingerprintMembersManageDao.save(model);
/*     */     }
/*  59 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoFingerprintMembers model)
/*     */   {
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */   public BoFingerprintMembers update(BoFingerprintMembers model)
/*     */   {
/*  70 */     if (chkUpdateValid(model)) {
/*  71 */       this.boFingerprintMembersManageDao.update(model);
/*     */     }
/*  73 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoFingerprintMembers model)
/*     */   {
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */   public BoFingerprintMembers delete(BoFingerprintMembers model)
/*     */   {
/*  85 */     if (chkUpdateValid(model)) {
/*  86 */       this.boFingerprintMembersManageDao.delete(model);
/*     */     }
/*  88 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  93 */     if ((id == null) || ("".equals(id)))
/*  94 */       return;
/*  95 */     if (chkDeleteValid(id))
/*  96 */       this.boFingerprintMembersManageDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 102 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 103 */     while (st.hasMoreElements()) {
/* 104 */       String id = st.nextToken();
/* 105 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<BoFingerprintMembers> get(String userCode, String lockAddress)
/*     */   {
/* 112 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoFingerprintMembers.class);
/* 113 */     criteria.add(Restrictions.eq("userCode", userCode));
/* 114 */     criteria.add(Restrictions.eq("lockAddress", lockAddress));
/* 115 */     criteria.addOrder(Order.asc("fingerprintSubscript"));
/* 116 */     return this.boFingerprintMembersManageDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoFingerprintMembers findFingerprint(String lockAddress, String fingerprintSubscript)
/*     */   {
/* 122 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoFingerprintMembers.class);
/* 123 */     criteria.add(Restrictions.eq("lockAddress", lockAddress));
/* 124 */     criteria.add(Restrictions.eq("fingerprintSubscript", fingerprintSubscript));
/* 125 */     List list = this.boFingerprintMembersManageDao.findByCriteria(criteria);
/* 126 */     if ((list == null) || (list.isEmpty())) {
/* 127 */       return null;
/*     */     }
/* 129 */     return (BoFingerprintMembers)list.get(0);
/*     */   }
/*     */ 
/*     */   public BoFingerprintMembers findFingerprint(String fingerprintMembersId)
/*     */   {
/* 135 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoFingerprintMembers.class);
/*     */ 
/* 137 */     criteria.add(Restrictions.eq("fingerprintMembersId", fingerprintMembersId));
/* 138 */     List list = this.boFingerprintMembersManageDao.findByCriteria(criteria);
/* 139 */     if ((list == null) || (list.isEmpty())) {
/* 140 */       return null;
/*     */     }
/* 142 */     return (BoFingerprintMembers)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoFingerprintMembers> get(String lockAddress)
/*     */   {
/* 148 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoFingerprintMembers.class);
/* 149 */     criteria.add(Restrictions.eq("lockAddress", lockAddress));
/* 150 */     criteria.addOrder(Order.asc("fingerprintSubscript"));
/* 151 */     return this.boFingerprintMembersManageDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List gets(String lockAddress)
/*     */   {
/* 157 */     StringBuffer sql = new StringBuffer();
/* 158 */     sql.append("SELECT ");
/* 159 */     sql.append("FINGERPRINT_MEMBERS_ID,");
/* 160 */     sql.append("LOCK_ADDRESS,");
/* 161 */     sql.append("FINGERPRINT_SUBSCRIPT,");
/* 162 */     sql.append("MEMBERS_NAME,");
/* 163 */     sql.append("MEMBERS_HEADPIC");
/* 164 */     sql.append("FROM ");
/* 165 */     sql.append("bo_fingerprint_members ");
/* 166 */     sql.append("WHERE ");
/* 167 */     sql.append("LOCK_ADDRESS = '" + lockAddress + "'");
/* 168 */     sql.append(" ORDER BY");
/* 169 */     sql.append(" FINGERPRINT_SUBSCRIPT asc");
/* 170 */     System.err.println(sql.toString());
/* 171 */     List list = this.boFingerprintMembersManageDao.findByNSQL(sql.toString());
/* 172 */     return list;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoFingerprintMembersServiceImpl
 * JD-Core Version:    0.6.2
 */