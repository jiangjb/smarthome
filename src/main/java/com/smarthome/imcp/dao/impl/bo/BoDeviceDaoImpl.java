/*     */ package com.smarthome.imcp.dao.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.GlobalMethod;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*     */ import com.smarthome.imcp.dao.bo.BoDeviceDaoIface;
/*     */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaDevice;
/*     */ import com.smarthome.imcp.dao.model.bo.BoDevice;
import com.smarthome.imcp.dao.model.bo.BoHostDevice;
import com.smarthome.imcp.dao.model.bo.BoUsers;

/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.MatchMode;
/*     */ import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boDeviceDao")
/*     */ public class BoDeviceDaoImpl extends CommonsDaoImpl<BoDevice, Serializable>
/*     */   implements BoDeviceDaoIface<BoDevice, Serializable>
/*     */ {
/*     */   public BoDeviceDaoImpl()
/*     */   {
/*  26 */     super(BoDevice.class);
/*     */   }
/*     */ 
/*     */   public List<BoDevice> getAllList() {
/*  30 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoDevice.class);
/*  31 */     return findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoDevice> getList(SearchCriteriaDevice searchCriteriadevice)
/*     */   {
/*  37 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoDevice.class);
/*  38 */     if (!GlobalMethod.isNullorEmpty(searchCriteriadevice))
/*     */     {
/*  40 */       if ((searchCriteriadevice.getFactoryId() != null) && (searchCriteriadevice.getFactoryId().intValue() > 0)) {
/*  41 */         criteria.add(Restrictions.eq("boFactory.factoryId", searchCriteriadevice.getFactoryId()));
/*     */       }
/*     */ 
/*  44 */       if (StringUtils.isNotEmpty(searchCriteriadevice.getDeviceName())) {
/*  45 */         criteria.add(Restrictions.like("deviceName", searchCriteriadevice.getDeviceName(), MatchMode.ANYWHERE));
/*     */       }
/*     */ 
/*  48 */       if (StringUtils.isNotEmpty(searchCriteriadevice.getDeviceType())) {
/*  49 */         criteria.add(Restrictions.eq("deviceType", searchCriteriadevice.getDeviceType()));
/*     */       }
/*     */ 
/*  52 */       if (StringUtils.isNotEmpty(searchCriteriadevice.getDeviceCode())) {
/*  53 */         criteria.add(Restrictions.like("deviceCode", searchCriteriadevice.getDeviceCode(), MatchMode.END));
/*     */       }
/*     */     }
/*     */ 
/*  57 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/*     */ 
/*  59 */     criteria.addOrder(Order.asc("mntPosition")).addOrder(Order.asc("deviceId"));
/*  60 */     return findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoDevice> getList(SearchCriteriaDevice searchCriteriadevice, Page page)
/*     */   {
/*  66 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoDevice.class);
/*  67 */     if (!GlobalMethod.isNullorEmpty(searchCriteriadevice)) {
/*  68 */       if ((searchCriteriadevice.getFactoryId() != null) && (searchCriteriadevice.getFactoryId().intValue() > 0)) {
/*  69 */         criteria.add(Restrictions.eq("boFactory.factoryId", searchCriteriadevice.getFactoryId()));
/*     */       }
/*     */ 
/*  72 */       if (StringUtils.isNotEmpty(searchCriteriadevice.getDeviceName())) {
/*  73 */         criteria.add(Restrictions.like("deviceName", searchCriteriadevice.getDeviceName(), MatchMode.ANYWHERE));
/*     */       }
/*     */ 
/*  76 */       if (StringUtils.isNotEmpty(searchCriteriadevice.getDeviceType())) {
/*  77 */         criteria.add(Restrictions.eq("deviceType", searchCriteriadevice.getDeviceType()));
/*     */       }
/*     */ 
/*  80 */       if (StringUtils.isNotEmpty(searchCriteriadevice.getDeviceCode())) {
/*  81 */         criteria.add(Restrictions.like("deviceCode", searchCriteriadevice.getDeviceCode(), MatchMode.END));
/*     */       }
/*     */     }
/*  84 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/*  85 */     if (StringUtils.isEmpty(page.getOrderField())) {
/*  86 */       if (page.isAsc())
/*  87 */         criteria.addOrder(Order.asc("mntPosition")).addOrder(Order.asc("deviceId"));
/*     */       else {
/*  89 */         criteria.addOrder(Order.desc("mntPosition")).addOrder(Order.desc("deviceId"));
/*     */       }
/*     */     }
/*  92 */     else if (page.isAsc())
/*  93 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*     */     else {
/*  95 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*     */     }
/*     */ 
/*  98 */     return findByCriteria(criteria, page);
/*     */   }
/*     */ 
/*     */   public List<BoDevice> getListOnline(SearchCriteriaDevice searchCriteriadevice, Page page)
/*     */   {
/* 104 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoDevice.class);
/* 105 */     if (!GlobalMethod.isNullorEmpty(searchCriteriadevice)) {
/* 106 */       if ((searchCriteriadevice.getFactoryId() != null) && (searchCriteriadevice.getFactoryId().intValue() > 0)) {
/* 107 */         criteria.add(Restrictions.eq("boFactory.factoryId", searchCriteriadevice.getFactoryId()));
/*     */       }
/*     */ 
/* 110 */       if (StringUtils.isNotEmpty(searchCriteriadevice.getDeviceName())) {
/* 111 */         criteria.add(Restrictions.like("deviceName", searchCriteriadevice.getDeviceName(), MatchMode.ANYWHERE));
/*     */       }
/*     */ 
/* 114 */       if (StringUtils.isNotEmpty(searchCriteriadevice.getDeviceType())) {
/* 115 */         criteria.add(Restrictions.eq("deviceType", searchCriteriadevice.getDeviceType()));
/*     */       }
/*     */ 
/* 118 */       if (StringUtils.isNotEmpty(searchCriteriadevice.getDeviceCode())) {
/* 119 */         criteria.add(Restrictions.like("deviceCode", searchCriteriadevice.getDeviceCode(), MatchMode.END));
/*     */       }
/*     */     }
/* 122 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 123 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 124 */       if (page.isAsc())
/* 125 */         criteria.addOrder(Order.desc("status")).addOrder(Order.asc("deviceId"));
/*     */       else {
/* 127 */         criteria.addOrder(Order.desc("status")).addOrder(Order.desc("deviceId"));
/*     */       }
/*     */     }
/* 130 */     else if (page.isAsc())
/* 131 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*     */     else {
/* 133 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*     */     }
/*     */ 
/* 136 */     return findByCriteria(criteria, page);
/*     */   }
/*     */
//			@Override
//			public BoDevice findByDCode(String deviceCode) {
//				StringBuffer hql = new StringBuffer();
//				Object[] values = { deviceCode};
//				hql.append("select bd.deviceCode,bd.status,bd.type from BoDevice bd");//可能有问题
//				List<BoDevice> boDevice=findByHQL(hql.toString(),values);
//				if(boDevice ==null) {
//					return null;
//				}
//				return boDevice.get(0);
//			}
			@Override
			public List<BoDevice> getAllHostDevices() {
//				StringBuffer hql = new StringBuffer();
//				hql.append("select bd.deviceCode,bd.status,bd.type from BoDevice bd");//可能有问题
//				List<BoDevice> list=findByHQL(hql.toString());
//				if(list ==null) {
//					return null;
//				}
//				return list;
				DetachedCriteria criteria = DetachedCriteria.forClass(BoDevice.class);
				return findByCriteria(criteria);
			}
			@Override
			public String addHostDevice(String deviceCode, String type) {
				String back="fail";
				Object[] values = { deviceCode, type };
				StringBuffer hql = new StringBuffer();
				hql.append("insert into BoDevice bd ");
				hql.append(" set bd.deviceCode = ?");
				hql.append(" and bd.type = ?");
				int result=bulkUpdate(hql.toString(), values);
				if(result !=-1) {
					back="success";
				}
				return back;
			}
//			@Override
//			public List<BoDevice> findByStatus(int status) {
////				StringBuffer hql = new StringBuffer();
////				Object[] values = { status };
////				hql.append("select bd.deviceCode,bd.status,bd.type from BoDevice bd");//可能有问题
////				hql.append(" where bd.status=?");
////				List<BoDevice> list=findByHQL(hql.toString(), values);
////				if(list ==null) {
////					return null;
////				}
////				return list;
//				DetachedCriteria criteria = DetachedCriteria.forClass(BoDevice.class);
//				criteria.add(Restrictions.eq("status", status));
//				List list = findByCriteria(status);
//				if ((list == null) || (list.isEmpty())) {
//					return null;
//				}
//				return (BoDevice)list.get(0);
//			}
			/*@Override
			public Long getSum() {
				Long totalCount = (Long) DetachedCriteria.forClass(BoDevice.class).setProjection(Projections.rowCount()).getExecutableCriteria(this.getHibernateTemplate().getSessionFactory().getCurrentSession()).uniqueResult();
				return totalCount;
			}*/
		}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoDeviceDaoImpl
 * JD-Core Version:    0.6.2
 */