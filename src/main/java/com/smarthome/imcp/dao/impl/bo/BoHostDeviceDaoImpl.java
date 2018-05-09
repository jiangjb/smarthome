/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoHostDeviceDaoIface;
/*    */ import com.smarthome.imcp.dao.model.bo.BoHostDevice;
import com.smarthome.imcp.dao.model.bo.BoUsers;

/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boHostDeviceDao")
/*    */ public class BoHostDeviceDaoImpl extends CommonsDaoImpl<BoHostDevice, Serializable>
/*    */   implements BoHostDeviceDaoIface<BoHostDevice, Serializable>
/*    */ {
/*    */   public BoHostDeviceDaoImpl()
/*    */   {
/* 20 */     super(BoHostDevice.class);
/*    */   }
/*    */ 
/*    */   public List<BoHostDevice> getByuserCode(String userCode)
/*    */   {
/* 26 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/*    */ 
/* 28 */     criteria.createAlias("boUsers", "boUsers");
/* 29 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/*    */ 
/* 32 */     return findByCriteria(criteria);
/*    */   }
/*    */
		@Override
		public String addValidation(String deviceCode, String type) {
			String back="fail";
			Object[] values = { deviceCode, type };
			StringBuffer hql = new StringBuffer();
			hql.append("insert into BoHostDevice bd ");
			hql.append(" set bd.deviceCode = ?");
			hql.append(" and bd.type = ?");
			int result=bulkUpdate(hql.toString(), values);
			if(result !=-1) {
				back="success";
			}
			return back;
		}

		@Override
		public List<BoHostDevice> getAllHostD() {
			DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
			return findByCriteria(criteria);
		}
//		@Override
//		public List<BoHostDevice> getAllDevices() {
//			StringBuffer hql = new StringBuffer();
//			hql.append("select bd.deviceCode,bu.userPhone,bhd.deviceAddress from BoHostDevice bhd,BoDevice bd,BoUsers bu");//可能有问题
//			List<BoHostDevice> list=findByHQL(hql.toString());
//			if(list ==null) {
//				return null;
//			}
//			return list;
//		}
//		@Override
//		public List<BoHostDevice> getAllRed() {
//			StringBuffer hql = new StringBuffer();
//			hql.append("select bhd.id,bhd.deviceAddress,bhd.validationCode from BoHostDevice bhd");//可能有问题
//			List<BoHostDevice> list=findByHQL(hql.toString());
//			if(list ==null) {
//				return null;
//			}
//			return list;
//		}
		@Override
		public String addDeviceRed(String deviceAddress, String validationCode) {
			String back="fail";
			Object[] values = { deviceAddress, validationCode };
			StringBuffer hql = new StringBuffer();
			hql.append("insert into BoHostDevice bd ");
			hql.append(" set bd.deviceAddress = ?");
			hql.append(" and bd.validationCode = ?");
			int result=bulkUpdate(hql.toString(), values);
			if(result !=-1) {
				back="success";
			}
			return back;
		}
//		@Override
//		public BoHostDevice findHostByUserPhone(String userPhone) {
//			StringBuffer hql = new StringBuffer();
//			Object[] values = { userPhone };
//			hql.append("select bd.deviceCode,bu.userPhone,bhd.deviceAddress from BoHostDevice bhd,BoDevice bd,BoUsers bu");//可能有问题
//			hql.append(" where bd.userPhone = ?");
//			List<BoHostDevice> list=findByHQL(hql.toString(), values);
//			if(list ==null) {
//				return null;
//			}
//			return list.get(0);
//		}
//		@Override
//		public List<BoHostDevice> findHostByDevicecode(String deviceCode) {
//			StringBuffer hql = new StringBuffer();
//			Object[] values = { deviceCode };
//			hql.append("select bd.deviceCode,bu.userPhone,bhd.deviceAddress from BoHostDevice bhd,BoDevice bd,BoUsers bu");//可能有问题
//			hql.append(" where bd.deviceCode = ?");
//			List<BoHostDevice> list=findByHQL(hql.toString(), values);
//			if(list ==null) {
//				return null;
//			}
//			return list;
//		}
//		@Override
//		public List<BoHostDevice> findRedByAddr(String deviceAddress) {
//			StringBuffer hql = new StringBuffer();
//			Object[] values = { deviceAddress };
//			hql.append("select bhd.deviceAddress,bhd.validationCode from BoHostDevice bhd");//可能有问题
//			hql.append(" where bhd.deviceAddress = ?");
//			List<BoHostDevice> list=findByHQL(hql.toString(), values);
//			if(list ==null) {
//				return null;
//			}
//			return list;
//		}
	}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoHostDeviceDaoImpl
 * JD-Core Version:    0.6.2
 */