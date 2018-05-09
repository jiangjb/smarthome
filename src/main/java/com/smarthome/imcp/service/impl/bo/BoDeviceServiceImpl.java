/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.ContextUtil;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoDeviceDaoIface;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaDevice;
/*     */ import com.smarthome.imcp.dao.model.bo.BoDevice;
import com.smarthome.imcp.dao.model.bo.BoUsers;
/*     */ import com.smarthome.imcp.exception.BusinessException;
/*     */ import com.smarthome.imcp.secur.CurrentUser;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoDeviceServiceIface;

import java.io.FileNotFoundException;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boDeviceService")
/*     */ public class BoDeviceServiceImpl extends AbstractBasicService<BoDevice, Serializable>
/*     */   implements BoDeviceServiceIface<BoDevice, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoDeviceDaoIface<BoDevice, Serializable> boDeviceDao;
/*     */ 
/*     */   public void updateIP(Integer deivceId, int status, String ip, String[] detail)
/*     */   {
/*  38 */     Object[] values = { ip, Integer.valueOf(status), detail[0], detail[1], detail[2], detail[3], detail[4], deivceId };
/*  39 */     StringBuffer hql = new StringBuffer();
/*  40 */     hql.append("update BoDevice d");
/*  41 */     hql.append(" set d.devIp = ?,");
/*  42 */     hql.append(" d.status = ?,");
/*  43 */     hql.append(" d.latitude = ?,");
/*  44 */     hql.append(" d.longitude = ?,");
/*  45 */     hql.append(" d.province = ?,");
/*  46 */     hql.append(" d.city = ?,");
/*  47 */     hql.append(" d.region = ?");
/*  48 */     hql.append(" where d.deviceId = ?");
/*  49 */     hql.append(" and d.mntDelete = 'N'");
/*     */ 
/*  51 */     this.boDeviceDao.bulkUpdate(hql.toString(), values);
/*     */   }
/*     */ 
/*     */   public void updateAllStatus(int status) {//改：把hostStatus加了一个判断
//	          System.out.println("updateAllStatus...");
			  String hostStatus="离线";
		      if(status != 0) {
		  	      hostStatus="在线";
		      }
/*  55 */     Object[] values = { Integer.valueOf(status), hostStatus };
/*  56 */     StringBuffer hql = new StringBuffer();
/*  57 */     hql.append("update BoDevice ");
/*  58 */     hql.append(" set status = ?");
/*  59 */     hql.append(",HOST_STATUS = ?");
/*  60 */     hql.append(" where mntDelete = 'N'");
/*  61 */     this.boDeviceDao.bulkUpdate(hql.toString(), values);
/*     */   }
/*     */ 
/*     */   public void updateStatus(String deivceCode, int status) {//改：把hostStatus加了一个判断
//			  System.out.println("updateStatus...");
			  String hostStatus="离线";
	          if(status != 0) {
	        	  hostStatus="在线";
	          }
/*  65 */     Object[] values = { Integer.valueOf(status), hostStatus, deivceCode };
/*  66 */     StringBuffer hql = new StringBuffer();
/*  67 */     hql.append("update BoDevice ");
/*  68 */     hql.append(" set status = ?");
/*  69 */     hql.append(",HOST_STATUS = ?");
/*  70 */     hql.append(" where deviceCode = ?");
/*  71 */     hql.append(" and mntDelete = 'N'");
/*  72 */     this.boDeviceDao.bulkUpdate(hql.toString(), values);
/*     */   }
/*     */ 
/*     */   public void updateWater(String deivceCode, int water) {
/*  76 */     Object[] values = { Integer.valueOf(water), deivceCode };
/*  77 */     StringBuffer hql = new StringBuffer();
/*  78 */     hql.append("update BoDevice d");
/*  79 */     hql.append(" set d.water = ?");
/*  80 */     hql.append(" where d.deviceCode = ?");
/*  81 */     hql.append(" and d.mntDelete = 'N'");
/*  82 */     this.boDeviceDao.bulkUpdate(hql.toString(), values);
/*     */   }
/*     */ 
/*     */   public BoDevice findByCode(String deviceCode) {
/*  86 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoDevice.class);
/*     */ 
/*  88 */     criteria.add(Restrictions.eq("deviceCode", deviceCode));
/*  89 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/*     */ 
/*  91 */     List list = this.boDeviceDao.findByCriteria(criteria);
/*  92 */     if ((list == null) || (list.isEmpty())) {
/*  93 */       return null;
/*     */     }
/*  95 */     return (BoDevice)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoDevice> getAllList() {
/*  99 */     return getList(null);
/*     */   }
/*     */ 
/*     */   public List<BoDevice> getList(SearchCriteria searchCriteria)
/*     */   {
/* 104 */     CurrentUser currentUser = ContextUtil.getCurrentUser();
/* 105 */     String userType = currentUser.getUserType();
/*     */ 
/* 107 */     SearchCriteriaDevice searchCriteriaDevice = (SearchCriteriaDevice)searchCriteria;
/* 108 */     if (searchCriteriaDevice == null) searchCriteriaDevice = new SearchCriteriaDevice();
/*     */ 
/* 110 */     if (("IDSSUP".equals(userType)) || ("IDSNOR".equals(userType))) {
/* 111 */       searchCriteriaDevice.setFactoryId(currentUser.getFactoryId());
/*     */     }
/*     */ 
/* 114 */     if (chkCriteriaValid(searchCriteria)) {
/* 115 */       return this.boDeviceDao.getList(searchCriteriaDevice);
/*     */     }
/* 117 */     return null;
/*     */   }
/*     */ 
/*     */   public List<BoDevice> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/* 122 */     CurrentUser currentUser = ContextUtil.getCurrentUser();
/* 123 */     String userType = currentUser.getUserType();
/*     */ 
/* 125 */     SearchCriteriaDevice searchCriteriaDevice = (SearchCriteriaDevice)searchCriteria;
/* 126 */     if (searchCriteriaDevice == null) searchCriteriaDevice = new SearchCriteriaDevice();
/*     */ 
/* 133 */     if (chkCriteriaValid(searchCriteria)) {
/* 134 */       return this.boDeviceDao.getList(searchCriteriaDevice, page);
/*     */     }
/* 136 */     return null;
/*     */   }
/*     */ 
/*     */   public List<BoDevice> getListOnline(SearchCriteria searchCriteria, Page page)
/*     */   {
/* 141 */     CurrentUser currentUser = ContextUtil.getCurrentUser();
/* 142 */     String userType = currentUser.getUserType();
/*     */ 
/* 144 */     SearchCriteriaDevice searchCriteriaDevice = (SearchCriteriaDevice)searchCriteria;
/* 145 */     if (searchCriteriaDevice == null) searchCriteriaDevice = new SearchCriteriaDevice();
/*     */ 
/* 148 */     if (("IDSSUP".equals(userType)) || ("IDSNOR".equals(userType))) {
/* 149 */       searchCriteriaDevice.setFactoryId(currentUser.getFactoryId());
/*     */     }
/*     */ 
/* 152 */     if (chkCriteriaValid(searchCriteria)) {
/* 153 */       return this.boDeviceDao.getList(searchCriteriaDevice, page);
/*     */     }
/* 155 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoDevice model)
/*     */   {
/* 160 */     BoDevice d = findByCode(model.getDeviceCode());
/* 161 */     if (d != null) {
/* 162 */       throw new BusinessException("设备序列号已存在,不能重复添加!");
/*     */     }
/*     */ 
/* 165 */     return true;
/*     */   }
/*     */ 
/*     */   public BoDevice save(BoDevice model)
/*     */   {
/* 170 */     if (chkSaveValid(model)) {
/* 171 */       this.boDeviceDao.save(model);
/*     */     }
/* 173 */     return model;
/*     */   }
/*     */ 
/*     */   public BoDevice findByKey(Serializable id)
/*     */   {
/* 178 */     BoDevice model = (BoDevice)this.boDeviceDao.findById(id);
/* 179 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoDevice model)
/*     */   {
/* 184 */     return true;
/*     */   }
/*     */ 
/*     */   public BoDevice update(BoDevice model)
/*     */   {
//	 	      System.out.println("update...");
/* 189 */     if (chkUpdateValid(model)) {
/* 190 */       this.boDeviceDao.update(model);
/*     */     }
/* 192 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String id)
/*     */   {
/* 197 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/* 202 */     if ((id == null) || ("".equals(id)))
/* 203 */       return;
/* 204 */     if (chkDeleteValid(id))
/* 205 */       this.boDeviceDao.deleteLogicByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 211 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 212 */     while (st.hasMoreElements()) {
/* 213 */       String id = st.nextToken();
/* 214 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateStatus(int status)
/*     */   {//改：
			  System.out.println("updateStatus...");
			  String hostStatus="离线";
		      if(status != 0) {
		  	      hostStatus="在线";
		      }
/* 221 */     Object[] values = { Integer.valueOf(status), hostStatus };//这里是向数据库里插入的值
/* 222 */     StringBuffer hql = new StringBuffer();
/* 223 */     hql.append("update BoDevice ");
/* 224 */     hql.append(" set status = ?");
/* 225 */     hql.append(",HOST_STATUS = ?");
/* 226 */     hql.append(" where mntDelete = 'N'");
/* 227 */     this.boDeviceDao.bulkUpdate(hql.toString(), values);
/*     */   }
/*     */
			@Override
			public String addDevice(String deviceCode, String type) {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public List<BoDevice> findByDCode(String deviceCode) {
				DetachedCriteria criteria = DetachedCriteria.forClass(BoDevice.class);
				criteria.add(Restrictions.eq("deviceCode", deviceCode));
				List list = this.boDeviceDao.findByCriteria(criteria);
				if ((list == null) || (list.isEmpty())) {
					return null;
					}
				return list;
			}
			@Override
			public List<BoDevice> getAllHostDevices() {
				// TODO Auto-generated method stub
				return this.boDeviceDao.getAllHostDevices();
			}
			@Override
			public String addHostDevice(String deviceCode, String type) {
				return this.boDeviceDao.addHostDevice(deviceCode,type);
			}
			@Override
			public String saveExcel(int choiceTo, String filepath) throws Exception {
				return this.boDeviceDao.saveExcel(choiceTo, filepath);
			}
			@Override
			public List<BoDevice> findByStatus(int status) {
				// TODO Auto-generated method stub
				DetachedCriteria criteria = DetachedCriteria.forClass(BoDevice.class);
				criteria.add(Restrictions.eq("status", status));
				List<BoDevice> list = this.boDeviceDao.findByCriteria(criteria);
				if ((list == null) || (list.isEmpty())) {
					return null;
					}
				return list;
			}
		
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoDeviceServiceImpl
 * JD-Core Version:    0.6.2
 */