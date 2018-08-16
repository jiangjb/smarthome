/*    */ package com.smarthome.imcp.service.impl.bo;
 
		 import com.smarthome.imcp.dao.bo.BrandsDaoIface;
		 import com.smarthome.imcp.dao.model.bo.Brands;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
		 import com.smarthome.imcp.service.bo.BrandsServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("brandsService")
/*    */ public class BrandsServiceImpl extends AbstractBasicService<Brands, Serializable>
/*    */   implements BrandsServiceIface<Brands, Serializable>
/*    */ {
			@Autowired
	        private BrandsDaoIface<Brands, Serializable> brandsDao;


			@Override
			public String findModelByid(int id) {
				return this.brandsDao.findModelByid(id);
			}


			@Override
			public List findAllBrands(int deviceId) {
				return this.brandsDao.findAllBrands(deviceId);
			}


   	
		}
