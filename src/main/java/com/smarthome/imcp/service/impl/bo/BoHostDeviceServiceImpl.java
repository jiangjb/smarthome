/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.bo.BoHostDeviceDaoIface;
import com.smarthome.imcp.dao.model.bo.BoChannel;
/*     */ import com.smarthome.imcp.dao.model.bo.BoHostDevice;
import com.smarthome.imcp.dao.model.bo.BoLaunch;
import com.smarthome.imcp.dao.model.bo.BoUsers;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoHostDeviceServiceIface;
/*     */ import java.io.Serializable;
import java.util.HashMap;
/*     */ import java.util.List;
import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boHostDeviceService")
/*     */ public class BoHostDeviceServiceImpl extends AbstractBasicService<BoHostDevice, Serializable>
/*     */   implements BoHostDeviceServiceIface<BoHostDevice, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoHostDeviceDaoIface<BoHostDevice, Serializable> boHostDeviceDao;
/*     */ 
/*     */   public boolean chkSaveValid(BoHostDevice model)
/*     */   {
/*  37 */     return true;
/*     */   }
/*     */ 
/*     */   public BoHostDevice save(BoHostDevice model)
/*     */   {
/*  44 */     if (chkSaveValid(model))
/*     */     {
/*  46 */       this.boHostDeviceDao.save(model);
/*     */     }
/*  48 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoHostDevice model)
/*     */   {
/*  54 */     return true;
/*     */   }
/*     */ 
/*     */   public BoHostDevice update(BoHostDevice model)
/*     */   {
/*  59 */     if (chkUpdateValid(model)) {
/*  60 */       this.boHostDeviceDao.update(model);
/*     */     }
/*  62 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(BoHostDevice model)
/*     */   {
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */   public BoHostDevice delete(BoHostDevice model)
/*     */   {
/*  74 */     if (chkUpdateValid(model)) {
/*  75 */       this.boHostDeviceDao.delete(model);
/*     */     }
/*  77 */     return model;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/*  82 */     if ((id == null) || ("".equals(id)))
/*  83 */       return;
/*  84 */     if (chkDeleteValid(id))
/*  85 */       this.boHostDeviceDao.deleteByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/*  91 */     StringTokenizer st = new StringTokenizer(ids, ",");
/*  92 */     while (st.hasMoreElements()) {
/*  93 */       String id = st.nextToken();
/*  94 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public BoHostDevice findBy(String type, String deviceAddress, Integer num)
/*     */   {
/* 101 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 102 */     criteria.add(Restrictions.eq("deviceType", type));
/* 103 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 104 */     criteria.add(Restrictions.eq("deviceNum", num));
/* 105 */     List list = this.boHostDeviceDao.findByCriteria(criteria);
/* 106 */     if ((list == null) || (list.isEmpty())) {
/* 107 */       return null;
/*     */     }
/* 109 */     return (BoHostDevice)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoHostDevice> getByuserCode(String userCode)
/*     */   {
/* 115 */     return this.boHostDeviceDao.getByuserCode(userCode);
/*     */   }
/*     */ 
/*     */   public BoHostDevice findBydeviceAddress(String userCode, String deviceAddress)
/*     */   {
/* 124 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 125 */     criteria.createAlias("boUsers", "boUsers");
/* 126 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 127 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/*     */ 
/* 129 */     List list = this.boHostDeviceDao.findByCriteria(criteria);
/* 130 */     if ((list == null) || (list.isEmpty())) {
/* 131 */       return null;
/*     */     }
/* 133 */     return (BoHostDevice)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoHostDevice> getListByUserCode(String userCode, String deviceCode, Boolean fid)
/*     */   {
/* 139 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 140 */     criteria.createAlias("boUsers", "boUsers");
/* 141 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 142 */     criteria.createAlias("boDevice", "boDevice");
/* 143 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 144 */     criteria.add(Restrictions.eq("deviceClassify", fid));
/* 145 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 146 */     return this.boHostDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoHostDevice> getListByUserCodes(String userCode, String deviceCode, Boolean fid)
/*     */   {
/* 152 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 153 */     criteria.createAlias("boUsers", "boUsers");
/* 154 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 155 */     criteria.createAlias("boDevice", "boDevice");
/* 156 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 157 */     criteria.add(Restrictions.eq("deviceClassify", fid));
/*     */ 
/* 159 */     return this.boHostDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoHostDevice> getUserCode(String userCode)
/*     */   {
/* 165 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 166 */     criteria.createAlias("boUsers", "boUsers");
/* 167 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 168 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 169 */     return this.boHostDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoHostDevice findBy(String userCode)
/*     */   {
/* 175 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 176 */     criteria.createAlias("boUsers", "boUsers");
/* 177 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/*     */ 
/* 179 */     List list = this.boHostDeviceDao.findByCriteria(criteria);
/* 180 */     if ((list == null) || (list.isEmpty())) {
/* 181 */       return null;
/*     */     }
/* 183 */     return (BoHostDevice)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoHostDevice> getListBy(String deviceCode)
/*     */   {
/* 189 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 190 */     criteria.createAlias("boDevice", "boDevice");
/* 191 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/*     */ 
/* 193 */     return this.boHostDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoHostDevice findByroomCode(String roomCode)
/*     */   {
/* 199 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 200 */     criteria.createAlias("boRoom", "boRoom");
/* 201 */     criteria.add(Restrictions.eq("boRoom.roomCode", roomCode));
/*     */ 
/* 203 */     List list = this.boHostDeviceDao.findByCriteria(criteria);
/* 204 */     if ((list == null) || (list.isEmpty())) {
/* 205 */       return null;
/*     */     }
/* 207 */     return (BoHostDevice)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoHostDevice> getroomCode(String userCode, String roomCode)
/*     */   {
/* 215 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 216 */     criteria.createAlias("boUsers", "boUsers");
/* 217 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 218 */     criteria.createAlias("boRoom", "boRoom");
/* 219 */     criteria.add(Restrictions.eq("boRoom.roomCode", roomCode));
/*     */ 
/* 221 */     return this.boHostDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoHostDevice> getroomCode(String roomCode)
/*     */   {
/* 227 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/*     */ 
/* 229 */     criteria.createAlias("boRoom", "boRoom");
/* 230 */     criteria.add(Restrictions.eq("boRoom.roomCode", roomCode));
/* 231 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 232 */     criteria.add(Restrictions.eq("whetherQueryStateSign", "Y"));
/* 233 */     criteria.addOrder(Order.asc("deviceType"));
/* 234 */     criteria.addOrder(Order.asc("deviceAddress"));
/* 235 */     return this.boHostDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoHostDevice> getByroomCode(String roomCode)
/*     */   {
/* 240 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 241 */     criteria.createAlias("boRoom", "boRoom");
/* 242 */     criteria.add(Restrictions.eq("boRoom.roomCode", roomCode));
/* 243 */     return this.boHostDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoHostDevice> getListByUserCode(String userCode)
/*     */   {
/* 248 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 249 */     criteria.createAlias("boUsers", "boUsers");
/* 250 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/*     */ 
/* 252 */     return this.boHostDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoHostDevice findBydeviceAddress(String deviceAddress)
/*     */   {
/* 258 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 259 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 260 */     List list = this.boHostDeviceDao.findByCriteria(criteria);
/* 261 */     if ((list == null) || (list.isEmpty())) {
/* 262 */       return null;
/*     */     }
/* 264 */     return (BoHostDevice)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoHostDevice> get(String userCode, String deviceCode, Boolean fid)
/*     */   {
/* 270 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 271 */     criteria.createAlias("boUsers", "boUsers");
/* 272 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 273 */     criteria.createAlias("boDevice", "boDevice");
/* 274 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 275 */     criteria.add(Restrictions.eq("deviceClassify", fid));
/* 276 */     return this.boHostDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoHostDevice> get(String userCode, String deviceCode)
/*     */   {
/* 282 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 283 */     criteria.createAlias("boUsers", "boUsers");
/* 284 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 285 */     criteria.createAlias("boDevice", "boDevice");
/* 286 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 287 */     return this.boHostDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoHostDevice> getListBy(String userCode, String deviceAddress)
/*     */   {
/* 293 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 294 */     criteria.createAlias("boUsers", "boUsers");
/* 295 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 296 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 297 */     criteria.add(Restrictions.eq("whetherQueryStateSign", "Y"));
/* 298 */     return this.boHostDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoHostDevice> getListBys(String deviceAddress)
/*     */   {
/* 304 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 305 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 306 */     return this.boHostDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoHostDevice> getListByUserCodess(String userCode, Boolean fid)
/*     */   {
/* 312 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 313 */     criteria.createAlias("boUsers", "boUsers");
/* 314 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/*     */ 
/* 317 */     criteria.add(Restrictions.eq("deviceClassify", fid));
/* 318 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 319 */     return this.boHostDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoHostDevice> getListByUserCode(String userCode, Boolean fid)
/*     */   {
/* 325 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 326 */     criteria.createAlias("boUsers", "boUsers");
/* 327 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 328 */     criteria.add(Restrictions.eq("deviceClassify", fid));
/* 329 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 330 */     criteria.addOrder(Order.asc("deviceType"));
/* 331 */     return this.boHostDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoHostDevice findBydeviceAddress(String userCode, String deviceCode, String deviceAddress)
/*     */   {
/* 338 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 339 */     criteria.createAlias("boUsers", "boUsers");
/* 340 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 341 */     criteria.createAlias("boDevice", "boDevice");
/* 342 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 343 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/*     */ 
/* 346 */     List list = this.boHostDeviceDao.findByCriteria(criteria);
/* 347 */     if ((list == null) || (list.isEmpty())) {
/* 348 */       return null;
/*     */     }
/* 350 */     return (BoHostDevice)list.get(0);
/*     */   }
/*     */ 
/*     */   public BoHostDevice controlEnclosure(String deviceCode, String userCode, String deviceType)
/*     */   {
/* 357 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 358 */     criteria.createAlias("boDevice", "boDevice");
/* 359 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 360 */     criteria.createAlias("boUsers", "boUsers");
/* 361 */     criteria.add(Restrictions.eq("boUsers.userCode", userCode));
/* 362 */     criteria.add(Restrictions.eq("deviceType", deviceType));
/* 363 */     List list = this.boHostDeviceDao.findByCriteria(criteria);
/* 364 */     if ((list == null) || (list.isEmpty())) {
/* 365 */       return null;
/*     */     }
/* 367 */     return (BoHostDevice)list.get(0);
/*     */   }
/*     */ 
/*     */   public BoHostDevice lock(String deviceCode, String deviceAddress)
/*     */   {
/* 373 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 374 */     criteria.createAlias("boDevice", "boDevice");
/* 375 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 376 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 377 */     List list = this.boHostDeviceDao.findByCriteria(criteria);
/* 378 */     if ((list == null) || (list.isEmpty())) {
/* 379 */       return null;
/*     */     }
/* 381 */     return (BoHostDevice)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoHostDevice> getDeviceByAddress(String deviceAddress)
/*     */   {
/* 387 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 388 */     criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
/* 389 */     return this.boHostDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoHostDevice> getLists(String deviceCode, String deviceType)
/*     */   {
/* 395 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 396 */     criteria.createAlias("boDevice", "boDevice");
/* 397 */     criteria.add(Restrictions.eq("boDevice.deviceCode", deviceCode));
/* 398 */     criteria.add(Restrictions.eq("deviceType", deviceType));
/*     */ 
/* 400 */     return this.boHostDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoHostDevice> getAllList()
/*     */   {
/* 406 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
/* 407 */     return this.boHostDeviceDao.findByCriteria(criteria);
/*     */   }
/*     */
			@Override
			public String addHostDevice(String deviceCode, String type) {
				return this.boHostDeviceDao.addValidation(deviceCode,type);
			}

			@Override
			public List<BoHostDevice> getAllHostD() {
				return this.boHostDeviceDao.getAllHostD();
			}
//			@Override
//			public List<BoHostDevice> getAllDevices() {
//				// TODO Auto-generated method stub
//				return this.boHostDeviceDao.getAllDevices();
//			}
//			@Override
//			public List<BoHostDevice> getAllRed() {
//				return this.boHostDeviceDao.getAllRed();
//			}
			@Override
			public String addDeviceRed(String deviceAddress, String validationCode) {
				// TODO Auto-generated method stub
				return this.boHostDeviceDao.addDeviceRed(deviceAddress,validationCode);
			}
	
			@Override
			public List<BoHostDevice> findHostByUserPhone(String userPhone) {
//				return this.boHostDeviceDao.findHostByUserPhone(userPhone);
				DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
				criteria.createAlias("boUsers", "boUsers");//3-22
				criteria.add(Restrictions.eq("boUsers.userPhone", userPhone));//3-22
				List<BoHostDevice> list = this.boHostDeviceDao.findByCriteria(criteria);
				if ((list == null) || (list.isEmpty())) {
					return null;
				}
				return list;
			}
			@Override
			public BoHostDevice findHostByDevicecode(String deviceCode) {
				DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
				criteria.add(Restrictions.eq("deviceCode", deviceCode));
				List<BoHostDevice> list = this.boHostDeviceDao.findByCriteria(criteria);
				if ((list == null) || (list.isEmpty())) {
					return null;
				}
				return (BoHostDevice)list.get(0);
			}
			@Override
			public List<BoHostDevice> findRedByAddr(String deviceAddress) {
				System.out.println("findRedByAddr");
				DetachedCriteria criteria = DetachedCriteria.forClass(BoHostDevice.class);
				criteria.add(Restrictions.eq("deviceAddress", deviceAddress));
				List<BoHostDevice> list = this.boHostDeviceDao.findByCriteria(criteria);
				System.out.println("list:"+list);
				if ((list == null) || (list.isEmpty())) {
					return null;
				}
				return list;
			}
			//new add
			public BoHostDevice findByKey(Serializable id){
				BoHostDevice model = (BoHostDevice)this.boHostDeviceDao.findById(id);
					return model;
			}

}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoHostDeviceServiceImpl
 * JD-Core Version:    0.6.2
 */